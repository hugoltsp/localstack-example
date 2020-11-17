package com.localstack.sample

import com.amazonaws.services.s3.AmazonS3ClientBuilder
import org.junit.jupiter.api.Test
import org.testcontainers.containers.localstack.LocalStackContainer
import org.testcontainers.containers.localstack.LocalStackContainer.Service.S3
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.DockerImageName.parse

@Testcontainers
class LocalstackTestContainerS3Test {

    @Container
    val localstack = LocalStackContainer(parse("localstack/localstack:0.12.2"))
            .withServices(S3)

    @Test
    fun testS3Api() {

        val s3 = AmazonS3ClientBuilder
                .standard()
                .withEndpointConfiguration(localstack.getEndpointConfiguration(S3))
                .withCredentials(localstack.defaultCredentialsProvider)
                .build()

        s3.createBucket("bucket-teste")
        s3.putObject("bucket-teste",
                "nome-do-objeto",
                "churrasco")

        assert(String(s3.getObject("bucket-teste", "nome-do-objeto").objectContent.readAllBytes()) == "churrasco")
    }

}
package com.localstack.sample

import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.regions.Regions
import com.amazonaws.services.s3.AmazonS3ClientBuilder

class S3Config {

    fun testS3Client() {

        val endpointConfiguration = AwsClientBuilder
                .EndpointConfiguration("http://localhost:4572", Regions.US_EAST_1.name)

        val awsS3 = AmazonS3ClientBuilder
                .standard()
                .enablePathStyleAccess()
                .withEndpointConfiguration(endpointConfiguration)
                .build()

        awsS3.deleteObject("teste-123","BBBaokdpoaskdpokaspokapdos")
        awsS3.putObject("teste-123", "aaaaaaaaaaaaaaaaaaaaaa", "aaaaaa")

    }

}
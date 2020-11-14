package com.localstack.sample

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@Configuration
class AmazonS3Config {

    @Primary
    @Bean
    fun s3Client(@Value("\${cloud.aws.credentials.secret-key}") secretKey: String,
                 @Value("\${cloud.aws.credentials.access-key}") accessKey: String,
                 @Value("\${cloud.aws.credentials.endpoint}") endpoint: String,
                 @Value("\${cloud.aws.region.static}") region: String): AmazonS3 = AmazonS3ClientBuilder
            .standard()
            .enablePathStyleAccess()
            .withEndpointConfiguration(EndpointConfiguration(endpoint, region))
            .withCredentials(AWSStaticCredentialsProvider(BasicAWSCredentials(accessKey, secretKey)))
            .build()


}
package com.localstack.sample

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration
import com.amazonaws.services.kms.AWSKMSClientBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AmazonKMSConfig {

    @Bean
    fun kmsClient(@Value("\${cloud.aws.credentials.secret-key}") secretKey: String,
                  @Value("\${cloud.aws.credentials.access-key}") accessKey: String,
                  @Value("\${cloud.aws.credentials.endpoint}") endpoint: String,
                  @Value("\${cloud.aws.region.static}") region: String) = AWSKMSClientBuilder
            .standard()
            .withEndpointConfiguration(EndpointConfiguration(endpoint, region))
            .withCredentials(AWSStaticCredentialsProvider(BasicAWSCredentials(accessKey, secretKey)))
            .build()

}
package com.localstack.sample

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.regions.Regions
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SqsClientConfig {


    @Bean
    fun sqsClient(@Value("\${cloud.aws.credentials.secret-key}") secretKey: String,
                  @Value("\${cloud.aws.credentials.access-key}") accessKey: String) = AmazonSQSAsyncClientBuilder
            .standard()
            .withEndpointConfiguration(AwsClientBuilder
                    .EndpointConfiguration(SQS_SERVICE_ENDPOINT, Regions.US_EAST_1.name))
            .withCredentials(AWSStaticCredentialsProvider(BasicAWSCredentials(accessKey, secretKey)))
            .build()

    private companion object {

        const val SQS_SERVICE_ENDPOINT = "http://localhost:4576"

    }

}
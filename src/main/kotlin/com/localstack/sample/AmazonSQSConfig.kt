package com.localstack.sample

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration
import com.amazonaws.services.sqs.AmazonSQSAsync
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder.standard
import com.amazonaws.services.sqs.buffered.AmazonSQSBufferedAsyncClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@Configuration
class AmazonSQSConfig {

    @Bean
    @Primary
    fun sqsClient(@Value("\${cloud.aws.credentials.secret-key}") secretKey: String,
                  @Value("\${cloud.aws.credentials.access-key}") accessKey: String,
                  @Value("\${cloud.aws.credentials.sqs.endpoint}") endpoint: String,
                  @Value("\${cloud.aws.region.static}") region: String): AmazonSQSAsync = AmazonSQSBufferedAsyncClient(standard()
            .withEndpointConfiguration(EndpointConfiguration(endpoint, region))
            .withCredentials(AWSStaticCredentialsProvider(BasicAWSCredentials(accessKey, secretKey)))
            .build())

    @Bean
    fun messageTemplate(amazonSQSAsync: AmazonSQSAsync) = QueueMessagingTemplate(amazonSQSAsync)

}
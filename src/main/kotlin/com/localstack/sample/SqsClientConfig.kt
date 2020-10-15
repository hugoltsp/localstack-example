package com.localstack.sample

import com.amazon.sqs.javamessaging.ProviderConfiguration
import com.amazon.sqs.javamessaging.SQSConnectionFactory
import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.regions.Regions
import com.amazonaws.services.sqs.AmazonSQSClientBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jms.config.DefaultJmsListenerContainerFactory
import org.springframework.jms.core.JmsTemplate
import org.springframework.jms.support.destination.DynamicDestinationResolver


@Configuration
class SqsClientConfig {

    @Bean
    fun config(@Autowired sqsConnectionFactory: SQSConnectionFactory): JmsTemplate {
        return JmsTemplate(sqsConnectionFactory)
    }

//    @Bean
    fun jmsListenerConnectionFactory(@Autowired sqsConnectionFactory: SQSConnectionFactory): DefaultJmsListenerContainerFactory {
        val factory = DefaultJmsListenerContainerFactory()
        factory.setConnectionFactory(sqsConnectionFactory)
        factory.setDestinationResolver(DynamicDestinationResolver())
//        factory.setSessionAcknowledgeMode(Session.AUTO_ACKNOWLEDGE)
        factory.setSessionTransacted(false)
        return factory
    }

    @Bean
    fun sqsConnectionFactory(): SQSConnectionFactory {
        return SQSConnectionFactory(ProviderConfiguration(), AmazonSQSClientBuilder
                .standard()
                .withEndpointConfiguration(AwsClientBuilder
                        .EndpointConfiguration("http://localhost:4576", Regions.US_EAST_1.name))
                .withCredentials(AWSStaticCredentialsProvider(BasicAWSCredentials("aaa",
                        "aaa")))
                .build())
    }

}
package com.localstack.sample

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy.ON_SUCCESS
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener
import org.springframework.messaging.handler.annotation.Headers
import org.springframework.stereotype.Component

@Component
class SQSMessageListener {

    @SqsListener(value = ["fila", "fila-1"], deletionPolicy = ON_SUCCESS)
    fun listen(payload: String, @Headers headers: Map<String, String>) {
        LOGGER.info("Retrieved: [{}] w/ headers: [{}] from SQS.", payload, headers)
    }

    private companion object {

        val LOGGER: Logger = LoggerFactory.getLogger(SQSMessageListener::class.java)

    }

}
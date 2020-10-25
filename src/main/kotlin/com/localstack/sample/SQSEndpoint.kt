package com.localstack.sample

import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/sqs")
class SQSEndpoint(val messageTemplate: QueueMessagingTemplate) {

    @PostMapping
    fun post(@RequestBody SQSMessage: SQSMessage){
        messageTemplate.convertAndSend("fila", SQSMessage)
    }

}
package com.localstack.sample

import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class LocalstackSampleApplication

fun main() {
    S3Config().testSqsLocalstack()
}

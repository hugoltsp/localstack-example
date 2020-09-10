package com.localstack.sample

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class LocalstackSampleApplication

fun main() {
	S3Config().testS3Client()
}

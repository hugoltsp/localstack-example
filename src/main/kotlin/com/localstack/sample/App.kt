package com.localstack.sample

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.aws.messaging.config.annotation.EnableSqs

@EnableSqs
@SpringBootApplication
class App {

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            runApplication<App>(*args)
        }

    }


}

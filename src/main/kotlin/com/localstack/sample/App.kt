package com.localstack.sample

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener

@SpringBootApplication
class App : CommandLineRunner {

    @SqsListener("teste")
    fun listen(payload: String) {
        println(payload)
    }

    override fun run(vararg args: String?) {

    }

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            runApplication<App>(*args)
        }

    }


}

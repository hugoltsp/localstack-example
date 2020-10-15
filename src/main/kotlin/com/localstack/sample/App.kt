package com.localstack.sample

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.jms.annotation.JmsListener

@SpringBootApplication
class App : CommandLineRunner {


    @JmsListener(destination = "teste")
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

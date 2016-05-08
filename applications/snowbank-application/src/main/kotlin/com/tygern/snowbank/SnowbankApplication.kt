package com.tygern.snowbank

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class SnowbankApplication

fun main(args: Array<String>) {
    SpringApplication.run(SnowbankApplication::class.java, *args)
}
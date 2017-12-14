package com.reactive.reactivedemo

import com.mongodb.reactivestreams.client.MongoClient
import com.mongodb.reactivestreams.client.MongoClients
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class ReactivedemoApplication

fun main(args: Array<String>) {
    runApplication<ReactivedemoApplication>(*args)
}

@Bean
fun mongoClient(): MongoClient {
    return MongoClients.create("mongodb://localhost")
}
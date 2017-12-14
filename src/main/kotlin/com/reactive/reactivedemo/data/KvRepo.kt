package com.reactive.reactivedemo.data

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.findById
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
class KvRepo {
    @Autowired
    lateinit var template: ReactiveMongoTemplate

    fun findByKey(key: String): Mono<String> {
        val kv: Mono<KvProperty> = template.findById(key, KvProperty::class.java)
        return kv.map { t -> t.value }
    }

    fun save(kvProperty: Mono<KvProperty>): Mono<KvProperty> {
        return template.insert(kvProperty)
    }
}
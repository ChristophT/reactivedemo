package com.reactive.reactivedemo

import com.reactive.reactivedemo.data.KvProperty
import com.reactive.reactivedemo.data.KvRepo
import org.reactivestreams.Publisher
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@RestController
class DemoController {

    @Autowired
    lateinit var kvRepo: KvRepo

    @GetMapping("/{name}")
    fun getHeight(@PathVariable name: String) : Mono<ResponseEntity<String>>? {
        return kvRepo.findByKey(name)
                .map { kv -> ResponseEntity.ok().body(kv) }
                .defaultIfEmpty(ResponseEntity.status(404).body(null))
    }

    @PutMapping("/{key}")
    fun putValue(@PathVariable key: String, @RequestBody valueStream: Publisher<String>): Mono<KvProperty> {
        return kvRepo.save(Mono.from(valueStream)
                .map { value -> KvProperty(key, value) })
    }
}
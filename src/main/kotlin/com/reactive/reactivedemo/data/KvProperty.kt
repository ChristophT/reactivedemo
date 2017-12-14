package com.reactive.reactivedemo.data

import org.springframework.data.annotation.Id

data class KvProperty(@Id var key: String, var value: String)
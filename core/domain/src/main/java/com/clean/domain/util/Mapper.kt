package com.clean.domain.util

interface Mapper<IN, OUT> {
    fun map(dto: IN): OUT
}
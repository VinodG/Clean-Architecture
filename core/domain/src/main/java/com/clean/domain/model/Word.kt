package com.clean.domain.model


data class Word(
    val word: String = "",
    val phonetic: String = "",
    val meanings: List<Meaning> = listOf()
)








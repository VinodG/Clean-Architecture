package com.clean.domain.model

data class Meaning(
    val partOfSpeech: String = "",
    val definitions: List<Definition> = listOf()
)
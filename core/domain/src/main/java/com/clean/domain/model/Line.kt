package com.clean.domain.model

data class Line(val line: String, val type: Type) {
    enum class Type {
        Phonetic,
        Definition,
        Example,
        Word,
        PartsOfSpeech
    }
}
package com.clean.network.model

import kotlinx.serialization.Serializable

@Serializable
data class WordDto(
    val word: String? = null,
    val phonetic: String? = null,
    val meanings: List<MeaningDto>? = null
)
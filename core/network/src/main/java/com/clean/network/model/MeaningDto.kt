package com.clean.network.model

import kotlinx.serialization.Serializable

@Serializable
data class MeaningDto(
    val partOfSpeech: String? = null,
    val definitions: List<DefinitionDto>? = null
)

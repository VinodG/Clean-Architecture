package com.clean.network.model

import kotlinx.serialization.Serializable

@Serializable
data class DefinitionDto(
    val definition: String? = null,
    val example: String? = null,
)
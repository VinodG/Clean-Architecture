package com.clean.data.mapper

import com.clean.network.model.DefinitionDto
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class DefinitionDtoMapperTest {

    private var mapper: DefinitionDtoMapper = DefinitionDtoMapper()

    @Test
    fun `given Dto object with null values, when map() is called, then it returns Definition object with empty fields`() {
        val definitionDto = DefinitionDto()
        val definition = mapper.map(definitionDto)
        assertThat(definition.definition).isEmpty()
        assertThat(definition.example).isEmpty()
    }

    @Test
    fun `given Dto object, when map() is called, then it returns Definition object`() {
        val fakeDefinition = "definition"
        val fakeExample = "example"
        val definitionDto = DefinitionDto(
            definition = fakeDefinition,
            example = fakeExample
        )
        val definition = mapper.map(definitionDto)
        assertThat(definition.definition).isEqualTo(fakeDefinition)
        assertThat(definition.example).isEqualTo(fakeExample)
    }
}
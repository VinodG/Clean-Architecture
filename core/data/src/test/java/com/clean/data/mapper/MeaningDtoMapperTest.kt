package com.clean.data.mapper

import com.clean.domain.model.Definition
import com.clean.network.model.DefinitionDto
import com.clean.network.model.MeaningDto
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever


class MeaningDtoMapperTest {

    private lateinit var definitionDtoMapper: DefinitionDtoMapper
    private lateinit var mapper: MeaningDtoMapper
    private val expectedPartsOfSpeech = "partsOfSpeech"


    @Before
    fun setUp() {
        definitionDtoMapper = mock()
        mapper = MeaningDtoMapper(definitionDtoMapper)
    }

    @Test
    fun `given null values to Dto object, when map(dto) is called, it returns Meaning object with empty values`() {
        val meaningDto = MeaningDto(null, null)
        val meaning = mapper.map(meaningDto)
        assertThat(meaning.definitions).isEmpty()
        assertThat(meaning.partOfSpeech).isEmpty()
    }

    @Test
    fun `given non-null values to Dto object, when map(dto) is called, it returns valid Meaning object`() {
        val expectedDefinition = "definition"
        val expectedExample = "example"
        val actualDefinitionDto =
            DefinitionDto(definition = expectedDefinition, example = expectedExample)
        val expectedDefinitionObject =
            Definition(definition = expectedDefinition, example = expectedExample)
        whenever(definitionDtoMapper.map(actualDefinitionDto)).thenReturn(expectedDefinitionObject)
        val meaningDto = MeaningDto(
            expectedPartsOfSpeech,
            listOf(actualDefinitionDto)
        )
        val meaning = mapper.map(meaningDto)
        meaning.apply {
            assertThat(partOfSpeech).isEqualTo(expectedPartsOfSpeech)
            assertThat(definitions).isEqualTo(listOf(expectedDefinitionObject))
        }
    }

    @Test
    fun `given empty object to definitions in Dto object, when map(dto) is called, it returns Meaning object with empty definition list`() {
        val meaningDto = MeaningDto(expectedPartsOfSpeech, listOf())
        val meaning = mapper.map(meaningDto)
        meaning.apply {
            assertThat(partOfSpeech).isEqualTo(expectedPartsOfSpeech)
            assertThat(definitions).isEmpty()
        }
    }
}
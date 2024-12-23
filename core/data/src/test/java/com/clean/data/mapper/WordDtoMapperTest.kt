package com.clean.data.mapper

import com.clean.domain.model.Meaning
import com.clean.network.model.MeaningDto
import com.clean.network.model.WordDto
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock


class WordDtoMapperTest {

    private lateinit var wordDtoMapper: WordDtoMapper
    private lateinit var meaningDtoMapper: MeaningDtoMapper


    @Before
    fun setup() {
        meaningDtoMapper = mock()
        wordDtoMapper = WordDtoMapper(meaningDtoMapper)
    }

    @Test
    fun `given default WordDto object, when map() is called, then returns Word object with empty values`() {
        val wordDto = WordDto()
        val actualWord = wordDtoMapper.map(wordDto)
        actualWord.apply {
            assertThat(word).isEmpty()
            assertThat(phonetic).isEmpty()
            assertThat(meanings).isEmpty()
        }
    }

    @Test
    fun `given meanings as empty to the WordDto, when map() is called, then returns Word object with meanings as empty`() {
        val wordDto = WordDto(meanings = listOf())
        val actualWord = wordDtoMapper.map(wordDto)
        actualWord.apply {
            assertThat(word).isEmpty()
            assertThat(phonetic).isEmpty()
            assertThat(meanings).isEmpty()
        }
    }

    @Test
    fun `given valid WordDto, when map() is called, then returns valid Word`() {
        val word = "word"
        val phonetic = "phonetic"
        val partOfSpeech = "partsOfSpeech"
        val meaningDto = MeaningDto(partOfSpeech = partOfSpeech,null)
        val meaning = Meaning(partOfSpeech = partOfSpeech, listOf())
        Mockito.`when`(meaningDtoMapper.map(meaningDto)).thenReturn(meaning)
        val wordDto = WordDto(meanings = listOf(meaningDto))
        val actualWord = wordDtoMapper.map(wordDto)
        actualWord.apply {
            assertThat(word).isEqualTo(word)
            assertThat(phonetic).isEqualTo(phonetic)
            assertThat(meanings).isEqualTo(listOf(meaning))
        }
    }
}
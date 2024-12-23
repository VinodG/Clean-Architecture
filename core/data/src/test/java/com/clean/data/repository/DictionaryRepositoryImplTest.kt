package com.clean.data.repository

import com.clean.data.mapper.WordDtoMapper
import com.clean.domain.model.Word
import com.clean.network.datasource.DictionaryDataSource
import com.clean.network.model.WordDto
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class DictionaryRepositoryImplTest {
    private lateinit var repository: DictionaryRepositoryImpl
    private lateinit var dataSource: DictionaryDataSource
    private lateinit var wordDtoMapper: WordDtoMapper
    private val testDispatcher = Dispatchers.IO
    private val word = "test"

    @Before
    fun setup() {
        dataSource = mock()
        wordDtoMapper = mock()
        repository = DictionaryRepositoryImpl(dataSource, wordDtoMapper, testDispatcher)
    }

    @Test
    fun `given getMeanings(word) of datasource returns  wordDtoList, when getMeaning(word) is called, then it returns flow of list of word object`() = runTest {
        val wordDto = mock<WordDto>()
        val wordModel = mock<Word>()
        val wordDtoList = listOf(wordDto)
        `when`(dataSource.getMeanings(word)).thenReturn(wordDtoList)
        `when`(wordDtoMapper.map(wordDto)).thenReturn(wordModel)
        val result = repository.getMeaning(word)
        result.onEach {
            assertThat(it).hasSize(1)
            assertThat(it.first()).isEqualTo(wordModel)
        }.collect()
    }

    @Test
    fun `given getMeanings(word) of datasource returns empty list, when getMeaning(word) is called, then it returns flow of empty list`() = runTest {
         `when`(dataSource.getMeanings(word)).thenReturn(emptyList())
        val result = repository.getMeaning(word)
        result.onEach {
            assertThat(it).isEmpty()
        }.collect()
    }

    @Test
    fun `given getMeanings(word) of datasource throws exception, when getMeaning(word) is called, then it throws exception`() = runTest {
        val exception = Exception("")
        `when`(dataSource.getMeanings(word)).then { exception }
        repository.getMeaning(word).catch {
            assertThat(it).isInstanceOf(Exception::class.java)
        }.onEach {
            assertThat(it).isEmpty()
        }.collect()
    }
}
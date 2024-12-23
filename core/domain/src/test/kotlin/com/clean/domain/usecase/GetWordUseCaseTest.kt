package com.clean.domain.usecase

import com.clean.domain.DictionaryRepository
import com.clean.domain.model.Word
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`



class GetWordUseCaseTest {
    private val newWord = "new"
    private val expectedResponse = listOf(Word())
    private val repository: DictionaryRepository = mock()
    private val getWordUseCase = GetWordUseCase(repository)

    @Test
    fun `given getMeaning() returns list of words, when getWordUseCase() called, then it returns list of words`() = runTest {
        `when`(repository.getMeaning(newWord)).thenReturn(flowOf(expectedResponse))
        getWordUseCase(newWord).onEach {
            assertThat(it).isEqualTo(expectedResponse)
        }.collect()
    }
    @Test
    fun `given getMeaning() returns empty, when getWordUseCase() called, then it returns empty of words`() = runTest {
        `when`(repository.getMeaning(newWord)).thenReturn(flowOf(listOf()))
        getWordUseCase(newWord).onEach {
            assertThat(it).isEmpty()
        }.collect()
    }
}
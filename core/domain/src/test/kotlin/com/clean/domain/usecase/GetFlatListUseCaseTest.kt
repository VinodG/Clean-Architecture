package com.clean.domain.usecase

import com.clean.domain.model.Definition
import com.clean.domain.model.Line
import com.clean.domain.model.Meaning
import com.clean.domain.model.Word
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever


class GetFlatListUseCaseTest {
    private val definition = "definition"
    private val example = "example"
    private val word = "word"
    private val phonetic = "phonetic"
    private val partsOfSpeech = "partsOfSpeech"
    private lateinit var getWordUseCase: GetWordUseCase
    private lateinit var getFlatListUseCase: GetFlatListUseCase


    @Before
    fun setUp() {
        getWordUseCase = mock()
        getFlatListUseCase = GetFlatListUseCase(getWordUseCase)
    }

    @Test
    fun `given getWordUseCase() returns list of Word, when getFlatListUseCase() is called, then it returns list of Line objects`() =
        runTest {
            whenever(getWordUseCase(word)).thenReturn(flowOf(listOf(getWord())))
            getFlatListUseCase(word).collectLatest {
                assertThat(it).isEqualTo(getExpected())
            }
        }

    @Test
    fun `given getWordUseCase() returns list of Word with out word, when getFlatListUseCase() is called, then it returns list of Line objects with out Word type`() =
        runTest {
            whenever(getWordUseCase(word)).thenReturn(flowOf(listOf(getWord(isWordSkipped = true))))
            getFlatListUseCase(word).collectLatest {
                assertThat(it).isEqualTo(getExpected(isWordSkipped = true))
            }
        }

    @Test
    fun `given getWordUseCase() returns list of Word with out phonetic, when getFlatListUseCase() is called, then it returns list of Line objects with out Phonetic type`() =
        runTest {
            whenever(getWordUseCase(word)).thenReturn(flowOf(listOf(getWord(isPhoneticSkipped = true))))
            getFlatListUseCase(word).collectLatest {
                assertThat(it).isEqualTo(getExpected(isPhoneticSkipped = true))
            }
        }

    @Test
    fun `given getWordUseCase() returns list of Word with out definition, when getFlatListUseCase() is called, then it returns list of Line objects with out Definition type`() =
        runTest {
            whenever(getWordUseCase(word)).thenReturn(flowOf(listOf(getWord(isDefinitionSkipped = true))))
            getFlatListUseCase(word).collectLatest {
                assertThat(it).isEqualTo(getExpected(isDefinitionSkipped = true))
            }
        }

    @Test
    fun `given getWordUseCase() returns list of Word with out partsOfSpeech, when getFlatListUseCase() is called, then it returns list of Line objects with out PartsOfSpeech type`() =
        runTest {
            whenever(getWordUseCase(word)).thenReturn(flowOf(listOf(getWord(isDefinitionSkipped = true))))
            getFlatListUseCase(word).collectLatest {
                assertThat(it).isEqualTo(getExpected(isDefinitionSkipped = true))
            }
        }

    @Test
    fun `given getWordUseCase() returns list of Word with out example, when getFlatListUseCase() is called, then it returns list of Line objects with out Example type`() =
        runTest {
            whenever(getWordUseCase(word)).thenReturn(flowOf(listOf(getWord(isExampleSkipped = true))))
            getFlatListUseCase(word).collectLatest {
                assertThat(it).isEqualTo(getExpected(isExampleSkipped = true))
            }
        }

    private fun getWord(
        isPhoneticSkipped: Boolean = false,
        isWordSkipped: Boolean = false,
        isDefinitionSkipped: Boolean = false,
        isPartsOfSpeechSkipped: Boolean = false,
        isExampleSkipped: Boolean = false,
    ): Word {
        val definition = Definition(
            definition = if (isDefinitionSkipped) "" else definition,
            example = if (isExampleSkipped) "" else example
        )

        val meanings = Meaning(
            partOfSpeech = if (isPartsOfSpeechSkipped) "" else partsOfSpeech,
            definitions = listOf(definition)
        )
        return Word(
            word = if (isWordSkipped) "" else word,
            phonetic = if (isPhoneticSkipped) "" else phonetic,
            meanings = listOf(meanings)
        )
    }

    private fun getExpected(
        isPhoneticSkipped: Boolean = false,
        isWordSkipped: Boolean = false,
        isDefinitionSkipped: Boolean = false,
        isPartsOfSpeechSkipped: Boolean = false,
        isExampleSkipped: Boolean = false,
    ): List<Line> {
        return mutableListOf<Line>().apply {
            if (!isWordSkipped) add(Line(word, Line.Type.Word))
            if (!isPhoneticSkipped) add(Line(phonetic, Line.Type.Phonetic))
            if (!isPartsOfSpeechSkipped) add(Line(partsOfSpeech, Line.Type.PartsOfSpeech))
            if (!isDefinitionSkipped) add(Line(definition, Line.Type.Definition))
            if (!isExampleSkipped) add(Line(example, Line.Type.Example))
        }
    }
}
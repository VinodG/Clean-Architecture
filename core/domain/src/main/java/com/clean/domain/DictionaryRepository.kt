package com.clean.domain

import com.clean.domain.model.Word
import kotlinx.coroutines.flow.Flow

interface DictionaryRepository {
    suspend fun getMeaning(word: String): Flow<List<Word>>
}
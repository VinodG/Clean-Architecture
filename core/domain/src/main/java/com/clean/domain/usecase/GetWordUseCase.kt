package com.clean.domain.usecase

import com.clean.domain.DictionaryRepository
import com.clean.domain.model.Word
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetWordUseCase @Inject constructor(private val repository: DictionaryRepository) {
    suspend operator fun invoke(word: String): Flow<List<Word>> {
        return repository.getMeaning(word)
    }
}
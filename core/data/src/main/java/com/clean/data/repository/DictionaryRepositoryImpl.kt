package com.clean.data.repository

import com.clean.data.mapper.WordDtoMapper
import com.clean.domain.DictionaryRepository
import com.clean.domain.model.Word
import com.clean.network.datasource.DictionaryDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DictionaryRepositoryImpl @Inject constructor(
    private val dataSource: DictionaryDataSource,
    private val wordDtoMapper: WordDtoMapper,
    private val coroutineDispatcher: CoroutineDispatcher
) : DictionaryRepository {
    override suspend fun getMeaning(word: String): Flow<List<Word>> {
        return flow {
            emit(dataSource.getMeanings(word).map { wordDtoMapper.map(it) })
        }.flowOn(coroutineDispatcher)
    }
}
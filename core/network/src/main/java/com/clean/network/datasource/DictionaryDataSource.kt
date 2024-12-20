package com.clean.network.datasource

import com.clean.network.model.WordDto

interface DictionaryDataSource {
    suspend fun getMeanings(word: String): List<WordDto>
}




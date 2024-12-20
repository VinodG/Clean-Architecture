package com.clean.network.datasource

import com.clean.network.CaApi
import com.clean.network.model.WordDto
import javax.inject.Inject

interface DictionaryDataSource {
    suspend fun getMeanings(word: String): List<WordDto>
}




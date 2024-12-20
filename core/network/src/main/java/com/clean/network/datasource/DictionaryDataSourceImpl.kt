package com.clean.network.datasource

import com.clean.network.CaApi
import com.clean.network.model.WordDto
import javax.inject.Inject

class DictionaryDataSourceImpl @Inject constructor(
    private val api: dagger.Lazy<CaApi>
) : DictionaryDataSource {
    override suspend fun getMeanings(word: String): List<WordDto> {
        return api.get().getMeanings(word)
    }
}
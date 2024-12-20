package com.clean.network

import com.clean.network.model.WordDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CaApi {
    @GET("entries/en/{word}")
    suspend fun getMeanings(@Path("word") word: String): List<WordDto>
}
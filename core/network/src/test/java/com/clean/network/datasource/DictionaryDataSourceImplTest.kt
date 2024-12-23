package com.clean.network.datasource

import com.clean.network.CaApi
import com.clean.network.model.WordDto
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock


class DictionaryDataSourceImplTest {
    private lateinit var api: CaApi
    private lateinit var dataSource: DictionaryDataSource
    private val word = ""

    @Before
    fun setUp() {
        api = mock()
        dataSource = DictionaryDataSourceImpl { api }
    }

    @Test
    fun `given getMeanings() of api returns empty list,when getMeanings() is called, then it returns empty list`() =
        runTest {
            Mockito.`when`(api.getMeanings(word)).thenReturn(listOf())
            assertThat(dataSource.getMeanings(word)).isEmpty()
        }

    @Test
    fun `given getMeanings() of api returns non-empty list, when getMeanings() is called, then it returns non-empty list`() =
        runTest {
            val list = listOf(WordDto())
            Mockito.`when`(api.getMeanings(word)).thenReturn(list)
            assertThat(dataSource.getMeanings(word)).isEqualTo(list)
        }
}
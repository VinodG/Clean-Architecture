package com.clean.home

import com.clean.domain.model.Line
import com.clean.domain.usecase.GetFlatListUseCase
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock


@ExperimentalCoroutinesApi
class HomeViewModelTest {

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private val getFlatListUseCase: GetFlatListUseCase = mock()
    private val expectedWords = listOf(Line("line",Line.Type.Word))

    private lateinit var viewModel: HomeViewModel
    private val newWord = "test"


    @Before
    fun setup() = runTest {
        Mockito.`when`(getFlatListUseCase.invoke(newWord)).thenReturn(flowOf(expectedWords))
        viewModel = HomeViewModel(getFlatListUseCase)
    }

    @Test
    fun `given OnChange(newWord), when onEvent() is called, then word returns newWord`() = runTest {
        viewModel.onEvent(Home.Event.OnChange(newWord))
        assertThat(viewModel.state.value.word).isEqualTo(newWord)
    }

    @Test
    fun `given OnSearchClick(newWord), when onEvent() is called, then returns non-empty lines, overlayState as None`() =
        runTest {
            viewModel.onEvent(Home.Event.OnSearchClick(newWord))
            viewModel.state.value.apply {
                assertThat(overlayState).isEqualTo(Home.OverlayState.None)
                assertThat(lines).isEqualTo(expectedWords)
            }
        }

    @Test
    fun `given getFlatListUseCase(newWord) returns words with delay, when onEvent(OnSearchClick) is called, then returns overlayState as Loading`() =
        runTest {
            Mockito.`when`(getFlatListUseCase.invoke(newWord)).thenReturn(
                flow {
                    delay(2000L)
                    emit(expectedWords)
                }
            )
            viewModel = HomeViewModel(getFlatListUseCase)
            viewModel.onEvent(Home.Event.OnSearchClick(newWord))
            assertThat(viewModel.state.value.overlayState).isEqualTo(Home.OverlayState.Loading)
        }

    @Test
    fun `given getFlatListUseCase(newWord) throws exception, when onEvent(OnSearchClick) is called, then returns overlayState as Error`() =
        runTest {
            Mockito.`when`(getFlatListUseCase(newWord)).thenReturn(flow<List<Line>> {
                throw Exception("API Error")
            })
            viewModel.onEvent(Home.Event.OnSearchClick(newWord))
            assertThat(viewModel.state.value.overlayState).isEqualTo(Home.OverlayState.Error)
        }


    @Test
    fun `given OnClear, when onEvent() is called, then word returns empty string`() = runTest {
        viewModel.onEvent(Home.Event.OnChange(newWord))
        viewModel.onEvent(Home.Event.OnClear)
        assertThat(viewModel.state.value.word).isEmpty()
    }

    @Test
    fun `given OnCloseDialog, when onEvent() is called, then overlayState returns None`() =
        runTest {
            viewModel.onEvent(Home.Event.OnCloseDialog)
            assertThat(viewModel.state.value.overlayState).isEqualTo(Home.OverlayState.None)
        }
}
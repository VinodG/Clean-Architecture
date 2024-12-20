package com.clean.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clean.domain.usecase.GetFlatListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getFlatListUseCase: GetFlatListUseCase) :
    ViewModel() {
    private var _state: MutableStateFlow<Home.UiState> = MutableStateFlow(Home.UiState())
    val state: StateFlow<Home.UiState> = _state.asStateFlow()

    fun onEvent(event: Home.Event) {
        when (event) {
            is Home.Event.OnChange -> _state.update { it.copy(word = event.text) }
            is Home.Event.OnSearchClick -> callApi(event.text)
            Home.Event.OnClear -> _state.update { it.copy(word = "") }
            Home.Event.OnCloseDialog -> _state.update { it.copy(overlayState = Home.OverlayState.None) }
        }
    }

    private fun callApi(word: String) {
        viewModelScope.launch {
            getFlatListUseCase(word)
                .onStart { _state.update { it.copy(overlayState = Home.OverlayState.Loading) } }
                .catch {
                    _state.update { it.copy(overlayState = Home.OverlayState.Error) }
                }
                .onEach { response ->
                    _state.update {
                        it.copy(
                            overlayState = Home.OverlayState.None,
                            lines = response
                        )
                    }
                }.collect()
        }
    }
}
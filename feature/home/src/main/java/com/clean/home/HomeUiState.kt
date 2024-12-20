package com.clean.home

import androidx.compose.runtime.Stable
import com.clean.domain.model.Line
import com.clean.domain.model.Word

object Home {
    @Stable
    data class UiState(
        val word: String = "word",
//        val words: List<Word> = listOf(),
        val lines: List<Line> = listOf(),
        val overlayState: OverlayState = OverlayState.None
    )

    sealed class OverlayState {
        data object Loading : OverlayState()
        data object Error : OverlayState()
        data object None : OverlayState()
    }

    sealed class Event {
        data class OnChange(val text: String) : Event()
        data class OnSearchClick(val text: String) : Event()
        data object OnClear : Event()
        data object OnCloseDialog : Event()
    }
}

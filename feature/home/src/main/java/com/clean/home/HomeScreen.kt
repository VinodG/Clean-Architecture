package com.clean.home

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.clean.designsystem.CaTheme
import com.clean.designsystem.component.CaSearchBox


@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    HomeScreenContent(state, viewModel::onEvent)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenContent(
    state: Home.UiState,
    onEvent: (Home.Event) -> Unit
) {
    Column(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
        TopAppBar(
            title = {
                Text(
                    text = stringResource(R.string.title_home),
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.primary
                )
            },
            colors = TopAppBarDefaults.topAppBarColors()
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Spacer(Modifier.height(24.dp))
                CaSearchBox(
                    value = state.word,
                    placeHolder = stringResource(R.string.hint_enter_a_word),
                    labelText = stringResource(R.string.hint_enter_a_word),
                    onDoneClick = { onEvent(Home.Event.OnSearchClick(state.word)) },
                    onValueChange = { onEvent(Home.Event.OnChange(it)) },
                    onClearClick = { onEvent(Home.Event.OnClear) }
                )
                Spacer(Modifier.height(24.dp))
                ResultSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1.0f),
                    lines = state.lines
                )
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        onEvent(Home.Event.OnSearchClick(state.word))
                    }) {
                    Text(stringResource(R.string.button_search))
                }
            }
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                HandleOverlayState(state.overlayState, onDismissDialog = {
                    onEvent(Home.Event.OnCloseDialog)
                })
            }
        }

    }
}


@Composable
private fun HandleOverlayState(
    overlayState: Home.OverlayState,
    onDismissDialog: () -> Unit
) {
    when (overlayState) {
        Home.OverlayState.Error -> CaDialog(
            text = stringResource(R.string.error_something_went_wrong),
            onDismissDialog = onDismissDialog
        )

        Home.OverlayState.Loading -> CircularProgressIndicator(
            modifier = Modifier
                .size(100.dp),
            strokeWidth = 12.dp
        )

        Home.OverlayState.None -> Unit
    }


}


@Preview(showSystemUi = true)
@Composable
private fun HomePreview() {
    CaTheme {
        HomeScreenContent(Home.UiState()) {
        }
    }
}

@Preview(showSystemUi = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun HomePreviewDark() {
    CaTheme {
        HomeScreenContent(Home.UiState()) {
        }
    }
}



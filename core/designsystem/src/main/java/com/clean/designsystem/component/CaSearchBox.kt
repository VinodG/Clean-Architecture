package com.clean.designsystem.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import com.clean.designsystem.R

@Composable
fun CaSearchBox(
    value: String,
    placeHolder: String,
    labelText: String,
    onValueChange: (String) -> Unit,
    onDoneClick: () -> Unit,
    onClearClick: () -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(placeHolder) },
        modifier = Modifier.fillMaxWidth(),
        label = { Text(labelText) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        keyboardActions = KeyboardActions(
            onDone = { onDoneClick() }
        ),
        trailingIcon = {
            IconButton(onClick = onClearClick) {
                androidx.compose.material3.Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = stringResource(R.string.icon_close),
                    tint = MaterialTheme.colorScheme.onSurface,
                )
            }
        }
    )
}
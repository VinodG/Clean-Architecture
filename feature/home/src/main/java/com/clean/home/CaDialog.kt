package com.clean.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun CaDialog(text: String, onDismissDialog: () -> Unit) {
    Dialog(onDismissRequest = onDismissDialog, DialogProperties()) {
        Column(
            Modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(12.dp))
                .background(MaterialTheme.colorScheme.background)
                .padding(20.dp)
        ) {
            Spacer(Modifier.height(32.dp))
            Text(
                text,
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(Modifier.height(32.dp))
            Button(modifier = Modifier.align(Alignment.End), onClick = onDismissDialog) {
                Text(stringResource(R.string.button_close))
            }
        }
    }
}

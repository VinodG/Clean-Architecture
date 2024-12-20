package com.clean.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.clean.domain.model.Line

@Composable
fun ResultSection(modifier: Modifier, lines: List<Line>) {
    LazyColumn(
        modifier = modifier
    ) {
        lines.forEach {
            when (it.type) {
                Line.Type.Word -> item {
                    Text(it.line, fontWeight = FontWeight.SemiBold)
                }

                Line.Type.Phonetic -> item {
                    Text(it.line, fontStyle = FontStyle.Italic)
                }

                Line.Type.Definition -> item {
                    Text(
                        "def: ${it.line}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }

                Line.Type.Example -> item {
                    Text(
                        "eg: ${it.line}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.secondary,
                        fontStyle = FontStyle.Italic
                    )
                }

                Line.Type.PartsOfSpeech -> item {
                    Text(
                        text = it.line,
                        modifier = Modifier
                            .padding(top = 16.dp, bottom = 8.dp)
                            .background(
                                color = MaterialTheme.colorScheme.tertiaryContainer,
                                shape = RoundedCornerShape(4.dp)
                            )
                            .padding(horizontal = 8.dp)
                    )
                }
            }
        }
    }
}
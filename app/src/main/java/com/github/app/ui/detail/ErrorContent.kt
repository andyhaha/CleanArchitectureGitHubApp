package com.github.app.ui.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ErrorContent(
    innerPadding: PaddingValues = PaddingValues(),
    onRetry: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .clickable {
                onRetry()
            },
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            contentAlignment = Alignment.Center
        ) {
            ErrorText()
        }
    }
}

@Composable
fun ErrorText() {
    val annotatedText = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = Color.DarkGray,
                fontStyle = MaterialTheme.typography.displayMedium.fontStyle
            )
        ) {
            append("Whoops... Something went wrong.\nPlease")
        }
        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.error)) {
            append(" Click the screen ")
        }
        withStyle(
            style = SpanStyle(
                color = Color.DarkGray,
                fontStyle = MaterialTheme.typography.displayMedium.fontStyle
            )
        ) {
            append("to try again.")
        }
    }
    Text(text = annotatedText)
}

@Preview(showBackground = true)
@Composable
fun PreviewErrorContent() {
    ErrorContent()
}

@Preview(showBackground = true)
@Composable
fun PreviewErrorText() {
    ErrorText()
}
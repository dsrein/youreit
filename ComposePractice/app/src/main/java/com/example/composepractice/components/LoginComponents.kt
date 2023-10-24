package com.example.composepractice.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ClickableLoginTextComponent(
    questionText: String,
    navigationText: String,
    onTextSelected: (String) -> Unit) {
    val annotatedString = buildAnnotatedString {
        append("$questionText ")
        withStyle(style = SpanStyle(MaterialTheme.colors.primary)) {
            pushStringAnnotation(tag = navigationText, annotation = navigationText)
            append(navigationText)
        }
    }
    ClickableText(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 80.dp),
        style = TextStyle(
            fontSize = 21.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center,
        ),
        text = annotatedString,
        onClick = { offset ->
            annotatedString.getStringAnnotations(offset, offset)
                .firstOrNull()?.let { span ->
                    if (span.item == navigationText) {
                        onTextSelected(span.item)
                    }
                }
        })
}

package com.code4galaxy.compose_cards.component

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

/** Formats up to 19 digits as groups of four without mutating the source input. */
val CardNumberFilter = VisualTransformation { text ->
    val digits = text.text.filter(Char::isDigit).take(19)
    val formatted = digits.chunked(4).joinToString(" ")

    TransformedText(
        text = AnnotatedString(formatted),
        offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                val safeOffset = offset.coerceIn(0, digits.length)
                return (safeOffset + ((safeOffset - 1).coerceAtLeast(0) / 4))
                    .coerceAtMost(formatted.length)
            }

            override fun transformedToOriginal(offset: Int): Int = formatted
                .take(offset.coerceIn(0, formatted.length))
                .count(Char::isDigit)
        },
    )
}

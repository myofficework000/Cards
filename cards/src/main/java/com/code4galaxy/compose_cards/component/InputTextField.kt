package com.code4galaxy.compose_cards.component

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation

/** A Material 3 text field used by [com.code4galaxy.compose_cards.CardDetails]. */
@Composable
fun InputTextField(
    value: String,
    label: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChanged,
        label = { Text(label) },
        modifier = modifier,
        visualTransformation = visualTransformation,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        singleLine = true,
        maxLines = 1,
    )
}

/** Backwards-compatible overload for callers that currently use [TextFieldValue]. */
@Composable
fun InputTextField(
    textFieldValue: TextFieldValue,
    label: String,
    onTextChanged: (TextFieldValue) -> Unit,
    modifier: Modifier = Modifier,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardType: KeyboardType = KeyboardType.Text,
) {
    InputTextField(
        value = textFieldValue.text,
        label = label,
        onValueChanged = { onTextChanged(TextFieldValue(it)) },
        modifier = modifier,
        visualTransformation = visualTransformation,
        keyboardType = keyboardType,
    )
}

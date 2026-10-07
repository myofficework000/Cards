package com.code4galaxy.compose_cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.code4galaxy.compose_cards.component.CardNumberFilter
import com.code4galaxy.compose_cards.component.InputTextField
import com.code4galaxy.compose_cards.ui.CreditCard
import com.code4galaxy.compose_cards.util.Card
import com.code4galaxy.compose_cards.util.detectCard

/** Non-sensitive values surfaced when the user taps Save. Do not persist raw PAN/CVV. */
data class CardInput(
    val cardNumber: String,
    val holderName: String,
    val expiryDate: String,
    val cvv: String,
)

/**
 * A complete, stateful Material 3 card form suitable for a demo or a lightweight checkout UI.
 * The caller owns submission through [onSave]; this library does not process payments.
 */
@Composable
fun CardDetails(
    modifier: Modifier = Modifier,
    onSave: (CardInput) -> Unit = {},
) {
    var cardNumber by remember { mutableStateOf("") }
    var cardHolderName by remember { mutableStateOf("") }
    var expiryDate by remember { mutableStateOf("") }
    var cardCvv by remember { mutableStateOf("") }
    var cvvFocused by remember { mutableStateOf(false) }
    val brand = detectCard(cardNumber)
    val maxCvvLength = if (brand == Card.AmericanExpress) 4 else 3

    Column(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
            .imePadding()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
    ) {
        CreditCard(
            cardNumber = cardNumber,
            holderName = cardHolderName,
            expiryDate = expiryDate,
            cardCvv = cardCvv,
            isBackVisible = cvvFocused,
        )

        Text("Card details", style = MaterialTheme.typography.titleLarge)
        InputTextField(
            value = cardNumber,
            label = "Card number",
            onValueChanged = { cardNumber = it.filter(Char::isDigit).take(19) },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = CardNumberFilter,
            keyboardType = KeyboardType.Number,
        )
        InputTextField(
            value = cardHolderName,
            label = "Cardholder name",
            onValueChanged = { cardHolderName = it.take(40) },
            modifier = Modifier.fillMaxWidth(),
            keyboardType = KeyboardType.Text,
        )
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            InputTextField(
                value = expiryDate,
                label = "Expiry (MMYY)",
                onValueChanged = { expiryDate = it.filter(Char::isDigit).take(4) },
                modifier = Modifier.weight(1f),
                keyboardType = KeyboardType.Number,
            )
            InputTextField(
                value = cardCvv,
                label = if (maxCvvLength == 4) "CID" else "CVV",
                onValueChanged = { cardCvv = it.filter(Char::isDigit).take(maxCvvLength) },
                modifier = Modifier.weight(1f).onFocusChanged { cvvFocused = it.isFocused },
                keyboardType = KeyboardType.NumberPassword,
                imeAction = ImeAction.Done,
            )
        }
        Button(
            onClick = {
                onSave(CardInput(cardNumber, cardHolderName, expiryDate, cardCvv))
            },
            modifier = Modifier.fillMaxWidth().padding(top = 6.dp),
            enabled = cardNumber.length >= 12 && cardHolderName.isNotBlank() && expiryDate.length == 4 && cardCvv.length == maxCvvLength,
        ) {
            Text("Save card details", modifier = Modifier.padding(vertical = 5.dp))
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF6F3FF)
@Composable
private fun CardDetailsPreview() = CardDetails()

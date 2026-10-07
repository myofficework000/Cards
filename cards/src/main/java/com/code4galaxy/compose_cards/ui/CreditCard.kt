package com.code4galaxy.compose_cards.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.code4galaxy.compose_cards.util.Card
import com.code4galaxy.compose_cards.util.detectCard

/**
 * A Material 3 payment-card preview. The back is shown while the CVV field is focused.
 * This composable displays input only; it never stores or submits card data.
 */
@Composable
fun CreditCard(
    cardNumber: String,
    holderName: String,
    expiryDate: String,
    cardCvv: String,
    modifier: Modifier = Modifier,
    isBackVisible: Boolean = false,
) {
    val card = detectCard(cardNumber)
    val rotation by animateFloatAsState(
        targetValue = if (isBackVisible) 180f else 0f,
        animationSpec = tween(durationMillis = 450),
        label = "cardRotation",
    )
    val visibleNumber = cardNumber.filter(Char::isDigit).take(19)
        .ifBlank { "••••••••••••••••" }
        .chunked(4)
        .joinToString(" ")
    val visibleExpiry = expiryDate.filter(Char::isDigit).take(4)
        .chunked(2)
        .joinToString(" / ")
        .ifBlank { "MM / YY" }

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .widthIn(max = 520.dp)
            .height(212.dp)
            .graphicsLayer {
                rotationY = rotation
                cameraDistance = 12f * density
            }
            .semantics { contentDescription = "${card.displayName} card preview" },
        shape = RoundedCornerShape(24.dp),
        color = card.color,
        contentColor = Color.White,
        shadowElevation = 10.dp,
    ) {
        Box(modifier = Modifier.fillMaxSize().padding(20.dp)) {
            AnimatedVisibility(visible = !isBackVisible) {
                Column(modifier = Modifier.fillMaxSize()) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top,
                    ) {
                        Surface(
                            modifier = Modifier.widthIn(min = 46.dp),
                            shape = RoundedCornerShape(8.dp),
                            color = Color.White.copy(alpha = 0.18f),
                        ) {
                            Text(
                                text = "CHIP",
                                style = MaterialTheme.typography.labelSmall,
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 7.dp),
                            )
                        }
                        card.image?.let { image ->
                            Image(
                                painter = painterResource(image),
                                contentDescription = "${card.displayName} logo",
                            )
                        }
                    }
                    Spacer(Modifier.weight(1f))
                    Text(
                        text = visibleNumber,
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.SemiBold,
                        ),
                        maxLines = 1,
                    )
                    Spacer(Modifier.height(18.dp))
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        CardLabel(label = "CARDHOLDER", value = holderName.ifBlank { "YOUR NAME" })
                        CardLabel(label = "EXPIRES", value = visibleExpiry, alignEnd = true)
                    }
                }
            }

            AnimatedVisibility(visible = isBackVisible) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .graphicsLayer { rotationY = 180f },
                ) {
                    Spacer(
                        Modifier
                            .fillMaxWidth()
                            .height(46.dp)
                            .background(Color.Black.copy(alpha = 0.82f)),
                    )
                    Spacer(Modifier.height(22.dp))
                    Text("AUTHORIZED SIGNATURE", style = MaterialTheme.typography.labelSmall)
                    Surface(
                        modifier = Modifier.fillMaxWidth().padding(top = 5.dp),
                        color = Color.White,
                        shape = RoundedCornerShape(6.dp),
                    ) {
                        Text(
                            text = cardCvv.filter(Char::isDigit).take(if (card == Card.AmericanExpress) 4 else 3).padEnd(3, '•'),
                            color = Color.Black,
                            style = MaterialTheme.typography.titleMedium.copy(fontFamily = FontFamily.Monospace),
                            modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp),
                        )
                    }
                    Spacer(Modifier.weight(1f))
                    Text(card.displayName.uppercase(), style = MaterialTheme.typography.labelLarge)
                }
            }
        }
    }
}

@Composable
private fun CardLabel(label: String, value: String, alignEnd: Boolean = false) {
    Column(horizontalAlignment = if (alignEnd) Alignment.End else Alignment.Start) {
        Text(label, style = MaterialTheme.typography.labelSmall, color = Color.White.copy(alpha = 0.72f))
        Text(value.uppercase(), style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Medium, maxLines = 1)
    }
}

/** Compatibility overload for existing integrations using [TextFieldValue]. */
@Composable
fun CreditCard(
    cardNumber: TextFieldValue,
    holderName: TextFieldValue,
    expiryDate: TextFieldValue,
    cardCVV: TextFieldValue,
) = CreditCard(cardNumber.text, holderName.text, expiryDate.text, cardCVV.text)

@Preview(showBackground = true, backgroundColor = 0xFFF6F3FF)
@Composable
private fun CreditCardPreview() {
    CreditCard("4111111111111111", "Taylor Morgan", "1228", "123")
}

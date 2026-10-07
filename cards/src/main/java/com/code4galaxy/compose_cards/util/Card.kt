package com.code4galaxy.compose_cards.util

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.code4galaxy.compose_cards.R

/** Supported payment-card brands and their presentation metadata. */
enum class Card(
    @param:DrawableRes val image: Int?,
    val color: Color,
    val displayName: String,
) {
    None(null, Color(0xFF263238), "Payment card"),
    Visa(R.drawable.ic_visa, Color(0xFF174EA6), "Visa"),
    Mastercard(R.drawable.ic_mastercard, Color(0xFF00695C), "Mastercard"),
    RuPay(R.drawable.rupay_logo, Color(0xFF5E35B1), "RuPay"),
    AmericanExpress(R.drawable.amex_logo, Color(0xFF6A1B9A), "American Express"),
    Maestro(R.drawable.maestro, Color(0xFF1565C0), "Maestro"),
    DinersClub(R.drawable.diner_clubs, Color(0xFFB71C1C), "Diners Club"),
}

/** Detects a card brand from an incomplete or complete Primary Account Number. */
fun detectCard(number: String): Card {
    val digits = number.filter(Char::isDigit)
    return when {
        digits.startsWith("4") -> Card.Visa
        digits.take(2).toIntOrNull() in 51..55 || digits.take(4).toIntOrNull() in 2221..2720 -> Card.Mastercard
        digits.startsWith("34") || digits.startsWith("37") -> Card.AmericanExpress
        digits.startsWith("36") || digits.startsWith("38") || digits.startsWith("30") -> Card.DinersClub
        digits.startsWith("60") || digits.startsWith("65") || digits.startsWith("81") || digits.startsWith("82") -> Card.RuPay
        digits.startsWith("50") || digits.startsWith("56") || digits.startsWith("57") || digits.startsWith("58") || digits.startsWith("63") || digits.startsWith("67") -> Card.Maestro
        else -> Card.None
    }
}

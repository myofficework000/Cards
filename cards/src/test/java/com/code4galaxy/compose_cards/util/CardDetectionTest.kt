package com.code4galaxy.compose_cards.util

import org.junit.Assert.assertEquals
import org.junit.Test

class CardDetectionTest {
    @Test
    fun detectsSupportedCardPrefixes() {
        assertEquals(Card.Visa, detectCard("4111 1111 1111 1111"))
        assertEquals(Card.Mastercard, detectCard("5555 5555 5555 4444"))
        assertEquals(Card.Mastercard, detectCard("2221000000000009"))
        assertEquals(Card.AmericanExpress, detectCard("378282246310005"))
        assertEquals(Card.DinersClub, detectCard("30569309025904"))
        assertEquals(Card.RuPay, detectCard("6070010000000000"))
        assertEquals(Card.Maestro, detectCard("6759649826438453"))
    }

    @Test
    fun returnsNoneForAnUnknownPrefix() {
        assertEquals(Card.None, detectCard("990000"))
    }
}

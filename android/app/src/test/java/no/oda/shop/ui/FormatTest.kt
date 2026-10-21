package no.oda.shop.ui

import org.junit.Assert.assertEquals
import org.junit.Test

class FormatTest {
    @Test
    fun `formats øre as kroner with a decimal comma`() {
        assertEquals("39,90 kr", formatOre(3990))
    }

    @Test
    fun `keeps two decimals on whole kroner`() {
        assertEquals("29,00 kr", formatOre(2900))
    }
}

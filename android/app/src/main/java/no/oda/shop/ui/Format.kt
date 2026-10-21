package no.oda.shop.ui

import java.text.NumberFormat
import java.util.Locale

private val norwegianKroner = NumberFormat.getNumberInstance(Locale.forLanguageTag("nb-NO")).apply {
    minimumFractionDigits = 2
    maximumFractionDigits = 2
}

/** 3990 -> "39,90 kr". Money is øre everywhere; format only when you display it. */
fun formatOre(ore: Int): String = "${norwegianKroner.format(ore / 100.0)} kr"

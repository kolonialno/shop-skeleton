package no.oda.shop.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// A small slice of Oda's design tokens. Use them, add to them, change them.
object ShopColors {
    val PurpleDarkest = Color(0xFF17001E)
    val PurpleDarker = Color(0xFF1D1429)
    val PurpleDark = Color(0xFF261D35)
    val PurpleMid = Color(0xFF433D57)
    val PurpleLight = Color(0xFF534C67)

    val Orange = Color(0xFFFFA424)
    val OrangeDark = Color(0xFFE89B1B)

    val White = Color(0xFFFFFFFF)
    val Beige = Color(0xFFD8D2CB)
    val Coral = Color(0xFFFF6F59)
}

private val colorScheme = darkColorScheme(
    primary = ShopColors.Orange,
    onPrimary = ShopColors.PurpleDarkest,
    background = ShopColors.PurpleDarkest,
    onBackground = ShopColors.White,
    surface = ShopColors.PurpleDark,
    onSurface = ShopColors.White,
    surfaceContainer = ShopColors.PurpleDarker,
    onSurfaceVariant = ShopColors.Beige,
    outline = ShopColors.PurpleLight,
    error = ShopColors.Coral,
)

@Composable
fun ShopTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = colorScheme, content = content)
}

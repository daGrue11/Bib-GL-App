package de.bibgl.konto.ui

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.Surface
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val LightColors = lightColorScheme(
    primary = Color(0xFF1B5E4A),
    onPrimary = Color.White,
    primaryContainer = Color(0xFFA8F2D5),
    onPrimaryContainer = Color(0xFF002014),
    secondary = Color(0xFF4B635A),
    tertiary = Color(0xFF3F6375),
    surface = Color(0xFFFBFDF9),
    background = Color(0xFFFBFDF9),
    error = Color(0xFFBA1A1A),
)

private val DarkColors = darkColorScheme(
    primary = Color(0xFF8CD6BA),
    onPrimary = Color(0xFF003828),
    primaryContainer = Color(0xFF00513B),
    onPrimaryContainer = Color(0xFFA8F2D5),
    secondary = Color(0xFFB2CCC0),
    tertiary = Color(0xFFA7CBDF),
    surface = Color(0xFF191C1B),
    background = Color(0xFF191C1B),
    error = Color(0xFFFFB4AB),
)

/** Ampelfarben fuer den Frist-Countdown - bewusst unabhaengig vom Theme-Akzent. */
object DueColors {
    val overdue = Color(0xFFBA1A1A)
    val overdueDark = Color(0xFFFF8A80)
    val urgent = Color(0xFFB45309)
    val urgentDark = Color(0xFFFFB74D)
    val soon = Color(0xFF7A6300)
    val soonDark = Color(0xFFE8C547)
    val ok = Color(0xFF1B6E4A)
    val okDark = Color(0xFF7FD1AC)
}

@Composable
fun BibTheme(content: @Composable () -> Unit) {
    val dark = isSystemInDarkTheme()
    val context = LocalContext.current
    val colors = when {
        // Ab Android 12 passt sich die App an das Wallpaper-Farbschema an.
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.S ->
            if (dark) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        dark -> DarkColors
        else -> LightColors
    }
    MaterialTheme(colorScheme = colors) {
        // Die Surface ist nicht bloss Deko: sie setzt LocalContentColor auf
        // onBackground. Ohne sie faellt jeder Text ohne eigene Farbangabe auf
        // Schwarz zurueck - im Dunkelmodus also schwarz auf schwarz. Bildschirme
        // mit Scaffold fiel das nicht auf, weil Scaffold selbst eine Surface mitbringt;
        // der Anmeldebildschirm hat keine und war deshalb unlesbar.
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
            content = content,
        )
    }
}

package wottrich.github.io.take.notes.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember

@Composable
fun TakeNotesTheme(
    colors: TakeNotesColors = darkColors(),
    content: @Composable () -> Unit
) {
    val rememberedColors = remember {
        colors.copy()
    }.apply {
        updateColorsFrom(colors)
    }
    CompositionLocalProvider(
        LocalTakeNotesColors provides rememberedColors
    ) {
        MaterialTheme(
            colorScheme = LocalTakeNotesColors.current.toMaterialTheme(),
            typography = MaterialTheme.typography,
            content = content
        )
    }
}

@Composable
fun TakeNotesColors.toMaterialTheme(): ColorScheme {
    return darkColorScheme().copy(
        primary = primary,
        onPrimary = onPrimary,
        primaryContainer = primaryVariant,
        onPrimaryContainer = onPrimary,
        secondary = secondary,
        onSecondary = onSecondary,
        secondaryContainer = secondaryVariant,
        onSecondaryContainer = onSecondary,
        background = background,
        onBackground = onBackground,
        surface = surface,
        onSurface = onSurface,
        error = error,
        onError = onError,
    )
}
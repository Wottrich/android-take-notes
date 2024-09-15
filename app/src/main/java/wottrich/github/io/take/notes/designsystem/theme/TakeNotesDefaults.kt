package wottrich.github.io.take.notes.designsystem.theme

import androidx.compose.ui.graphics.Color

fun darkColors(
    primary: Color = ColorsDarkPallet.primary,
    primaryVariant: Color = ColorsDarkPallet.primaryVariant,
    secondary: Color = ColorsDarkPallet.secondary,
    secondaryVariant: Color = ColorsDarkPallet.secondaryVariant,
    background: Color = ColorsDarkPallet.background,
    surface: Color = ColorsDarkPallet.surface,
    error: Color = ColorsDarkPallet.error,
    onPrimary: Color = ColorsDarkPallet.onPrimary,
    onSecondary: Color = ColorsDarkPallet.onSecondary,
    onBackground: Color = ColorsDarkPallet.onBackground,
    onSurface: Color = ColorsDarkPallet.onSurface,
    onError: Color = ColorsDarkPallet.onError
): TakeNotesColors = TakeNotesColors(
    primary,
    primaryVariant,
    secondary,
    secondaryVariant,
    background,
    surface,
    error,
    onPrimary,
    onSecondary,
    onBackground,
    onSurface,
    onError,
    isLight = false
)
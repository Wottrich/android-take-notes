package wottrich.github.io.take.notes.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable

object TakeNotesTheme {

    val colors: TakeNotesColors
    @Composable
    @ReadOnlyComposable
    get() = LocalTakeNotesColors.current

}
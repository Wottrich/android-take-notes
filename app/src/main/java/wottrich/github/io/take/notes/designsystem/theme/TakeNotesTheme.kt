package wottrich.github.io.take.notes.designsystem.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable

object TakeNotesTheme {

    val colors: TakeNotesColors
    @Composable
    @ReadOnlyComposable
    get() = LocalTakeNotesColors.current

}
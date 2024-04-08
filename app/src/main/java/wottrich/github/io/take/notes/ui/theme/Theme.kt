package wottrich.github.io.take.notes.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun TakeNotesTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = TakeNotesPallet.DarkColorScheme,
        typography = MaterialTheme.typography,
        content = content
    )
}
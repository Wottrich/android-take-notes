package wottrich.github.io.take.notes.writenote.presentation.viewmodel

import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import wottrich.github.io.take.notes.writenote.presentation.model.Line

data class WriteNoteUiModel(
    val lines: List<Line> = listOf(Line()),
    val lineToRequestFocus: Line? = null
)

class WriteNoteViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(WriteNoteUiModel())
    val uiState = _uiState.asStateFlow()

    fun addNewLine(index: Int) {
        val items = uiState.value.lines
        val nextItem = uiState.value.lines.getOrNull(index + 1)
        val newLine = Line()
        val updatedLines = items.toMutableList().apply {
            if (nextItem != null) {
                add(index + 1, newLine)
            } else {
                add(newLine)
            }
        }
        _uiState.value = uiState.value.copy(
            lines = updatedLines,
            lineToRequestFocus = newLine
        )
    }

    fun deleteLine(index: Int) {
        val items = uiState.value.lines
        val previousItem = items.getOrNull(index - 1)
        val updatedLines = items.toMutableList().apply {
            if (items.getOrNull(index) != null) {
                removeAt(index)
            }
        }
        _uiState.value = uiState.value.copy(
            lines = updatedLines,
            lineToRequestFocus = previousItem
        )
    }

    fun updateLine(index: Int, textFieldValue: TextFieldValue) {
        val items = uiState.value.lines
        val note = items[index]
        val updatedLines = items.toMutableList().apply {
            if (this.getOrNull(index) != null) {
                this[index] = note.copy(text = textFieldValue)
            }
        }
        _uiState.value = uiState.value.copy(lines = updatedLines)
    }

    fun cleanRequestedFocus() {
        _uiState.value = uiState.value.copy(lineToRequestFocus = null)
    }
}
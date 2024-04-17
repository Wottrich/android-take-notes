package wottrich.github.io.take.notes.writenote.presentation.ui

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.NativeKeyEvent
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import wottrich.github.io.take.notes.designsystem.custom.TakeNotesTextField
import wottrich.github.io.take.notes.writenote.presentation.model.Line
import wottrich.github.io.take.notes.writenote.presentation.viewmodel.WriteNoteViewModel
import wottrich.github.io.take.notes.designsystem.theme.TakeNotesTheme

@Composable
fun WriteNoteScreen(
    viewModel: WriteNoteViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
        initializer = { WriteNoteViewModel() }
    )
) {
    val state by viewModel.uiState.collectAsState()
    WriteNoteComponent(
        items = state.lines,
        lineToRequestFocus = state.lineToRequestFocus,
        onAddNewLine = { index ->
            viewModel.addNewLine(index)
        },
        onDeleteLine = { index ->
            viewModel.deleteLine(index)
        },
        onNoteChange = { index, textFieldValue ->
            viewModel.updateLine(index, textFieldValue)
        },
        focusRequested = { viewModel.cleanRequestedFocus() }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun WriteNoteComponent(
    items: List<Line>,
    lineToRequestFocus: Line?,
    onAddNewLine: (Int) -> Unit,
    onDeleteLine: (Int) -> Unit,
    onNoteChange: (Int, TextFieldValue) -> Unit,
    focusRequested: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Take notes")
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxHeight()
                .padding(paddingValues),
            content = {
                itemsIndexed(items) { index, note ->
                    val focusRequester = remember { FocusRequester() }
                    TextFieldItem(
                        textFieldValue = note.text,
                        focusRequester = focusRequester,
                        onTextFieldValueChange = {
                            onNoteChange(index, it)
                        },
                        onAddNewLine = {
                            onAddNewLine(index)
                        },
                        onDeleteLine = {
                            if (items.size > 1) {
                                onDeleteLine(index)
                            }
                        }
                    )
                    SideEffect {
                        if (note === lineToRequestFocus) {
                            focusRequester.requestFocus()
                            focusRequested()
                        }
                    }
                }
            }
        )
    }
}

@Composable
private fun TextFieldItem(
    textFieldValue: TextFieldValue,
    focusRequester: FocusRequester,
    onTextFieldValueChange: (TextFieldValue) -> Unit,
    onAddNewLine: () -> Unit,
    onDeleteLine: () -> Unit
) {
    TakeNotesTextField(
        modifier = Modifier
            .focusRequester(focusRequester)
            .onKeyEvent {
                if (it.nativeKeyEvent.keyCode == NativeKeyEvent.KEYCODE_DEL) {
                    if (textFieldValue.text.isEmpty()) {
                        onDeleteLine()
                    }
                }
                return@onKeyEvent true
            }.fillMaxWidth(),
        value = textFieldValue,
        onValueChange = onTextFieldValueChange,
        placeholder = { Text("Type here...") },
        textStyle = LocalTextStyle.current.copy(color = Color.Unspecified),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(
            onDone = { onAddNewLine() }
        )
    )
}

@Preview
@Composable
fun WriteNoteComponentPreview() {
    TakeNotesTheme {
//        WriteNoteComponent()
    }
}
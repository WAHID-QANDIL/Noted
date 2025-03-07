package org.wahid.noted.feature_note.presentation.add_edit_note_screen.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

@Composable
fun TransparentInputTextField(
    modifier: Modifier = Modifier,
    text:String,
    hint:String,
    isHintVisible:Boolean = true,
    textStyle: TextStyle = TextStyle(),
    singleLine:Boolean,
    onValueChanged:(String)->Unit,
    onFocusedChanged:(FocusState)->Unit
) {
    Box(modifier = modifier) {
        BasicTextField(
            value = text,
            onValueChange = onValueChanged,
            textStyle = textStyle,
            singleLine = singleLine,
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { onFocusedChanged(it) },
            decorationBox = { innerTextField ->
                Box {
                    innerTextField()
                    if (isHintVisible && text.isEmpty()) {
                        Text(
                            text = hint,
                            style = textStyle,
                            color = Color.DarkGray
                        )
                    }
                }
            }
        )
    }
}
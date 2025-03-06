package org.wahid.noted.feature_note.presentation.note_screen.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.graphics.ColorUtils
import org.wahid.noted.feature_note.domain.modle.Note


@Composable
fun NoteItem(
    modifier: Modifier = Modifier,
    note: Note,
    cornerRadiusSize: Dp = 10.dp,
    cutCornerSize: Dp = 30.dp,
    onDeleteClicked: () -> Unit = {},

    ) {
    Box(modifier = modifier) {

        Canvas(modifier = modifier.matchParentSize()) {
            val pathLine = Path().apply {
                lineTo(size.width - cutCornerSize.toPx(), 0f)
                lineTo(size.width, cutCornerSize.toPx())
                lineTo(size.width, size.height)
                lineTo(0f, size.height)
                close()

            }

            clipPath(
                path = pathLine,
            ) {
                drawRoundRect(
                    color = Color(note.color),
                    size = size,
                    cornerRadius = CornerRadius(cornerRadiusSize.toPx())
                )

                drawRoundRect(
                    color = Color(
                        ColorUtils.blendARGB(note.color, 0x00000000, 0.2f)
                    ),
                    topLeft = Offset(size.width - cutCornerSize.toPx(), -100f),
                    size = Size(cutCornerSize.toPx() + 100f, cutCornerSize.toPx() + 100f),
                    cornerRadius = CornerRadius(cornerRadiusSize.toPx())
                )


            }

        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(end = 32.dp)

        ) {

            Text(
                text = note.title,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Spacer(Modifier.height(8.dp))

            Text(
                text = note.content,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 10,
                overflow = TextOverflow.Ellipsis,
            )
        }
        IconButton(
            modifier = Modifier.align(Alignment.BottomEnd),
            onClick = onDeleteClicked
        ) {

            Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete Note")
        }
    }

}


@Preview(showBackground = true)
@Composable
private fun NoteItemPreview() {
    NoteItem(
        modifier = Modifier.size(500.dp,300.dp),
        note = Note(
            title = "This is the note title",
            content = "This is the note content",
            timestamp = 44,
            color = 0x1111111,
            id = 1,
        )
    )

}
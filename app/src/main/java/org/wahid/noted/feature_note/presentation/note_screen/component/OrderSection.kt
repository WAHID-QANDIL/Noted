package org.wahid.noted.feature_note.presentation.note_screen.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.wahid.noted.feature_note.domain.utils.NoteOrder
import org.wahid.noted.feature_note.domain.utils.OrderType

@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    onOrderChanged: (NoteOrder) -> Unit,
) {


    Column(modifier = modifier)
    {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                label = "Title",
                isChecked = noteOrder is NoteOrder.Title,
                whenChecked = { onOrderChanged(NoteOrder.Title(noteOrder.orderType)) }
            )
            Spacer(Modifier.width(8.dp))

            DefaultRadioButton(
                label = "Date",
                isChecked = noteOrder is NoteOrder.Date,
                whenChecked = { onOrderChanged(NoteOrder.Date(noteOrder.orderType)) }
            )
            Spacer(Modifier.width(8.dp))
            DefaultRadioButton(
                label = "Color",
                isChecked = noteOrder is NoteOrder.Color,
                whenChecked = { onOrderChanged(NoteOrder.Color(noteOrder.orderType)) }
            )

        }
        Spacer(Modifier.height(16.dp))
        Row(modifier = modifier) {

            DefaultRadioButton(
                label = "Ascending",
                isChecked = noteOrder.orderType is OrderType.Ascending,
                whenChecked = { onOrderChanged(noteOrder.copy(OrderType.Ascending)) }
            )
            Spacer(Modifier.width(8.dp))

            DefaultRadioButton(
                label = "Descending",
                isChecked = noteOrder.orderType is OrderType.Descending,
                whenChecked = { onOrderChanged(noteOrder.copy(OrderType.Descending)) }
            )
        }


    }


}

@Preview
@Composable
private fun OrderSectionPreview() {
    OrderSection(
        onOrderChanged = {}
    )

}
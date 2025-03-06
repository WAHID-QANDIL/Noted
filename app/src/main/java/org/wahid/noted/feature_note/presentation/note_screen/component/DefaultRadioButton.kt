package org.wahid.noted.feature_note.presentation.note_screen.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun DefaultRadioButton(
    modifier: Modifier = Modifier,
    label:String,
    isChecked:Boolean = false,
    whenChecked:()->Unit
) {

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    )
    {
        RadioButton(
            selected = isChecked,
            onClick = whenChecked,
            colors = RadioButtonDefaults.colors(
                selectedColor = MaterialTheme.colorScheme.primary,
                unselectedColor = MaterialTheme.colorScheme.onBackground
            )
        )
        Spacer(Modifier.width(8.dp))
        Text(text = label, style = MaterialTheme.typography.bodyLarge)
    }

}

@Preview(showBackground = true)
@Composable
private fun DefaultRadioButtonPreview() {
    DefaultRadioButton(
        modifier = Modifier,
        label = "Title",
        isChecked = false
    ){}
}
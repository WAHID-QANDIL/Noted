package org.wahid.noted.feature_note.domain.modle

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.wahid.noted.ui.theme.Beige
import org.wahid.noted.ui.theme.DarkSage
import org.wahid.noted.ui.theme.Orange
import org.wahid.noted.ui.theme.Sage


@Entity
data class Note (
    val title:String,
    val content:String,
    val timestamp: Long,
    val color:Int,
    @PrimaryKey val id:Int? = null
)
{
    companion object{
        val colors = listOf(
            DarkSage,
            Sage,
            Orange,
            Beige
        )

        class InvalidNoteException(message:String):Exception(message)

    }
}
package org.wahid.noted.feature_note.domain.use_case

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.wahid.noted.feature_note.domain.modle.Note
import org.wahid.noted.feature_note.domain.repository.NoteRepository
import org.wahid.noted.feature_note.domain.utils.NoteOrder
import org.wahid.noted.feature_note.domain.utils.OrderType

class GetAllNotes(private val repository: NoteRepository) {

    operator fun invoke(noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending)):Flow<List<Note>>
    {
        return repository.getAllNotes().map { notes->

            when(noteOrder.orderType)
            {
                is OrderType.Ascending ->{
                    when(noteOrder){
                        is NoteOrder.Title-> notes.sortedBy { it.title.lowercase() }
                        is NoteOrder.Date-> notes.sortedBy { it.timestamp }
                        is NoteOrder.Color-> notes.sortedBy { it.color }
                    }

                }

                is OrderType.Descending ->{
                    when(noteOrder){
                    is NoteOrder.Title-> notes.sortedByDescending { it.title.lowercase() }
                    is NoteOrder.Date-> notes.sortedByDescending { it.timestamp }
                    is NoteOrder.Color-> notes.sortedByDescending { it.color }
                }}

            }




        }
    }

}
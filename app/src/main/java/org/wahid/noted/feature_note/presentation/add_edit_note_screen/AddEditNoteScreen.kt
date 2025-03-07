package org.wahid.noted.feature_note.presentation.add_edit_note_screen

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.MaterialTheme
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.wahid.noted.feature_note.domain.modle.Note
import org.wahid.noted.feature_note.presentation.add_edit_note_screen.component.TransparentInputTextField

@Composable
fun AddEditNoteScreen(
    navController: NavController,
    viewModel: AddEditNoteViewModel = hiltViewModel(),
    noteColor: Int,
) {
    val noteTitleState = viewModel.noteTitleState.value
    val noteContentState = viewModel.noteContentState.value
    val scaffoldState = rememberScaffoldState()

    val noteBackGroundAnimatibleColor = remember {
        Animatable(
            Color(
                if (noteColor != -1) noteColor else viewModel.noteColorState.value
            )
        )

    }

    val coroutineScope = rememberCoroutineScope()


    LaunchedEffect(key1 = true){
        viewModel.eventFlow.collectLatest {event->

            when(event){
                is AddEditNoteViewModel.UiEvent.SaveNote->{
                    navController.navigateUp()
                }
                is AddEditNoteViewModel.UiEvent.ShowSnackBar->{
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )


                }
            }
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { viewModel.onEvent(AddEditNoteEvent.SaveNote) },
                Modifier.background(MaterialTheme.colors.primary)
            ) {
                Icon(Icons.Filled.Check, contentDescription = "Save Note")
            }
        },
        scaffoldState = scaffoldState
    )
    {
        val padding = it
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(noteBackGroundAnimatibleColor.value)
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Note.colors.forEach { color ->
                    val colorInt = color.toArgb()
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .shadow(50.dp, CircleShape)
                            .clip(CircleShape)
                            .background(color)
                            .border(
                                width = 3.dp,
                                color = if (viewModel.noteColorState.value == colorInt) {
                                    Color.Black
                                } else {
                                    Color.Transparent
                                },
                                shape = CircleShape
                            )
                            .clickable {
                                coroutineScope.launch {
                                    noteBackGroundAnimatibleColor.animateTo(
                                        targetValue = Color(colorInt),
                                        animationSpec = tween(
                                            durationMillis = 500
                                        )
                                    )
                                }
                                viewModel.onEvent(AddEditNoteEvent.ChangeColor(colorInt))
                            }
                    )
                }


            }
            Spacer(Modifier.height(16.dp))
            
            TransparentInputTextField(
                text = noteTitleState.content,
                hint = noteTitleState.title,
                isHintVisible = noteTitleState.isHintVisible,
                onValueChanged = {
                    viewModel.onEvent(AddEditNoteEvent.EnteredTitle(it))
                },
                onFocusedChanged =
                {
                    viewModel.onEvent(AddEditNoteEvent.ChangeTitleFocus(it))
                },
                singleLine = true,

            )

            Spacer(Modifier.height(16.dp))

            TransparentInputTextField(
                text = noteContentState.content,
                hint = noteContentState.title,
                isHintVisible = noteContentState.isHintVisible,
                onValueChanged = {
                    viewModel.onEvent(AddEditNoteEvent.EnteredContent(it))
                },
                onFocusedChanged =
                {
                    viewModel.onEvent(AddEditNoteEvent.ChangeContentFocus(it))
                },
                singleLine = true,
                textStyle = MaterialTheme.typography.body1,
                modifier = Modifier.fillMaxHeight()

                )

        }

    }


}
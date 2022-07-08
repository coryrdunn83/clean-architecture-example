package com.example.cleanarchitectureexample.presentation.note_details

import androidx.activity.compose.BackHandler
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.cleanarchitectureexample.R
import com.example.cleanarchitectureexample.domain.model.Note
import com.example.cleanarchitectureexample.presentation.common_components.CDTextField
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun NoteDetailsScreen(
    viewModel: NoteDetailsViewModel = hiltViewModel(),
    navController: NavController,
    noteColor: Int
) {

    val titleState = viewModel.noteTitleState.value
    val contentState = viewModel.noteContentState.value
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    val noteBackgroundAnimatable = remember {
        Animatable(
            Color(if (noteColor != -1) noteColor else viewModel.noteColorState.value)
        )
    }

    BackHandler(enabled = true) {
        navController.navigateUp()
    }

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when(event) {
                is NoteDetailsViewModel.UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(message = event.message)
                }
                is NoteDetailsViewModel.UiEvent.SaveNote -> {
                    navController.navigateUp()
                }
            }
        }
    }
    
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { viewModel.onEvent(NoteDetailsEvent.SaveNote) },
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_save_24),
                    contentDescription = "Save"
                )
            }
        },
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(noteBackgroundAnimatable.value)
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Note.noteColors.forEach { color ->
                    val colorInt = color.toArgb()
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .shadow(15.dp, CircleShape)
                            .clip(CircleShape)
                            .background(color)
                            .border(
                                width = 3.dp,
                                color = if (viewModel.noteColorState.value == colorInt) {
                                    Color.Black
                                } else Color.Transparent,
                                shape = CircleShape
                            )
                            .clickable {
                                coroutineScope.launch {
                                    noteBackgroundAnimatable.animateTo(
                                        targetValue = Color(colorInt),
                                        animationSpec = tween(durationMillis = 500)
                                    )
                                }
                                viewModel.onEvent(NoteDetailsEvent.ChangeColor(colorInt))
                            }
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            CDTextField(
                text = titleState.text,
                hint = titleState.hint,
                onValueChange = {
                    viewModel.onEvent(NoteDetailsEvent.EnteredTitle(it))
                },
                onFocusChange = {
                    viewModel.onEvent(NoteDetailsEvent.ChangeTitleFocus(it))
                },
                showHint = titleState.showHint,
                singleLine = true,
                textStyle = MaterialTheme.typography.h5
            )
            Spacer(modifier = Modifier.height(16.dp))
            CDTextField(
                text = contentState.text,
                hint = contentState.hint,
                onValueChange = {
                    viewModel.onEvent(NoteDetailsEvent.EnteredContent(it))
                },
                onFocusChange = {
                    viewModel.onEvent(NoteDetailsEvent.ChangeContentFocus(it))
                },
                showHint = contentState.showHint,
                singleLine = true,
                textStyle = MaterialTheme.typography.h5
            )
        }
    }

}
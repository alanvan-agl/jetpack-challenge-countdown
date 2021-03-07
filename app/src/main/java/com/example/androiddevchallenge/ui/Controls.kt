package com.example.androiddevchallenge.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ui.theme.cancelButtonColor
import com.example.androiddevchallenge.ui.theme.pauseButtonColor
import com.example.androiddevchallenge.ui.theme.startAndResumeButtonColor

@Composable
fun Controls(
    timerState: TimerState,
    onStart: () -> Unit,
    onPause: () -> Unit,
    onCancel: () -> Unit
) {
    val buttonWidth = 100.dp
    val buttonHeight = 50.dp
    val buttonCornerRadius = 25.dp
    when (timerState) {
        is TimerState.Idle -> {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                TextButton(
                    modifier = Modifier.width(buttonWidth).height(buttonHeight),
                    onClick = onStart,
                    colors = ButtonDefaults.buttonColors(backgroundColor = startAndResumeButtonColor),
                    shape = RoundedCornerShape(buttonCornerRadius)
                ) {
                    Text(
                        text = stringResource(id = R.string.start),
                        color = MaterialTheme.colors.secondary,
                        fontSize = 18.sp
                    )
                }
            }
        }
        is TimerState.Running -> {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                TextButton(
                    modifier = Modifier.width(buttonWidth).height(buttonHeight),
                    onClick = onPause,
                    colors = ButtonDefaults.buttonColors(backgroundColor = pauseButtonColor),
                    shape = RoundedCornerShape(buttonCornerRadius)
                ) {
                    Text(
                        text = stringResource(id = R.string.pause),
                        color = MaterialTheme.colors.secondary,
                        fontSize = 18.sp
                    )
                }
                TextButton(
                    modifier = Modifier.width(buttonWidth).height(buttonHeight),
                    onClick = onCancel,
                    colors = ButtonDefaults.buttonColors(backgroundColor = cancelButtonColor),
                    shape = RoundedCornerShape(buttonCornerRadius)
                ) {
                    Text(
                        text = stringResource(id = R.string.cancel),
                        color = MaterialTheme.colors.secondary,
                        fontSize = 18.sp
                    )
                }
            }
        }
        is TimerState.Paused -> {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                TextButton(
                    modifier = Modifier.width(buttonWidth).height(buttonHeight),
                    onClick = onStart,
                    colors = ButtonDefaults.buttonColors(backgroundColor = startAndResumeButtonColor),
                    shape = RoundedCornerShape(buttonCornerRadius)
                ) {
                    Text(
                        text = stringResource(id = R.string.resume),
                        color = MaterialTheme.colors.secondary,
                        fontSize = 18.sp
                    )
                }
                TextButton(
                    modifier = Modifier.width(buttonWidth).height(buttonHeight),
                    onClick = onCancel,
                    colors = ButtonDefaults.buttonColors(backgroundColor = cancelButtonColor),
                    shape = RoundedCornerShape(buttonCornerRadius)
                ) {
                    Text(
                        text = stringResource(id = R.string.cancel),
                        color = MaterialTheme.colors.secondary,
                        fontSize = 18.sp
                    )
                }
            }
        }
    }
}
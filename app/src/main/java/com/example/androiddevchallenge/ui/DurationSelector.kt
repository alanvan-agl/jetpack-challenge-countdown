package com.example.androiddevchallenge.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.MainViewModel
import com.example.androiddevchallenge.R

@Composable
fun DurationSelector(viewModel: MainViewModel) {
    val currentHour = viewModel.hour.collectAsState()
    val currentMinute = viewModel.minute.collectAsState()
    val currentSecond = viewModel.second.collectAsState()
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        NumberSelector(
            type = stringResource(id = R.string.hours),
            currentValue = currentHour.value,
            onDurationIncreased = {
                viewModel.increaseDuration(it, MainViewModel.DurationType.HOUR)
            },
            onDurationDecreased = {
                viewModel.decreaseDuration(it, MainViewModel.DurationType.HOUR)
            }
        )
        Text(
            modifier = Modifier.padding(PaddingValues(top = 100.dp)),
            color = MaterialTheme.colors.secondary,
            text = ":",
            fontSize = 48.sp
        )
        NumberSelector(
            type = stringResource(id = R.string.minutes),
            currentValue = currentMinute.value,
            onDurationIncreased = {
                viewModel.increaseDuration(it, MainViewModel.DurationType.MINUTE)
            },
            onDurationDecreased = {
                viewModel.decreaseDuration(it, MainViewModel.DurationType.MINUTE)
            }
        )
        Text(
            modifier = Modifier.padding(PaddingValues(top = 100.dp)),
            color = MaterialTheme.colors.secondary,
            text = ":",
            fontSize = 48.sp
        )
        NumberSelector(
            type = stringResource(id = R.string.seconds),
            currentValue = currentSecond.value,
            onDurationIncreased = {
                viewModel.increaseDuration(it, MainViewModel.DurationType.SECOND)
            },
            onDurationDecreased = {
                viewModel.decreaseDuration(it, MainViewModel.DurationType.SECOND)
            }
        )
    }
}

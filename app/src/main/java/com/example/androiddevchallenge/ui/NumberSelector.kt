package com.example.androiddevchallenge.ui

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.R

@Composable
fun NumberSelector(
    currentValue: Int,
    onDurationIncreased: (Int) -> Unit,
    onDurationDecreased: (Int) -> Unit,
    type: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(color = MaterialTheme.colors.secondary, text = type)
        Image(
            modifier = Modifier
                .padding(PaddingValues(top = 16.dp))
                .clickable { onDurationIncreased(currentValue) },
            painter = painterResource(id = R.drawable.ic_baseline_arrow_upward_48),
            contentDescription = "Increase time"
        )
        Text(
            modifier = Modifier.padding(PaddingValues(top = 16.dp, bottom = 16.dp)),
            color = MaterialTheme.colors.secondary,
            text = String.format("%02d", currentValue),
            fontSize = 48.sp
        )
        Image(
            modifier = Modifier
                .padding(PaddingValues(bottom = 16.dp))
                .clickable { onDurationDecreased(currentValue) },
            painter = painterResource(id = R.drawable.ic_baseline_arrow_downward_48),
            contentDescription = "Decrease time"
        )
    }
}

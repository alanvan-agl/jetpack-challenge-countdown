/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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

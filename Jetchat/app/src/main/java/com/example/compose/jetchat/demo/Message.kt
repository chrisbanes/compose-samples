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

package com.example.compose.jetchat.demo

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ContentCopy
import androidx.compose.material.icons.outlined.InsertEmoticon
import androidx.compose.material.icons.outlined.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.jetchat.conversation.Message
import com.example.compose.jetchat.data.demoMessages
import com.example.compose.jetchat.theme.JetchatTheme

@Preview
@Composable
fun PreviewMessage_Me() {
    JetchatTheme {
        Message(message = demoMessages[0], isFromMe = true)
    }
}

@Preview
@Composable
fun PreviewMessage_Other() {
    JetchatTheme {
        Message(message = demoMessages[1], isFromMe = false)
    }
}

@Composable
fun Message(
    message: Message,
    isFromMe: Boolean,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier,
        backgroundColor = when {
            isFromMe -> MaterialTheme.colors.surface
            else -> MaterialTheme.colors.secondary
        },
    ) {
        var showActions by remember { mutableStateOf(false) }
        Column(
            modifier = Modifier
                .clickable { showActions = !showActions }
                .padding(12.dp)
        ) {
            Text(text = message.content)

            AnimatedVisibility(
                visible = showActions,
                enter = fadeIn(
                    animSpec = spring(stiffness = Spring.StiffnessLow)
                ) + expandVertically(
                    animSpec = spring(stiffness = Spring.StiffnessLow)
                ),
            exit = fadeOut(
                animSpec = spring(stiffness = Spring.StiffnessLow)
            ) + shrinkVertically(
                animSpec = spring(stiffness = Spring.StiffnessLow)
            ),
            ) {
                MessageActions()
            }
        }
    }
}

@Composable
fun MessageActions() {
    Row {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(Icons.Outlined.ContentCopy, contentDescription = "Copy message")
        }
        IconButton(onClick = { /*TODO*/ }) {
            Icon(Icons.Outlined.Share, contentDescription = "Share")
        }
        IconButton(onClick = { /*TODO*/ }) {
            Icon(Icons.Outlined.InsertEmoticon, contentDescription = "Insert emoji")
        }
    }
}

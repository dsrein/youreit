package com.example.composepractice

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composepractice.ui.theme.InternalBlue
import com.example.composepractice.ui.theme.InternalGray
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers


@Composable
fun MessageScreen(viewModel: MainViewModel = MainViewModel(), modifier: Modifier = Modifier) {
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
    ) {
        MessagesListView(viewModel)
        InputBar(
            modifier = Modifier.align(Alignment.BottomCenter),
            updateCallback = {
                StubRetrofitInstance.api.getMessages("alpha")
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        viewModel.updateList(it)
                    }
            },
            viewModel = viewModel
        )
    }
}

@Composable
fun MessagesListView(viewModel: MainViewModel, modifier: Modifier = Modifier) {
    val messagesState = viewModel.messagesFlow.collectAsState()

    LazyColumn(modifier = modifier
        .padding(vertical = 16.dp)) {
        items(messagesState.value) {
            MessageCard(it)
        }
    }
}

@Composable
fun InputBar(modifier: Modifier = Modifier, updateCallback: () -> Unit = {}, viewModel: MainViewModel) {
    val padding = 8.dp
    var inputText by remember {
        mutableStateOf("")
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        OutlinedTextField(
            value = inputText,
            onValueChange = {
                inputText = it
            },
            textStyle = TextStyle.Default.copy(fontSize = 18.sp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colors.primary,
                unfocusedBorderColor = MaterialTheme.colors.primary),
            modifier = Modifier
                .weight(8f)
                .size(50.dp)
        )
        Spacer(modifier = Modifier.size(padding))
        Button(onClick = {
            StubRetrofitInstance.api.postMessage("alpha", viewModel.id++, "red", inputText)
                .subscribe {
                    inputText = ""
                    updateCallback()
                }

        },
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .weight(2f)) {
            Text("Send")
        }
    }

}

@Composable
fun MessageCard(messageItem: Message) { // 1
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp),
        horizontalAlignment = when {
            messageItem.isMine -> Alignment.End
            else -> Alignment.Start
        },
    ) {
        Card(
            modifier = Modifier.widthIn(max = 340.dp),
            shape = cardShapeFor(messageItem),
            backgroundColor = when {
                messageItem.isMine -> MaterialTheme.colors.primary
                else -> MaterialTheme.colors.secondary
            },
        ) {
            Text(
                modifier = Modifier.padding(10.dp),
                text = messageItem.text,
                style = TextStyle.Default.copy(fontSize = 18.sp),
                color = when {
                    messageItem.isMine -> MaterialTheme.colors.onPrimary
                    else -> MaterialTheme.colors.onSecondary
                },
            )
        }
        Text(
            text = messageItem.senderId,
            fontSize = 12.sp,
        )
    }
}

@Composable
fun cardShapeFor(message: Message): Shape {
    val roundedCorners = RoundedCornerShape(16.dp)
    return when {
        message.isMine -> roundedCorners.copy(bottomEnd = CornerSize(0))
        else -> roundedCorners.copy(bottomStart = CornerSize(0))
    }
}
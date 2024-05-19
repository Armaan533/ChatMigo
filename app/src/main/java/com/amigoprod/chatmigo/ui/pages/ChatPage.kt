package com.amigoprod.chatmigo.ui.pages



import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.amigoprod.chatmigo.ui.theme.PurpleGrey80

//@Preview
//@Composable
//fun ChatPage() {
//    ConstraintLayout(
//        modifier = Modifier.fillMaxSize()
//    ) {
//        val(messages, chatBox) = createRef()
//        LazyColumn {
//
//        }
//    }
//}


@Composable
private fun Message(
    isMine: Boolean,
    content: String
) {
    Box(
        modifier = Modifier
            .clip(
                RoundedCornerShape(
                    topStart = 48f,
                    topEnd = 48f,
                    bottomStart = if (isMine) 48f else 0f,
                    bottomEnd = if (isMine) 0f else 48f
                )
            )
            .background(PurpleGrey80)
            .padding(16.dp)
    ) {
        Text(text = content)
    }
}
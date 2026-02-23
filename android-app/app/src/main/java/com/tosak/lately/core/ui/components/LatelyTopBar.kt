package com.tosak.lately.core.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.tosak.lately.R

@Composable
fun LatelyTopBar(
  onCameraClick: () -> Unit,
  onChatClick: () -> Unit
) {

  val pacificoFont = FontFamily(
    Font(R.font.pacifico)
  )

  Surface {
    Box(
      modifier = Modifier
        .fillMaxWidth()
    ) {

      IconButton(
        onClick = onCameraClick,
        modifier = Modifier.align(Alignment.CenterStart)
      ) {
        Icon(
          imageVector = Icons.Default.Add,
          contentDescription = "Camera",
          modifier = Modifier.size(30.dp)

        )
      }

      Text(
        text = "Lately",
        fontSize = 26.sp,
        fontFamily = pacificoFont,
        modifier = Modifier.align(Alignment.Center)
      )

      IconButton(
        onClick = onChatClick,
        modifier = Modifier.align(Alignment.CenterEnd)
      ) {
        Icon(
          imageVector = Icons.AutoMirrored.Filled.Send,
          contentDescription = "Chat",
          modifier = Modifier
            .size(30.dp)
            .rotate(315f)
        )
      }
    }
  }
}
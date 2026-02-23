package com.tosak.lately.core.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DestructiveButton(
  label: String,
  onClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  OutlinedButton(
    onClick = onClick,
    modifier = modifier
      .fillMaxWidth()
      .height(52.dp),
    shape = RoundedCornerShape(14.dp),
    colors = ButtonDefaults.outlinedButtonColors(
      contentColor = MaterialTheme.colorScheme.error
    ),
    border = BorderStroke(
      1.dp,
      MaterialTheme.colorScheme.error.copy(alpha = 0.4f)
    )
  ) {
    Text(
      text = label,
      fontWeight = FontWeight.SemiBold,
      fontSize = 15.sp
    )
  }
}
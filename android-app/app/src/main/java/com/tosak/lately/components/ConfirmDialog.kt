package com.tosak.lately.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun ConfirmDialog(
  title: String,
  body: String,
  confirmLabel: String,
  onConfirm: () -> Unit,
  onDismiss: () -> Unit
) {
  AlertDialog(
    onDismissRequest = onDismiss,
    shape = RoundedCornerShape(20.dp),
    title = {
      Text(title, fontWeight = FontWeight.Bold)
    },
    text = {
      Text(body, color = MaterialTheme.colorScheme.onSurfaceVariant)
    },
    confirmButton = {
      TextButton(
        onClick = onConfirm,
        colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colorScheme.error)
      ) {
        Text(confirmLabel, fontWeight = FontWeight.SemiBold)
      }
    },
    dismissButton = {
      TextButton(onClick = onDismiss) { Text("Cancel") }
    }
  )
}
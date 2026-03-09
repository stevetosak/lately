package com.tosak.lately.features.profile.components

import androidx.compose.runtime.Composable
import com.tosak.lately.core.ui.components.dialogs.ConfirmDialog

@Composable
fun UnfriendDialog(
  displayName: String,
  onConfirm: () -> Unit,
  onDismiss: () -> Unit
) {
  ConfirmDialog(
    title = "Unfriend $displayName?",
    body = "$displayName will be removed from your friends list.",
    confirmLabel = "Unfriend",
    onConfirm = onConfirm,
    onDismiss = onDismiss
  )
}
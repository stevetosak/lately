package com.tosak.lately.features.friends.components

import androidx.compose.runtime.Composable
import com.tosak.lately.components.ConfirmDialog

@Composable
fun RemoveFriendDialog(displayName: String, onConfirm: () -> Unit, onDismiss: () -> Unit) {
  ConfirmDialog(
    title = "Remove friend?",
    body = "$displayName will no longer be in your friends list.",
    confirmLabel = "Remove",
    onConfirm = onConfirm,
    onDismiss = onDismiss
  )
}

@Composable
fun BlockFriendDialog(displayName: String, onConfirm: () -> Unit, onDismiss: () -> Unit) {
  ConfirmDialog(
    title = "Block $displayName?",
    body = "They won't be able to find your profile or send you messages.",
    confirmLabel = "Block",
    onConfirm = onConfirm,
    onDismiss = onDismiss
  )
}
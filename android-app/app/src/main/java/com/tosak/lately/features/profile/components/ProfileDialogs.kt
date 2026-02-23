package com.tosak.lately.features.profile.components

import androidx.compose.runtime.Composable
import com.tosak.lately.components.ConfirmDialog

@Composable
fun DeactivateAccountDialog(onConfirm: () -> Unit, onDismiss: () -> Unit) {
  ConfirmDialog(
    title        = "Deactivate Account",
    body         = "Your account will be deactivated and others won't be able to find you. Are you sure?",
    confirmLabel = "Deactivate",
    onConfirm    = onConfirm,
    onDismiss    = onDismiss
  )
}
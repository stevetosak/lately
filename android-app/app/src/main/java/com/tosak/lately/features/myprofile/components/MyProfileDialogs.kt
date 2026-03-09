package com.tosak.lately.features.myprofile.components

import androidx.compose.runtime.Composable
import com.tosak.lately.core.ui.components.dialogs.ConfirmDialog

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
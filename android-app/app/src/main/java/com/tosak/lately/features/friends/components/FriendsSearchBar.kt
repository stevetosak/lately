package com.tosak.lately.features.friends.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FriendsSearchBar(
  searchText: String,
  onSearchTextChange: (String) -> Unit,
  modifier: Modifier = Modifier
) {

  OutlinedTextField(
    value = searchText,
    onValueChange = onSearchTextChange,
    modifier = modifier.fillMaxWidth(),
    placeholder = { Text("Search friends…", color = MaterialTheme.colorScheme.onSurfaceVariant) },
    leadingIcon = {
      Icon(
        imageVector = Icons.Outlined.Search,
        contentDescription = null,
        tint = MaterialTheme.colorScheme.onSurfaceVariant
      )
    },
    trailingIcon = {
      if (searchText.isNotEmpty()) {
        IconButton(onClick = { onSearchTextChange("") }) {
          Icon(
            imageVector = Icons.Outlined.Close,
            contentDescription = "Clear search",
            tint = MaterialTheme.colorScheme.onSurfaceVariant
          )
        }
      }
    },
    singleLine = true,
    shape = RoundedCornerShape(16.dp),
    colors = OutlinedTextFieldDefaults.colors(
      focusedBorderColor = MaterialTheme.colorScheme.primary,
      unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f),
      focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f),
      unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)
    )
  )
}
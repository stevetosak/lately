package com.tosak.lately.features.myprofile.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.ManageAccounts
import androidx.compose.material.icons.outlined.PhotoLibrary
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun MyProfileSettings(
  onEditProfile: () -> Unit,
  onArchivedStories: () -> Unit,
) {
  Surface(
    modifier = Modifier.fillMaxWidth(),
    shape    = RoundedCornerShape(20.dp),
    color    = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f),
    tonalElevation = 0.dp
  ) {
    Column(modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)) {
      ActionRow(
        icon      = Icons.Outlined.ManageAccounts,
        label     = "Profile info",
        sublabel  = "Update personal info",
        onClick   = onEditProfile
      )
      HorizontalDivider(
        modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp),
        color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f),
        thickness = 0.5.dp
      )
      ActionRow(
        icon      = Icons.Outlined.PhotoLibrary,
        label     = "Archived stories",
        sublabel  = "Browse your past stories",
        onClick   = onArchivedStories
      )
    }
  }
}

/**
 * A tappable row used in every profile section card.
 */
@Composable
fun ActionRow(
  icon: ImageVector,
  label: String,
  sublabel: String,
  onClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  val interactionSource = remember { MutableInteractionSource() }
  var pressed by remember { mutableStateOf(false) }

  val scale by animateFloatAsState(
    targetValue = if (pressed) 0.97f else 1f,
    animationSpec = tween(100),
    label = "rowScale"
  )

  Row(
    modifier = modifier
      .fillMaxWidth()
      .scale(scale)
      .clip(RoundedCornerShape(16.dp))
      .clickable(
        interactionSource = interactionSource,
        indication = null,
        onClick = onClick
      )
      .padding(vertical = 14.dp, horizontal = 4.dp),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(14.dp)
  ) {
    ActionIconBox(icon = icon)

    Column(modifier = Modifier.weight(1f)) {
      Text(
        text = label,
        style = MaterialTheme.typography.bodyLarge,
        fontWeight = FontWeight.SemiBold,
        color = MaterialTheme.colorScheme.onBackground
      )
      Text(
        text = sublabel,
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.onSurfaceVariant
      )
    }

    Icon(
      imageVector = Icons.Outlined.ChevronRight,
      contentDescription = null,
      modifier = Modifier.size(20.dp),
      tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f)
    )
  }
}

/**
 * Rounded square icon container used in action rows.
 */
@Composable
fun ActionIconBox(
  icon: ImageVector,
  modifier: Modifier = Modifier
) {
  Box(
    modifier = modifier
      .size(44.dp)
      .clip(RoundedCornerShape(12.dp))
      .background(MaterialTheme.colorScheme.primaryContainer),
    contentAlignment = Alignment.Center
  ) {
    Icon(
      imageVector = icon,
      contentDescription = null,
      modifier = Modifier.size(22.dp),
      tint = MaterialTheme.colorScheme.onPrimaryContainer
    )
  }
}
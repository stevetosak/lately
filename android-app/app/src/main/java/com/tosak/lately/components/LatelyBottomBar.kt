package com.tosak.lately.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Map
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.tosak.lately.navigation.Destinations

data class NavItem(
  val destination: Destinations,
  val selectedIcon: ImageVector,
  val unselectedIcon: ImageVector
)

@Composable
fun LatelyBottomBar(navController: NavHostController) {
  val sideItems = listOf(
    NavItem(Destinations.Search, Icons.Filled.Search, Icons.Outlined.Search),
    NavItem(Destinations.Notifications, Icons.Filled.Notifications, Icons.Outlined.Notifications),
    NavItem(Destinations.Profile, Icons.Filled.Person, Icons.Outlined.Person),
    NavItem(Destinations.Settings, Icons.Filled.Settings, Icons.Outlined.Settings),
  )

  val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
  val isMapSelected = currentRoute == Destinations.Map.route

  fun navigate(destination: Destinations) {
    if (currentRoute != destination.route) {
      navController.navigate(destination.route) {
        popUpTo(Destinations.Map.route) { saveState = true }
        launchSingleTop = true
        restoreState = true
      }
    }
  }

  Box(
    modifier = Modifier
      .fillMaxWidth()
      .navigationBarsPadding(),
    contentAlignment = Alignment.BottomCenter
  ) {
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .height(60.dp)
        .background(MaterialTheme.colorScheme.surface)
    ) {
      BottomBarRow(
        sideItems = sideItems,
        currentRoute = currentRoute,
        onNavigate = ::navigate
      )
    }

    NavIconFab(
      selected = isMapSelected,
      onClick = { navigate(Destinations.Map) }
    )
  }
}

@Composable
private fun BottomBarRow(
  sideItems: List<NavItem>,
  currentRoute: String?,
  onNavigate: (Destinations) -> Unit
) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .height(65.dp)
      .padding(horizontal = 12.dp),
    horizontalArrangement = Arrangement.SpaceEvenly,
    verticalAlignment = Alignment.CenterVertically
  ) {
    sideItems.take(2).forEach { item ->
      NavIconButton(
        item = item,
        selected = currentRoute == item.destination.route,
        onClick = { onNavigate(item.destination) },
        modifier = Modifier.weight(1f)
      )
    }

    Spacer(modifier = Modifier.weight(1f))

    sideItems.drop(2).forEach { item ->
      NavIconButton(
        item = item,
        selected = currentRoute == item.destination.route,
        onClick = { onNavigate(item.destination) },
        modifier = Modifier.weight(1f)
      )
    }
  }
}

@Composable
private fun NavIconButton(
  item: NavItem,
  selected: Boolean,
  onClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  Box(
    modifier = modifier
      .height(72.dp)
      .clickable(
        interactionSource = remember { MutableInteractionSource() },
        indication = null,
        onClick = onClick
      ),
    contentAlignment = Alignment.Center
  ) {
    if (selected) {
      NavSelectionPill()
    }

    Icon(
      imageVector = if (selected) item.selectedIcon else item.unselectedIcon,
      contentDescription = item.destination.route,
      modifier = Modifier.size(28.dp),
      tint = if (selected)
        MaterialTheme.colorScheme.onSurface
      else
        MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
    )
  }
}

@Composable
private fun NavSelectionPill() {
  Box(
    modifier = Modifier
      .size(width = 20.dp, height = 3.dp)
      .offset(y = 16.dp)
      .clip(RoundedCornerShape(50))
      .background(
        Brush.horizontalGradient(
          colors = listOf(
            MaterialTheme.colorScheme.primary,
            MaterialTheme.colorScheme.tertiary
          )
        )
      )
  )
}


@Composable
private fun NavIconFab(
  selected: Boolean,
  onClick: () -> Unit
) {
  val elevation by animateDpAsState(
    targetValue = if (selected) 12.dp else 6.dp,
    animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
    label = "fabElevation"
  )
  val scale by animateFloatAsState(
    targetValue = if (selected) 1.08f else 1f,
    animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
    label = "fabScale"
  )
  val background = if (selected)
    Brush.linearGradient(
      colors = listOf(
        MaterialTheme.colorScheme.primary,
        MaterialTheme.colorScheme.tertiary
      )
    )
  else
    Brush.linearGradient(
      colors = listOf(
        MaterialTheme.colorScheme.primaryContainer,
        MaterialTheme.colorScheme.secondaryContainer
      )
    )

  Box(
    modifier = Modifier
      .offset(y = (-24).dp)
      .graphicsLayer { scaleX = scale; scaleY = scale }
      .shadow(
        elevation = elevation,
        shape = CircleShape,
        ambientColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f),
        spotColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.4f)
      )
      .size(68.dp)
      .clip(CircleShape)
      .background(background)
      .clickable(
        interactionSource = remember { MutableInteractionSource() },
        indication = null,
        onClick = onClick
      ),
    contentAlignment = Alignment.Center
  ) {
    Icon(
      imageVector = if (selected) Icons.Filled.Map else Icons.Outlined.Map,
      contentDescription = "Map",
      modifier = Modifier.size(32.dp),
      tint = if (selected)
        MaterialTheme.colorScheme.onPrimary
      else
        MaterialTheme.colorScheme.onPrimaryContainer
    )
  }
}

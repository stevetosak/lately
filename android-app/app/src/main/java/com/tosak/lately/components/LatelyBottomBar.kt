package com.tosak.lately.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.outlined.Explore
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
fun LatelyBottomBar(
  navController: NavHostController
) {
  val items = listOf(
    NavItem(
      destination = Destinations.Map,
      selectedIcon = Icons.Filled.Explore,
      unselectedIcon = Icons.Outlined.Explore
    ),
    NavItem(
      destination = Destinations.Search,
      selectedIcon = Icons.Filled.Search,
      unselectedIcon = Icons.Outlined.Search
    ),
    NavItem(
      destination = Destinations.Notifications,
      selectedIcon = Icons.Filled.Notifications,
      unselectedIcon = Icons.Outlined.Notifications
    ),
    NavItem(
      destination = Destinations.Profile,
      selectedIcon = Icons.Filled.Person,
      unselectedIcon = Icons.Outlined.Person
    )
  )

  val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

  NavigationBar(
    tonalElevation = 0.dp,
    containerColor = MaterialTheme.colorScheme.surface,
    modifier = Modifier
      .fillMaxWidth()
      .navigationBarsPadding()
      .height(56.dp)
  ) {

    items.forEach { item ->
      val selected = currentRoute == item.destination.route

      NavigationBarItem(
        selected = selected,
        onClick = {
          if (currentRoute != item.destination.route) {
            navController.navigate(item.destination.route) {
              popUpTo(Destinations.Map.route) {
                saveState = true
              }
              launchSingleTop = true
              restoreState = true
            }
          }
        },
        icon = {
          Icon(
            imageVector = if (selected) item.selectedIcon else item.unselectedIcon,
            contentDescription = item.destination.route,
            modifier = Modifier.size(30.dp)
          )
        },
        alwaysShowLabel = false,
        colors = NavigationBarItemDefaults.colors(
          indicatorColor = Color.Transparent,
          selectedIconColor = MaterialTheme.colorScheme.onSurface,
          unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant
        )
      )
    }
  }
}
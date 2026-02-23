package com.tosak.lately.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.tosak.lately.features.chats.MessagesScreen
import com.tosak.lately.features.map.MapScreen
import com.tosak.lately.features.notifications.NotificationsScreen
import com.tosak.lately.features.archivedstories.ArchivedStoriesScreen
import com.tosak.lately.features.profile.edit.EditProfileScreen
import com.tosak.lately.features.friends.FriendsScreen
import com.tosak.lately.features.profile.ProfileScreen
import com.tosak.lately.features.search.SearchScreen
import com.tosak.lately.features.settings.SettingsScreen

@Composable
fun NavGraph(
  navController: NavHostController,
  modifier: Modifier = Modifier
) {

  NavHost(
    navController = navController,
    startDestination = Destinations.Map.route,
    modifier = modifier,
    enterTransition = { EnterTransition.None },
    exitTransition = { ExitTransition.None }
  ) {

    composable(Destinations.Map.route) {
      MapScreen(navController)
    }

    composable(Destinations.Search.route) {
      SearchScreen()
    }

    composable(Destinations.Notifications.route) {
      NotificationsScreen()
    }

    composable(Destinations.Profile.route) {
      ProfileScreen(navController = navController)
    }

    composable(route = Destinations.Settings.route) {
      SettingsScreen()
    }

    composable(
      route = Destinations.Messages.route,
      enterTransition = { slideInHorizontally(initialOffsetX = { it }) },
      exitTransition = { slideOutHorizontally(targetOffsetX = { it }) }
    ) {
      MessagesScreen(navController)
    }

    composable(
      route           = Destinations.EditProfile.route,
      enterTransition = { slideInHorizontally(initialOffsetX = { it }) },
      exitTransition  = { slideOutHorizontally(targetOffsetX = { it }) }
    ) {
      EditProfileScreen(navController = navController)
    }

    composable(
      route           = Destinations.ArchivedStories.route,
      enterTransition = { slideInHorizontally(initialOffsetX = { it }) },
      exitTransition  = { slideOutHorizontally(targetOffsetX = { it }) }
    ) {
      ArchivedStoriesScreen(navController = navController)
    }

    composable(
      route = Destinations.Friends.route,
      enterTransition = { slideInHorizontally { it } },
      exitTransition = { slideOutHorizontally { it } }
    ) {
      FriendsScreen(navController = navController)
    }
  }
}
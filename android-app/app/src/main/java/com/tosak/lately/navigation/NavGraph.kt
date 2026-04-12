package com.tosak.lately.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.tosak.lately.features.map.MapScreen
import com.tosak.lately.features.notifications.NotificationsScreen
import com.tosak.lately.features.archivedstories.ArchivedStoriesScreen
import com.tosak.lately.features.archivedstories.ArchivedStoriesViewModel
import com.tosak.lately.features.chats.ChatsScreen
import com.tosak.lately.features.chats.chat.ChatScreen
import com.tosak.lately.features.stories.viewer.StoryViewerScreen
import com.tosak.lately.features.myprofile.edit.EditProfileScreen
import com.tosak.lately.features.friends.FriendsScreen
import com.tosak.lately.features.myprofile.MyProfileScreen
import com.tosak.lately.features.profile.ProfileScreen
import com.tosak.lately.features.search.SearchScreen
import com.tosak.lately.features.settings.SettingsScreen
import com.tosak.lately.features.stories.StoryViewModel
import com.tosak.lately.features.stories.viewer.StoryViewerViewModel
import com.tosak.lately.features.stories.viewer.toViewerItem

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
      SearchScreen(navController)
    }

    composable(Destinations.Notifications.route) {
      NotificationsScreen(navController)
    }

    composable(Destinations.MyProfile.route) {
      MyProfileScreen(navController = navController)
    }

    composable(Destinations.Profile.route) {
      ProfileScreen(navController = navController)
    }

    composable(route = Destinations.Settings.route) {
      SettingsScreen()
    }

    composable(route = Destinations.Chats.route) {
      ChatsScreen(navController)
    }

    composable(
      route = Destinations.Chat.route,
      arguments = listOf(
        navArgument("userId") { type = NavType.StringType }
      ),
      enterTransition = { slideInHorizontally(initialOffsetX = { it }) },
      exitTransition = { slideOutHorizontally(targetOffsetX = { it }) }
    ) {
      ChatScreen(navController = navController)
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

    composable(
      route           = Destinations.ArchivedStoryViewer.route,
      enterTransition = { slideInVertically { it } },
      exitTransition  = { slideOutVertically { it } }
    ) { backStackEntry ->

      val parentEntry = remember(backStackEntry) {
        navController.getBackStackEntry(Destinations.ArchivedStories.route)
      }

      val archivedVm: ArchivedStoriesViewModel = hiltViewModel(parentEntry)
      val viewerVm: StoryViewerViewModel = hiltViewModel(backStackEntry)
      val storyId = backStackEntry.arguments?.getString("storyId") ?: return@composable

      LaunchedEffect(Unit) {
        val items = archivedVm.getCachedArchivedStories().map { it.toViewerItem() }
        viewerVm.load(items)
      }

      StoryViewerScreen(
        navController = navController,
        storyId       = storyId,
        viewModel     = viewerVm
      )
    }

    composable(
      route           = Destinations.LiveStoryViewer.route,
      enterTransition = { slideInVertically { it } },
      exitTransition  = { slideOutVertically { it } }
    ) { backStackEntry ->

      val parentEntry = remember(backStackEntry) {
        navController.getBackStackEntry(Destinations.Map.route)
      }

      val storyVm: StoryViewModel = hiltViewModel(parentEntry)
      val viewerVm: StoryViewerViewModel = hiltViewModel(backStackEntry)
      val storyId = backStackEntry.arguments?.getString("storyId") ?: return@composable

      LaunchedEffect(Unit) {
        val item = storyVm.getCachedNearbyStories()
          .firstOrNull { it.id == storyId }
          ?.toViewerItem()
          ?: return@LaunchedEffect

        viewerVm.load(listOf(item))
      }

      StoryViewerScreen(
        navController = navController,
        storyId       = storyId,
        viewModel     = viewerVm
      )
    }
  }
}
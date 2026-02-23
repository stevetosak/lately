package com.tosak.lately.navigation

sealed class Destinations(val route: String) {

  object Map : Destinations("map")
  object Search : Destinations("search")
  object Notifications : Destinations("notifications")
  object Messages : Destinations("messages")
  object Profile : Destinations("profile")

  object Settings     : Destinations("settings")
  object EditProfile     : Destinations("profile/edit")
  object ArchivedStories : Destinations("profile/archived")
  object Friends         : Destinations("profile/friends")

  companion object {
    val bottomBarRoutes = listOf(
      Map.route,
      Search.route,
      Notifications.route,
      Profile.route,
      Settings.route
    )
  }
}

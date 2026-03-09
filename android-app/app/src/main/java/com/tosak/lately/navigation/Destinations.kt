package com.tosak.lately.navigation

sealed class Destinations(val route: String) {

  object Map : Destinations("map")
  object Search : Destinations("search")
  object Notifications : Destinations("notifications")
  object Messages : Destinations("messages")
  object MyProfile : Destinations("my-profile")

  object Profile : Destinations("profile/{profileId}") {
    fun route(profileId: String) = "profile/$profileId"
  }

  object Settings     : Destinations("settings")
  object EditProfile     : Destinations("my-profile/edit")
  object ArchivedStories : Destinations("my-profile/archived")
  object Friends         : Destinations("my-profile/friends")

//  object StoryViewer : Destinations("profile/archived/{storyId}") {
//    fun route(storyId: String) = "profile/archived/$storyId"
//  }

  companion object {
    val bottomBarRoutes = listOf(
      Map.route,
      Search.route,
      Notifications.route,
      MyProfile.route,
      Settings.route
    )
  }
}

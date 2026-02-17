package com.tosak.lately.navigation

sealed class Destinations(val route: String) {

  object Map : Destinations("map")
  object Search : Destinations("search")
  object Notifications : Destinations("notifications")
  object Profile : Destinations("profile")
  object Messages : Destinations("messages")
}

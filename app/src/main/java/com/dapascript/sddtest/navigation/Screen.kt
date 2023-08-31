package com.dapascript.sddtest.navigation

sealed class Screen(val route: String) {
    object HomeListScreen : Screen("home_screen")
    object DetailScreen : Screen("detail_screen")
}

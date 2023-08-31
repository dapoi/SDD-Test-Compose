package com.dapascript.sddtest.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dapascript.sddtest.data.source.PromoResponse
import com.dapascript.sddtest.navigation.Screen
import com.dapascript.sddtest.presentation.detail.DetailScreen
import com.dapascript.sddtest.presentation.home.HomeScreen
import com.dapascript.sddtest.presentation.ui.theme.SDDTestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SDDTestTheme {
                PromoApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PromoApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
        modifier = modifier,
    ) { innerPadding ->
        NavHost(
            modifier = Modifier.padding(innerPadding),
            navController = navController,
            startDestination = Screen.HomeListScreen.route
        ) {
            composable(
                route = Screen.HomeListScreen.route
            ) {
                HomeScreen(
                    navigateToDetail = { promo ->
                        navController.currentBackStackEntry?.savedStateHandle?.set(
                            "promo",
                            promo
                        )
                        navController.navigate(Screen.DetailScreen.route)
                    }
                )
            }
            composable(
                route = Screen.DetailScreen.route,
            ) {
                val result =
                    navController.previousBackStackEntry?.savedStateHandle?.get<PromoResponse>("promo")
                result?.let { promo ->
                    DetailScreen(promo = promo)
                }
            }
        }
    }
}
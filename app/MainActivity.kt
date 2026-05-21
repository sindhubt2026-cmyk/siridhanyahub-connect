package com.siridhanyahub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.Crossfade
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.siridhanyahub.ui.screens.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SiriDhanyaHubApp()
        }
    }
}

@Composable
fun SiriDhanyaHubApp() {
    var showSplash by remember { mutableStateOf(true) }
    val navController = rememberNavController()

    Crossfade(targetState = showSplash, label = "Splash") { splash ->
        if (splash) {
            SplashScreen(onSplashComplete = { showSplash = false })
        } else {
            NavHost(navController = navController, startDestination = "home") {
                composable("home") {
                    HomeScreen(onNavigate = { feature ->
                        when (feature) {
                            "About Millets" -> navController.navigate("about_millets")
                            "Health Benefits" -> navController.navigate("health_benefits")
                            "Recipes" -> navController.navigate("recipes")
                            "Market Prices" -> navController.navigate("market_prices")
                            "Direct Buy" -> navController.navigate("direct_buy")
                            "Mandi Watch" -> navController.navigate("mandi_watch")
                        }
                    })
                }
                composable("about_millets") {
                    AboutMilletsScreen(onBack = { navController.popBackStack() })
                }
                composable("health_benefits") {
                    PlaceholderScreen("Health Benefits", onBack = { navController.popBackStack() })
                }
                composable("recipes") {
                    PlaceholderScreen("Recipes", onBack = { navController.popBackStack() })
                }
                composable("market_prices") {
                    PlaceholderScreen("Market Prices", onBack = { navController.popBackStack() })
                }
                composable("direct_buy") {
                    PlaceholderScreen("Direct Buy", onBack = { navController.popBackStack() })
                }
                composable("mandi_watch") {
                    PlaceholderScreen("Mandi Watch", onBack = { navController.popBackStack() })
                }
            }
        }
    }
}

@Composable
fun PlaceholderScreen(title: String, onBack: () -> Unit) {
    Column(
        modifier = androidx.compose.foundation.background(Color(0xFFF5F1E8))
            .fillMaxSize()
    ) {
        TopAppBar(
            title = { androidx.compose.material3.Text(title) },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(
                        imageVector = androidx.compose.material.icons.filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color(0xFF8B6F47)
            )
        )
        Box(
            modifier = androidx.compose.foundation.layout.Box(
                modifier = androidx.compose.foundation.layout.fillMaxSize(),
                contentAlignment = androidx.compose.ui.Alignment.Center
            )
        ) {
            androidx.compose.material3.Text(
                "$title Screen - Coming Soon!",
                style = androidx.compose.ui.text.TextStyle(
                    fontSize = 18.sp,
                    color = Color(0xFF6B4C2F)
                )
            )
        }
    }
}

package com.stitchfix.navigationrecomposingbug

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.stitchfix.navigationrecomposingbug.ui.theme.NavigationRecomposingBugTheme
import kotlinx.coroutines.delay

object Nav {
    val Splash = "splash"
    val Home = "home"
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainActivityContent()
        }
    }
}
@Composable
fun MainActivityContent() {
    NavigationRecomposingBugTheme {
        val startDestination = remember { mutableStateOf(Nav.Splash) }

        NavHost(
            navController = rememberNavController(),
            startDestination = startDestination.value,
        ) {

            composableScreen(Nav.Splash) {
                Button(onClick = { startDestination.value = Nav.Home }) {
                    Text("Home")
                }
            }


            composableScreen(Nav.Home) {
                Button(onClick = { startDestination.value = Nav.Splash }) {
                    Text("Splash")
                }
            }
        }
    }
}

fun NavGraphBuilder.composableScreen(route: String, content: @Composable (NavBackStackEntry) -> Unit = {}) {
    composable(route) {
        val lifecycleState = LocalLifecycleOwner.current.lifecycle.collectState()

        Column {
            Text("Screen: $route")
            Text("State: $lifecycleState")
            Text("Max Lifecycle: ${it.maxLifecycle}")
            content(it)
        }
    }
}

@Composable
fun Lifecycle.collectState(): Lifecycle.State {
    var state by remember { mutableStateOf(currentState) }
    DisposableEffect(this) {
        val listener = LifecycleEventObserver { _, _ ->
            state = currentState
        }
        addObserver(listener)
        onDispose {
            removeObserver(listener)
        }
    }
    return state
}


package com.wakosen.janken

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.wakosen.janken.ui.home.HandType
import com.wakosen.janken.ui.home.HomeScreen
import com.wakosen.janken.ui.theme.result.ResultScreen
import com.wakosen.janken.ui.theme.JankenTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JankenTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    JankenApp()
                }//a
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JankenApp() {
    val navController = rememberNavController()
    Scaffold { paddingValues ->
        NavHost(
            navController = navController,
            // 初回起動時に表示される画面
            startDestination = "HOME",
            modifier = Modifier.padding(paddingValues = paddingValues)
        ) {
            // home画面
            composable(route = "HOME") {
                HomeScreen(navController)
            }
            // result画面
            // homeで選んだ手を受け取りたいので記述が増える
            composable(
                route = "RESULT/{hand_type}",
                arguments = listOf(
                    // じゃんけんの手の番号をresultに渡す
                    navArgument("hand_type") {
                        type = NavType.IntType
                    }
                )
            ) { entry ->
                // 受け取った手の番号をHandTypeに直してresult画面に渡す
                val handType = HandType.values()[entry.arguments?.getInt("hand_type") ?: 0]
                ResultScreen(navController, handType)
            }
        }
    }
}
package com.easy.base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.base.designsystem.theme.DefaultTheme
import com.easy.sample.home.FeatureListScreen
import com.easy.sample.home.FeatureViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      DefaultTheme {
        // A surface container using the 'background' color from the theme
        Surface(
          modifier = Modifier.fillMaxSize(),
          color = MaterialTheme.colorScheme.background
        ) {
          val navController = rememberNavController()
          NavHost(navController = navController, startDestination = "home") {
            composable(route = "home") {
              val featureViewModel: FeatureViewModel = hiltViewModel()
              val featureState by featureViewModel.featureState.collectAsStateWithLifecycle()
              FeatureListScreen(state = featureState, handleEvent = {
              })
            }
          }
        }
      }
    }
  }
}

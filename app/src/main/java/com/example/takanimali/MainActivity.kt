package com.example.takanimali

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.takanimali.navigation.AppNavHost
import com.example.takanimali.ui.auth.AuthViewModel
import com.example.takanimali.ui.collect.CollectViewModel
import com.example.takanimali.ui.register.RegisterViewModel
import com.example.takanimali.ui.report.ReportViewModel
import com.example.takanimali.ui.theme.TakaNiMaliTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TakaNiMaliTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.secondary
                ) {

                    val reportViewModel = hiltViewModel<ReportViewModel>()
                    val authViewModel = hiltViewModel<AuthViewModel>()
                    val collectViewModel = hiltViewModel<CollectViewModel>()
                    val registerViewModel = hiltViewModel<RegisterViewModel>()

                    AppNavHost()

                }
            }
        }
    }
}



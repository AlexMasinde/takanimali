package com.dca.takanimali

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.dca.takanimali.navigation.AppNavHost
import com.dca.takanimali.ui.auth.AuthViewModel
import com.dca.takanimali.ui.collect.CollectViewModel
import com.dca.takanimali.ui.register.RegisterViewModel
import com.dca.takanimali.ui.report.ReportViewModel
import com.dca.takanimali.ui.theme.TakaNiMaliTheme
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


                    AppNavHost()

                }
            }
        }
    }
}



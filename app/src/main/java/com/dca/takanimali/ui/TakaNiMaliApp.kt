//package com.example.takanimali.ui
//
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material.*
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.res.stringResource
//import androidx.lifecycle.viewmodel.compose.viewModel
//import com.example.takanimali.navigation.AppNavHost
//import com.example.takanimali.ui.auth.AuthViewModel
//import com.example.takanimali.ui.collect.CollectViewModel
//import com.example.takanimali.ui.register.RegisterViewModel
//import com.example.takanimali.ui.report.ReportViewModel
//
//@Composable
//fun TakaNiMaliApp(modifier: Modifier = Modifier) {
//    Scaffold(
//        modifier = modifier.fillMaxSize(),
//    ) {
//        Surface(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(it),
//            color = MaterialTheme.colors.background
//        ) {
//            val authViewModel: AuthViewModel = viewModel(factory = AuthViewModel.Factory)
//            val registerViewModel: RegisterViewModel = viewModel(factory = RegisterViewModel.Factory)
//            val reportViewModel: ReportViewModel = viewModel(factory = ReportViewModel.Factory)
//            val collectViewModel: CollectViewModel = viewModel(factory = CollectViewModel.Factory)
//
//            AppNavHost(authViewModel, registerViewModel, reportViewModel, collectViewModel)
//        }
//    }
//}
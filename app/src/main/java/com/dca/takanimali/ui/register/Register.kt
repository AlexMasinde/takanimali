package com.dca.takanimali.ui.register

//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.material.MaterialTheme
//import androidx.compose.material.Surface
//import androidx.compose.material.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import com.example.takanimali.ui.reusablecomponents.Input
//import com.example.takanimali.ui.reusablecomponents.PasswordInput
//import com.example.takanimali.ui.reusablecomponents.PrimaryButton
//
//@Composable
//fun Register() {
//    Surface(
//        modifier = Modifier
//            .background(MaterialTheme.colors.background)
//            .fillMaxHeight()
//            .fillMaxWidth(),
//    ) {
//        Column(
//            modifier = Modifier
//                .background(MaterialTheme.colors.background)
//                .fillMaxWidth()
//                .fillMaxHeight(),
//            verticalArrangement = Arrangement.Center,
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Box(modifier = Modifier.padding(bottom = 10.dp, top = 70.dp)) {
//                Text(
//                    text = "Welcome to Taka ni Mali",
//                    style = MaterialTheme.typography.h2,
//                )
//            }
//            Box {
//                Text(
//                    text = "Please fill the details below to continue",
//                    style = MaterialTheme.typography.body1
//                )
//            }
//            Box(modifier = Modifier.padding(bottom = 40.dp)){
//                Text(
//                    text = "Ensure you provide accurate location details",
//                    style = MaterialTheme.typography.body1
//                )
//            }
//            Input(value = "Your Name", placeholder = "Your Name") // Name
//            Input(value = "Email", placeholder = "Email") //Email
//            PasswordInput(value = "Password", placeholder = "Password") //Password
//            PasswordInput(value = "Confirm Password", placeholder = "Confirm Password") //Confirm password
//            Input(value= "Camp", placeholder = "Camp") //Camp
//            Input(value = "Zone", placeholder = "Zone") //Zone
//            Input(value = "Block", placeholder = "Block") //Block
//            PrimaryButton(buttonText = "Register")
//        }
//    }
//}
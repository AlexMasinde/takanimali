package com.example.takanimali.ui.terms

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.takanimali.ui.reusablecomponents.PageHeader


@Composable
fun Terms(navController: NavController, modifier: Modifier = Modifier) {
    Column() {
        PageHeader(text = "Terms", navController = navController, "home")
        Column(
            modifier
                .padding(start = 24.dp, end = 24.dp, bottom = 24.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Box(modifier.padding(top = 24.dp)) {
                Text(
                    text = "Terms and Conditions for Taka ni mali Digital Platform are as below. DCA reserves the right to modify and change the terms and conditions of use as it may find it appropriate and from time to time without notice.",
                    style = MaterialTheme.typography.h6,
                    lineHeight = 25.sp
                )
            }
            ConditionsContainer(
                title = "1. Usage",
                text = "The platform will be used solely for the purpose of promoting waste management."
            )
            ConditionsContainer(
                title = "2. Copyright",
                text = "The copyright of the platform is fully owned by DCA Kenya and no information material of the same can be shared or used without written authority from DCA Kenya."
            )
            ConditionsContainer(
                title = "3. Limitation of Liability",
                text = "DCA will bear no liability or responsibility for errors which may result from usage of the digital application content."
            )
            ConditionsContainer(
                title = "4. Intellectual Property",
                text = "No part of the content on the application including contents, logo and other visual media created can be replicated without express authority from DCA."
            )
            ConditionsContainer(
                title = "5. Termination",
                text = "Any person that use the application in an abusive manner will result in termination at the sole discretion of DCA."
            )
            ConditionsContainer(
                title = "6. Privacy",
                text = "Privacy policy-Privacy of the application user’s personal data must be highly secured and may not be accessible without consent."
            )
            ConditionsContainer(
                title = "7. Governing Law",
                text = "Legal jurisdiction of the Laws of Kenya will apply in cases of dispute."
            )
            ConditionsContainer(
                title = "8. Technical Specifications",
                text = "DCA reserves the right to modify the technical specifications of the application as it sees appropriate at any time."
            )


        }
    }
}


@Composable
fun ConditionsContainer(modifier: Modifier = Modifier, title: String, text: String) {
    Column(modifier.padding(vertical = 9.dp)) {
        Box(modifier.padding(vertical = 4.dp)) {
            Text(text = title, style = MaterialTheme.typography.h4)
        }
        Box() {
            Text(
                text = text,
                style = MaterialTheme.typography.h6,
                lineHeight = 25.sp
            )
        }
    }
}
package com.dca.takanimali.ui.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dca.takanimali.ui.home.HomeNavigationItem


@Composable
fun NavigationCard(
    navigationItem: HomeNavigationItem,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier.clickable(
            enabled = true,
            onClick = { navController.navigate(navigationItem.navDestination) }),
        elevation = 3.dp,
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .height(140.dp)
                .width(156.dp)
        ) {
            Image(
                painter = painterResource(id = navigationItem.icon),
                contentDescription = stringResource(id = navigationItem.title)
            )
            Text(
                text = stringResource(id = navigationItem.title),
                style = MaterialTheme.typography.h4,
                modifier = modifier.padding(top = 12.dp)
            )
            Text(
                text = stringResource(id = navigationItem.subtitle),
                style = MaterialTheme.typography.subtitle2,
                modifier = modifier.padding(top = 4.dp)
            )
        }
    }
}


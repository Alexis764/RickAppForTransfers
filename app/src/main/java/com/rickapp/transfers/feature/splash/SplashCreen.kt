package com.rickapp.transfers.feature.splash

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.rickapp.transfers.R
import com.rickapp.transfers.navigation.Routes
import com.rickapp.transfers.ui.theme.Primary

@Composable
fun SplashScreen(
    navController: NavHostController = rememberNavController(),
    splashViewModel: SplashViewModel = hiltViewModel()
) {
    val startNextScreen: Boolean by splashViewModel.startNextScreen.observeAsState(false)
    val logoTypeSelected: LogoType by splashViewModel.logoTypeSelected.observeAsState(LogoType.WithoutColor)

    if (startNextScreen) {
        navController.popBackStack()
        navController.navigate(Routes.HomeScreen)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Primary),
        contentAlignment = Alignment.Center
    ) {
        Crossfade(targetState = logoTypeSelected, label = "Logo") { logoTypeSelected ->
            when (logoTypeSelected) {
                LogoType.WithoutColor -> {
                    Image(
                        imageVector = ImageVector.vectorResource(id = R.drawable.logo_whitout_color),
                        contentDescription = null,
                        modifier = Modifier.size(250.dp)
                    )
                }
                LogoType.WithColor -> {
                    Image(
                        painter = painterResource(id = R.drawable.loco_color),
                        contentDescription = null,
                        modifier = Modifier.size(250.dp)
                    )
                }
            }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
private fun SplashPreview() {
    SplashScreen()
}
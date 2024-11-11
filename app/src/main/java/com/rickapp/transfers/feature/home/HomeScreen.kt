package com.rickapp.transfers.feature.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rickapp.transfers.R
import com.rickapp.transfers.ui.theme.Accent
import com.rickapp.transfers.ui.theme.Jaro
import com.rickapp.transfers.ui.theme.Primary
import com.rickapp.transfers.ui.theme.PrimaryDark

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val characterList = homeViewModel.characterList
    val characterSelected by homeViewModel.characterSelected.observeAsState(null)

    val backgroundGradient = Brush.verticalGradient(listOf(Primary, PrimaryDark, PrimaryDark))
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundGradient)
    ) {
        if (characterSelected == null) {
            CharacterNoSelected(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) { homeViewModel.getRandomCharacter() }

        } else {

        }

        LazyRow(
            contentPadding = PaddingValues(20.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {

        }
    }
}


@Composable
fun CharacterNoSelected(
    modifier: Modifier = Modifier,
    getRandomCharacter: () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.loco_color),
            contentDescription = null,
            modifier = Modifier.size(250.dp)
        )

        Text(
            text = "Personaje no\nseleccionado",
            color = Color.White,
            fontSize = 32.sp,
            fontFamily = Jaro
        )
        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = { getRandomCharacter() },
            colors = ButtonDefaults.buttonColors(containerColor = Accent),
            border = BorderStroke(2.dp, Primary),
            shape = ShapeDefaults.Medium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(60.dp)
        ) {
            Text(
                text = "Random",
                color = PrimaryDark,
                fontSize = 28.sp,
                fontFamily = Jaro,
                modifier = Modifier.padding(vertical = 2.dp)
            )
        }
    }
}
@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.rickapp.transfers.feature.home

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.rickapp.transfers.R
import com.rickapp.transfers.feature.home.data.Character
import com.rickapp.transfers.ui.theme.Accent
import com.rickapp.transfers.ui.theme.Gotu
import com.rickapp.transfers.ui.theme.Jaro
import com.rickapp.transfers.ui.theme.Primary
import com.rickapp.transfers.ui.theme.PrimaryDark
import com.rickapp.transfers.ui.theme.Secondary

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val characterList = homeViewModel.characterList
    val characterSelected by homeViewModel.characterSelected.observeAsState(null)

    val backgroundGradient = Brush.verticalGradient(listOf(Primary, PrimaryDark, PrimaryDark))
    SharedTransitionLayout {
        AnimatedContent(
            targetState = characterSelected == null,
            label = ""
        ) { isCharacterSelected ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(backgroundGradient)
            ) {
                if (isCharacterSelected) {
                    CharacterNoSelected(
                        animatedVisibilityScope = this@AnimatedContent,
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                    ) { homeViewModel.getRandomCharacter() }

                } else {
                    CharacterSelected(
                        animatedVisibilityScope = this@AnimatedContent,
                        character = characterSelected ?: Character(),
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                    ) { homeViewModel.clearCharacterSelected() }
                }

                LazyRow(
                    contentPadding = PaddingValues(20.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(characterList) { character ->
                        CharacterItem(
                            character = character,
                            isSelected = character == characterSelected,
                            shapeValue = 16.dp
                        ) { homeViewModel.changeCharacterSelected(it) }
                    }
                }
            }
        }
    }
}

@Composable
fun CharacterItem(
    character: Character,
    isSelected: Boolean,
    shapeValue: Dp,
    modifier: Modifier = Modifier,
    changeCharacterSelected: (Character) -> Unit
) {
    Card(
        modifier = modifier
            .size(125.dp)
            .clickable { changeCharacterSelected(character) },
        shape = RoundedCornerShape(shapeValue),
        border = if (isSelected) BorderStroke(3.dp, Accent) else BorderStroke(2.dp, Primary)
    ) {
        AsyncImage(
            model = character.image,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}


@Composable
fun SharedTransitionScope.CharacterSelected(
    animatedVisibilityScope: AnimatedVisibilityScope,
    character: Character,
    modifier: Modifier = Modifier,
    clearCharacterSelected: () -> Unit
) {
    Column(
        modifier = modifier.padding(horizontal = 20.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            contentAlignment = Alignment.CenterEnd
        ) {
            Image(
                painter = painterResource(id = R.drawable.loco_color),
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .offset(x = 24.dp)
                    .clickable {
                        clearCharacterSelected()
                    }
                    .sharedElement(
                        state = rememberSharedContentState(key = "logo"),
                        animatedVisibilityScope = animatedVisibilityScope,
                        boundsTransform = { _, _ -> tween(1000) }
                    )
            )
        }

        Row(Modifier.fillMaxWidth()) {
            CharacterItem(
                character = character,
                isSelected = true,
                shapeValue = 100.dp,
                modifier = Modifier.size(150.dp)
            ) {}

            Spacer(modifier = Modifier.width(12.dp))

            Column {
                MyCategoryCharacter("Nombre", character.name)
            }
        }
        Spacer(modifier = Modifier.height(12.dp))

        Column {
            MyCategoryCharacter("Especie", character.species)
            MyCategoryCharacter("Estado", character.status)

            MyTitle("Genero")
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                MySubtitle(character.gender)
                val genderIcon = when (character.gender.lowercase()) {
                    "female" -> R.drawable.hembra
                    "male" -> R.drawable.masculino
                    else -> R.drawable.masculino
                }
                Image(
                    painter = painterResource(id = genderIcon),
                    contentDescription = null,
                    modifier = Modifier.size(12.dp)
                )
            }
            Spacer(modifier = Modifier.height(12.dp))

            MyCategoryCharacter("Origen", character.origin.name)
        }
    }
}

@Composable
fun MyCategoryCharacter(title: String, subtitle: String) {
    MyTitle(title)
    MySubtitle(subtitle)
    Spacer(modifier = Modifier.height(12.dp))
}

@Composable
fun MySubtitle(text: String) {
    Text(text = text, fontSize = 20.sp, color = Color.White, fontFamily = Gotu)
}

@Composable
fun MyTitle(text: String) {
    Text(text = text, fontSize = 26.sp, color = Secondary, fontFamily = Jaro)
}


@Composable
fun SharedTransitionScope.CharacterNoSelected(
    animatedVisibilityScope: AnimatedVisibilityScope,
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
            modifier = Modifier
                .size(250.dp)
                .sharedElement(
                    state = rememberSharedContentState(key = "logo"),
                    animatedVisibilityScope = animatedVisibilityScope,
                    boundsTransform = { _, _ -> tween(1000) }
                )
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
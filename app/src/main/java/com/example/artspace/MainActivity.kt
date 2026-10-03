package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

// Modèle de données pour une œuvre d'art
data class Artwork(
    val imageRes: Int,
    val titleRes: Int,
    val artistRes: Int,
    val yearRes: Int
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpaceLayout()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceLayout() {
    val artworkList = listOf(
        Artwork(
            imageRes = R.drawable.image,
            titleRes = R.string.artwork_title1,
            artistRes = R.string.artwork_artist1,
            yearRes = R.string.artwork_year1
        ),
        Artwork(
            imageRes = R.drawable.image2,
            titleRes = R.string.artwork_title2,
            artistRes = R.string.artwork_artist2,
            yearRes = R.string.artwork_year2
        ),
        Artwork(
            imageRes = R.drawable.image3,
            titleRes = R.string.artwork_title3,
            artistRes = R.string.artwork_artist3,
            yearRes = R.string.artwork_year3
        )
    )

    var currentIndex by remember { mutableIntStateOf(0) }
    val currentArtwork = artworkList[currentIndex]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Affichage de l'image
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Surface(
                shadowElevation = 8.dp,
                color = Color.White
            ) {
                Image(
                    painter = painterResource(currentArtwork.imageRes),
                    contentDescription = stringResource(currentArtwork.titleRes),
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.padding(32.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Affichage des informations de l'œuvre
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
                .background(Color(0xFFECEFF1))
                .padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = stringResource(currentArtwork.titleRes),
                fontSize = 22.sp,
                fontWeight = FontWeight.Light,
                color = Color.Black
            )
            Row(
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Text(
                    text = stringResource(currentArtwork.artistRes),
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = Color.Black
                )
                Text(
                    text = " " + stringResource(currentArtwork.yearRes),
                    fontWeight = FontWeight.Light,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Boutons de navigation (Previous / Next)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    if (currentIndex == 2) {
                        currentIndex = 1
                    } else if (currentIndex == 1) {
                        currentIndex = 0
                    } else {
                        currentIndex = 2
                    }
                },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "Previous")
            }

            Spacer(modifier = Modifier.width(16.dp))

            Button(
                onClick = {
                    if (currentIndex == 0) {
                        currentIndex = 1
                    } else if (currentIndex == 1) {
                        currentIndex = 2
                    } else {
                        currentIndex = 0
                    }
                },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "Next")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArtSpaceTheme {
        ArtSpaceLayout()
    }
}

package com.example.mixlayoutapplication.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.mixlayoutapplication.MainViewModel
import com.example.mixlayoutapplication.R
import com.example.mixlayoutapplication.data.NationalPark

@Composable
fun ComposeContent(modifier: Modifier = Modifier, viewModel: MainViewModel) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    MaterialTheme(
        colorScheme = if (isSystemInDarkTheme()) darkColorScheme() else lightColorScheme()
    ) {
        var snackbarMessage by remember { mutableStateOf("") }
        val snackbarHostState = remember { SnackbarHostState() }

        Scaffold(
            modifier = modifier.padding(bottom = 50.dp),
            snackbarHost = { TopSnackbarContainer(snackbarHostState) }
        ) { padding ->
            NationalParkList(
                modifier = modifier,
                padding = padding,
                nationalParks = uiState.nationalParksAlaska,
                onClick = { newMessage: String -> snackbarMessage = newMessage}
            )

            LaunchedEffect(key1 = snackbarMessage) {
                if (snackbarMessage.isNotBlank()) {
                    snackbarHostState.showSnackbar(
                        message = snackbarMessage,
                        duration = SnackbarDuration.Short
                    )
                    snackbarMessage = "" // Reset the message after displaying
                }
            }
        }
    }
}

@Composable
fun TopSnackbarContainer(
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp) // Add padding as needed
    ) {
        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun NationalParkList(
    modifier: Modifier = Modifier,
    padding: PaddingValues,
    nationalParks: List<NationalPark>,
    onClick: (String) -> Unit,
) {
    Column(
        modifier = modifier
            .padding(padding)
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        ParkTitle(modifier = modifier)
        ParkList(modifier = modifier, parks = nationalParks, onClick)
    }
}

@Composable
fun ParkTitle(modifier: Modifier = Modifier) {
    Text(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        text = "Alaska National Parks",
        style = MaterialTheme.typography.titleLarge,
        textAlign = TextAlign.Center
    )
}

@Composable
fun ParkList(modifier: Modifier = Modifier, parks: List<NationalPark>, onClick: (String) -> Unit) {
    LazyColumn {
        items(parks) { park ->
            ParkContent(
                modifier = modifier.padding(8.dp),
                park = park,
                onClick
            )
        }
    }
}

@Composable
fun ParkContent(modifier: Modifier = Modifier, park: NationalPark, onClick: (String) -> Unit) {
    AsyncImage(
        model = park.imageUrl,
        contentDescription = "Park image", // Provide a meaningful description
        modifier = modifier
            .fillMaxWidth()
            .height(250.dp),
        contentScale = ContentScale.Crop // Adjust scaling as needed
    )
    Text(
        modifier = modifier.padding(top = 0.dp, bottom = 0.dp),
        text = park.name,
        maxLines = 1,
        style = MaterialTheme.typography.titleMedium
    )
    Text(
        modifier = modifier.padding(top = 0.dp, bottom = 0.dp),
        text = park.description,
        maxLines = 7,
        overflow = TextOverflow.Ellipsis,
        style = MaterialTheme.typography.bodySmall
    )
    Row(verticalAlignment = Alignment.CenterVertically) {
        Button(
            modifier = modifier
                .padding(start = 8.dp, end = 8.dp)
                .width(150.dp),
            onClick = { onClick("Visit ${park.name}") }) {
            Text(text = "Visit")
        }
        Image(
            modifier = Modifier
                .height(IntrinsicSize.Min) // Match height to Button
                .width(IntrinsicSize.Min)
                .padding(top = 8.dp, bottom = 8.dp, start = 4.dp, end = 4.dp)
                .clickable { onClick("Navigate ${park.name}")}, // Maintain aspect ratio.clickable { println("nannandenden navigate clicked") },
            contentScale = ContentScale.Fit,
            painter = painterResource(R.drawable.baseline_assistant_navigation_24),
            contentDescription = "navigation"
        )
    }
}

@Preview
@Composable
fun ParkTitlePreview() {
    ParkTitle(Modifier)
}

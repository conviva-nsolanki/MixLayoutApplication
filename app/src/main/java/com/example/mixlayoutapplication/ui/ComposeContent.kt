package com.example.mixlayoutapplication.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
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
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
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
    nationalParks: List<NationalPark> = emptyList(),
    onClick: (String) -> Unit = {},
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
fun ParkTitle(modifier: Modifier = Modifier, name: String = "Alaska National Parks") {
    Text(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        text = name,
        style = MaterialTheme.typography.titleLarge,
        textAlign = TextAlign.Center
    )
}
@Preview
@Composable
fun ParkTitlePreview() {
    ParkTitle(name = "Colorado National Park")
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
fun ParkContent(
    modifier: Modifier = Modifier,
    park: NationalPark,
    onClick: (String) -> Unit = {}
) {
    Column(modifier) {
        ParkImage(modifier, park.imageUrl)
        TitleText(modifier, park.name)
        DescriptionText(modifier, park.description)
        ParkActionRwoItems(modifier, park.name, onClick)
    }
}
@Preview
@Composable
fun ParkContentPreview() {
    ParkContent(park = NationalPark(
        url = "",
        name = "Yellow Stone",
        description = "Largest National Park in US.",
        imageUrl = ""
    ))
}

@Composable
fun ParkActionRwoItems(modifier: Modifier, name: String, onClick: (String) -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        // Visit park button
        LogAnalyticsButton(
            modifier = modifier
                .padding(start = 8.dp, end = 8.dp)
                .width(150.dp),
            onClick = {
                onClick("Visit ${name}")
            },
            eventValue = name) {
            Text(text = "Visit")
        }
        // Navigation Image
        Image(
            modifier = modifier
                .height(IntrinsicSize.Min) // Match height to Button
                .width(IntrinsicSize.Min)
                .padding(top = 8.dp, bottom = 8.dp, start = 4.dp, end = 4.dp)
                .clickable {
                    onClick("Navigate ${name}")
                },
            contentScale = ContentScale.Fit,
            painter = painterResource(R.drawable.baseline_assistant_navigation_24),
            contentDescription = "navigation"
        )
    }
}

@Composable
fun DescriptionText(modifier: Modifier, description: String) {
    Text(
        modifier = modifier.padding(top = 0.dp, bottom = 0.dp),
        text = description,
        maxLines = 7,
        overflow = TextOverflow.Ellipsis,
        style = MaterialTheme.typography.bodySmall
    )
}

@Composable
fun TitleText(modifier: Modifier, name: String) {
    Text(
        modifier = modifier.padding(top = 0.dp, bottom = 0.dp),
        text = name,
        maxLines = 1,
        style = MaterialTheme.typography.titleMedium
    )
}

@Composable
fun ParkImage(modifier: Modifier, imageUrl: String) {
    AsyncImage(
        model = imageUrl,
        contentDescription = "Park image", // Provide a meaningful description
        modifier = modifier
            .fillMaxWidth()
            .height(250.dp),
        contentScale = ContentScale.Crop // Adjust scaling as needed
    )
}

/**
 * create a custom clickable modifier using existing
 * [clickable] modifier that can be reused
 */
@Composable
fun Modifier.logEventClickable(
    eventValue: String,
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    onClick: () -> Unit
): Modifier = this.clickable(
    enabled,
    onClickLabel,
    role,
    onClick = {
        // Log event
//        ConvivaAppAnalytics
//            .getDefaultTracker()
//            ?.trackCustomEvent("button_click", JSONObject().also {
//                it.put("Park name", eventValue)
//            })
        onClick.invoke()
    }
)


/**
 * create a custom button that log event when click action happens
 */
@Composable
fun LogAnalyticsButton(
    eventValue: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = ButtonDefaults.shape,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    elevation: ButtonElevation? = ButtonDefaults.buttonElevation(),
    border: BorderStroke? = null,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable RowScope.() -> Unit
) {
    Button(
        onClick = {
            // Log event
//            ConvivaAppAnalytics
//                .getDefaultTracker()
//                ?.trackCustomEvent("button_click", JSONObject().also {
//                    it.put("Park name", eventValue)
//                })
            onClick.invoke()
        },
        modifier,
        enabled,
        shape,
        colors,
        elevation,
        border,
        contentPadding,
        interactionSource,
        content
    )
}


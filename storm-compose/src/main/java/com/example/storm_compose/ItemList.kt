package com.example.storm_compose

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp


@Composable()
fun OnBoardingScreen(
    modifier: Modifier = Modifier,
    finishClickCallback: () -> Unit,
    welcomeMessage: String = "Welcome to Sample App",
    buttonText: String = "Finish OnBoarding"
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier.fillMaxSize()
    ) {
        Text(text = welcomeMessage)
        OutlinedButton(
            onClick = finishClickCallback,
            modifier = Modifier.padding(vertical = 10.dp)
        ) {
            Text(text = buttonText)
        }
    }
}

@Composable()
fun ItemList(
    persons: List<Pair<String, String>>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier.padding(vertical = 8.dp)) {
        items(items = persons) { person ->
            Greeting(name = person.first, infoMessage = person.second)
        }
    }
}

@Composable
fun Greeting(name: String, infoMessage: String, modifier: Modifier = Modifier) {

    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
    ) {
        CardContent(name, infoMessage)
    }
}

@Composable
fun CardContent(name: String, infoMessage: String) {

    var expandedState by rememberSaveable {
        mutableStateOf(false)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 10.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "Hello :)"
            )
            Text(
                text = if (expandedState) {
                    infoMessage
                } else {
                    name
                },
                style = MaterialTheme.typography.headlineSmall,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold,
            )
        }
        ElevatedButton(onClick = {
            expandedState = !expandedState
        }) {
            Text(
                if (expandedState) {
                    "Less . ."
                } else {
                    "More About Me . ."
                }
            )
        }
    }
}

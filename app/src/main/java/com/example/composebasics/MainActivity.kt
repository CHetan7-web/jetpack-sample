package com.example.composebasics

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.composebasics.ui.theme.ComposeBasicsTheme
import com.example.storm_compose.ItemList
import com.example.storm_compose.OnBoardingScreen
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBasicsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp()
                }
            }
        }
    }
}

@Composable
fun MyApp() {
    var showOnBoarding by remember {
        mutableStateOf(true)
    }

    if (showOnBoarding) {
        OnBoardingScreen(
            finishClickCallback = { showOnBoarding = false },
            welcomeMessage = "Welcome to Sample App",
            buttonText = "Finish OnBoarding"
        )
    } else {
        ItemList(persons = List(100) { index ->
            Pair(
                "Number $index",
                "More Info about me : My id is $index".repeat(Random.nextInt(from = 1, until = 8))
            )
        })
    }
}

@Preview(
    showBackground = true,
    widthDp = 300,
    heightDp = 300,
    uiMode = UI_MODE_NIGHT_YES,
    name = "Dark"
)
@Preview(showBackground = true, widthDp = 300, heightDp = 300)
@Composable
fun GreetingPreview() {
    ComposeBasicsTheme {
        MyApp()
    }
}

@Preview(showBackground = true, widthDp = 300, heightDp = 300)
@Composable()
fun OnBoardingPreview() {
    ComposeBasicsTheme {
        OnBoardingScreen(finishClickCallback = {})
    }
}
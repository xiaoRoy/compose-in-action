package com.learn.growthcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import com.learn.growthcompose.ui.theme.GrowthComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setComposeView()
//        setContent {
//            GrowthComposeTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
//                    Greeting("Android")
//                }
//            }
//        }
    }

    private fun setComposeView() {
        val composeView = ComposeView(this)
        composeView.setContent {
            Text(text = "Hello World!")
        }
        setContentView(composeView)
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GrowthComposeTheme {
        Greeting("Android")
    }
}

package com.rantmedia.jetpackcomposedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rantmedia.jetpackcomposedemo.ui.theme.JetpackComposeDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainComposable {
                ScreenContent()
            }
        }
    }
}

@Composable
fun MainComposable(content: @Composable () -> Unit) {
    JetpackComposeDemoTheme {
        // Define a Surface to set a different background color.
        Surface(color = MaterialTheme.colors.background) {
            content()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainComposable {
        ScreenContent()
    }
}

@Composable
fun ScreenContent() {
    Column {
        NewsStory(header = "Jetpack Compose", body1 = "Cardiff", body2 = "June 2021")
        NewsStory(
            header = "A day wandering through the sandhills in Shark Fin Cove, and a few of the sights I saw",
            body1 = "Davenport, California",
            body2 = "December 2018"
        )
        Counter()
    }
}

@Composable
fun NewsStory(header: String, body1: String, body2: String) {
    val typography = MaterialTheme.typography
    Column(modifier = Modifier.padding(16.dp)) {
        Image(
            painter = painterResource(id = R.drawable.header_image),
            contentDescription = null,
            modifier = Modifier
                .height(180.dp)
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(4.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = header,
            style = typography.h6,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
        Text(body1, style = typography.body2)
        Text(body2, style = typography.body2)
    }
}

@Composable
fun Counter() {
    val count = remember { mutableStateOf(0) }
    Button(
        onClick = { count.value++ },
        modifier = Modifier.padding(start = 16.dp)
    ) {
        Text("I've been clicked ${count.value} times")
    }
}
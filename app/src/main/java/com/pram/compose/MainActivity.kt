package com.pram.compose

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pram.compose.ui.theme.ComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTheme {


                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    ScrollBoxes()
//                }
                ScrollBoxes()
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
private fun ScrollBoxes() {
    var showText by remember { mutableStateOf(false) }
    var showButton by remember { mutableStateOf(false) }
    var size = 6
    val context = LocalContext.current
    var text1 by remember { mutableStateOf("") }
    Column(
        Modifier.fillMaxSize()
    ) {
        Row(
            Modifier
                .fillMaxWidth(1f)
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            repeat(size) {
                Button(onClick = {
                    text1 = it.toString()
                    ShowToast(text1 = text1, context)
                    showButton = true
                }) {
                    Text(text = "Android $it")
                }
            }
        }
        if (showButton) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                Modifier.fillMaxWidth()
            ) {
                item {

                    Button(
                        onClick = { showText = true },
                        enabled = !showText,
                        modifier = Modifier.weight(0.5f)
                    ) {
                        Text(text = "Show Text")
                    }
                }

                item {
                    Button(
                        onClick = { showText = false },
                        enabled = showText,
                        modifier = Modifier.weight(0.5f)
                    ) {
                        Text(text = "Hide Text")
                    }
                }

            }

        }
        if (showText) {
//                    LazyColumn(Modifier.fillMaxWidth()) {
//                        items.forEach { item { ListItem(item = it) } }
//                    }
            Text(text = text1)
        }
    }
}

@Composable
fun ListItem(item: Item) {
    LazyVerticalGrid(columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(5.dp),
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        content = {
//            Image(painter = painterResource(id = item.image), contentDescription = )
            item {
                Text(text = item.text)
            }
        })
}

fun ShowToast(text1: String, context: Context) {
    Toast.makeText(context, text1, Toast.LENGTH_SHORT).show()
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeTheme {
        ScrollBoxes()
    }
}

val items = listOf(Item("", "Text 1"), Item("", "Text 2"), Item("", "Text 3"))

data class Item(
    var image: String,
    var text: String
)
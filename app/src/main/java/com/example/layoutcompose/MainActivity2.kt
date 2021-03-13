package com.example.layoutcompose

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Adb
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.layoutcompose.ui.theme.LayoutComposeTheme
import dev.chrisbanes.accompanist.coil.CoilImage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LayoutComposeTheme {
                LayoutsCodelab()
            }
        }
    }
}

@Composable
fun LayoutsCodelab() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "LayoutsCodelab")
                },
                actions = {
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(Icons.Sharp.Adb, contentDescription = null)
                    }
                }
            )
        }
    ) { innerPadding ->
        BodyContent(
            Modifier
                .padding(innerPadding)
                .padding(8.dp))
    }
}

@Composable
fun BodyContent(modifier: Modifier = Modifier) {
    // We save the scrolling position with this state that can also
    // be used to programmatically scroll the list
    val scrollState = rememberLazyListState()
    // We save the coroutine scope where our animated scroll will be executed
    val coroutineScope = rememberCoroutineScope()

    Column(modifier = modifier) {
        Text("Hi there!")
        Text(text = "Thanks for going through the Layouts codelab")
        Divider(color = Color.Blue, thickness = 3.dp)
        ButtonsListsOrder(scrollState, coroutineScope)
        Divider(color = Color.Blue, thickness = 3.dp)
        LazyList(scrollState)
    }
}

@Composable
fun ButtonsListsOrder(scrollState: LazyListState, coroutineScope: CoroutineScope) {

    val listSize = 100

    Row {
        Button(onClick = {
            coroutineScope.launch {
                // 0 is the first item index
                scrollState.animateScrollToItem(0)
            }
        }) {
            Text("Scroll to the top")
        }

        Button(onClick = {
            coroutineScope.launch {
                // listSize - 1 is the last index of the list
                scrollState.animateScrollToItem(listSize - 1)
            }
        }) {
            Text("Scroll to the end")
        }
    }

}

@Composable
fun LazyList(scrollState: LazyListState) {

    LazyColumn(state = scrollState) {
        items(100) {
            ImageListItem(it)
        }
    }
}

@Composable
fun ImageListItem(index: Int) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        CoilImage(
            data = "https://developer.android.com/images/brand/Android_Robot.png",
            contentDescription = "Android Logo",
            modifier = Modifier.size(50.dp)
        )
        Spacer(Modifier.width(10.dp))
        Text("Item #$index", style = MaterialTheme.typography.subtitle1)
    }
}

@Preview
@Composable
fun LayoutsCodelabPreview() {
    LayoutComposeTheme {
        LayoutsCodelab()
    }
}

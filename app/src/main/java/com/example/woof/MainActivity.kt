package com.example.woof

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.woof.data.DataSource
import com.example.woof.model.Dog
import com.example.woof.ui.theme.WoofTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WoofTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    WoofApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WoofTopAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    modifier = Modifier
                        .size(64.dp)
                        .padding(8.dp),
                    painter = painterResource(R.drawable.ic_woof_logo),
                    contentDescription = null
                )
                Text(
                    text = stringResource(R.string.app_name),
                    style = MaterialTheme.typography.displayLarge
                )
            }
        },
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WoofApp() {
    Scaffold (
        topBar = {
            WoofTopAppBar()
        }
    ) {it ->
        LazyColumn(contentPadding = it) {
            items(DataSource.dogs) {
                DogItem(
                    dog = it,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

@Composable
fun DogItem(
    dog: Dog,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Image(
                modifier = Modifier
                    .size(64.dp)
                    .padding(8.dp)
                    .clip(MaterialTheme.shapes.small),
                contentScale = ContentScale.Crop,
                painter = painterResource(dog.imageResourceId),
                contentDescription = null
            )

            Column {
                Text(
                    text = stringResource(dog.name),
                    style = MaterialTheme.typography.displayMedium,
                    modifier = Modifier.padding(top = 8.dp)
                )
                Text(
                    text = stringResource(R.string.years_old, dog.age),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

@Preview
@Composable
fun WoofPreview() {
    WoofTheme {
        WoofApp()
    }
}

@Preview
@Composable
fun WoofDarkThemePreview() {
    WoofTheme(darkTheme = true) {
        WoofApp()
    }
}
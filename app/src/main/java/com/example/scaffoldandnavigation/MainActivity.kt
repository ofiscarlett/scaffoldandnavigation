package com.example.scaffoldandnavigation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.scaffoldandnavigation.ui.theme.ScaffoldandnavigationTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.sp
import androidx.compose.material3.TopAppBar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScaffoldandnavigationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ScaffoldApp()
                }
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(title: String, navController: NavController){
    var expanded by remember { mutableStateOf(false) }
    TopAppBar(
        title = { Text(title, color = androidx.compose.ui.graphics.Color.White)},
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.tertiary
        ),
        actions = {
            IconButton(onClick = { expanded = !expanded }
            ) {
                Icon(Icons.Filled.MoreVert, contentDescription = null)
            }
            DropdownMenu(
                expanded = expanded,
                //google said modify like above
                //expanded = !expanded,
                onDismissRequest = { expanded = false }){
                DropdownMenuItem(text = { Text("Info") }, onClick = {
                    //add expanded = false,
                    expanded = false;
                    navController.navigate("info")})
                DropdownMenuItem(text = { Text("Settings") }, onClick = {
                    //add expanded = false,
                    expanded = false;
                    navController.navigate("settings")})

            }
        })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenTopBar(title: String, navController: NavController){
    TopAppBar(
        title = { Text(title, color = androidx.compose.ui.graphics.Color.White)},
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.tertiary
        ),
        navigationIcon = {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(Icons.Filled.MoreVert, contentDescription = null)
            }
        }
    /*
    TopAppBar(
        title = { Text(title) },
        navigationIcon = {
            IconButton(onClick = {navController.navigateUp() }) {
                Icon(Icons.Filled.MoreVert, contentDescription = null)
            }
        }
    )
    */
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavController) {
    Scaffold(
        topBar = { MainTopBar("My App", navController) },
        content = { innerPadding ->
            // You should place your content here
            Box(modifier = Modifier.padding(innerPadding)) {

                Text("Content for Home screen", color =androidx.compose.ui.graphics.Color.Black, fontSize = 25.sp)
            }}

  /*      content = {
            Text(text = "Content for Home screen")
        }
   */
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun InfoScreen(navController: NavController){
    Scaffold (
        topBar = { ScreenTopBar("info" , navController ) },
        content = {innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
            Text("Content for Info screen", color =androidx.compose.ui.graphics.Color.Black, fontSize = 25.sp)
            }    }

    //Text(text = "Content for Info screen") },
    )
}
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SettingsScreen(navController: NavController){
    Scaffold (
        topBar = { ScreenTopBar("Setting" , navController ) },
        content = {innerPadding -> Box (
            modifier = Modifier.padding(innerPadding)){
            Text("Content for setting screen", color =androidx.compose.ui.graphics.Color.Black, fontSize = 25.sp)
        }}
            //Text(text = "Content for Settings screen") },
    )
}
@Composable
fun ScaffoldApp(){
    val navController = rememberNavController()
    NavHost(
        navController = navController ,
        startDestination = "home" ){
        composable(route = "home"){
            MainScreen(navController)
        }
        composable(route = "Info"){
            InfoScreen(navController)
        }
        composable(route = "Settings"){
            SettingsScreen(navController)
        }

    }
}


/*
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}
*/
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ScaffoldandnavigationTheme {
        ScaffoldApp()
    }
}
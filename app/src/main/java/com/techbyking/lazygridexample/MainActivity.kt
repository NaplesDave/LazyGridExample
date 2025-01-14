package com.techbyking.lazygridexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.techbyking.lazygridexample.ui.theme.LazyGridExampleTheme


//David A. King  Nov 30 2024
// LazyGrid Example - Grid of Scrolling List of Cards
// Udemy Jetpack Compose Class - Lesson #37-43
// Oak Academy

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LazyGridExampleTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ){
                    // call navigation controller to route to page
                    MyNavigation()
                }//end surface content scope
            }//end LazyGridExample Theme
        }//end set Content
    }//end onCreate
}//end MainActivity fun


// Make Navigation Controller to switch pages / activities
@Composable
fun MyNavigation(){
    val navController = rememberNavController()

    // Create navController and set first screen
    NavHost(
        navController = navController,
        startDestination = "FirstPage"){

        // Declare routes to all pages, and data passing

        composable(route = "FirstPage"){
            FirstPage(navController = navController)
        }

        composable(route = "SecondPage/{id}",
            arguments = listOf(
                navArgument(name = "id"){type = NavType.IntType}
            )
        ){
            val countryId = it.arguments?.getInt("id")

            countryId?.let {id ->

                SecondPage(
                    navController = navController,
                    id = id
                )

            }



        }


    }//end NavGraphBuilder


}//end myNavigation function
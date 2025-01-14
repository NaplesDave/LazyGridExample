package com.techbyking.lazygridexample

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


//David A. King  Nov 30 2024
// LazyGrid Example - Grid of Scrolling List of Cards
// Udemy Jetpack Compose Class - Lesson #37-43
// Oak Academy


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FirstPage(navController: NavController) { // use navcontroller to switch pg

    // get country list
    val countryList = retrieveCountries()

    val myContext = LocalContext.current

    // var to track scrolling and topAppBar
    val topBarBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(

        // modifier for changing TopApBar color changes
        modifier = Modifier.nestedScroll(topBarBehavior.nestedScrollConnection),

        topBar = {
            TopAppBar(
                title ={Text(text = "Countries", fontSize = 20.sp)},
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = colorResource(R.color.purple_500),
                    titleContentColor = Color.White,
                    // color to change to when list scrolled into appBar
                    scrolledContainerColor = colorResource(R.color.purple_200)
                ),

                //update scrollBar about scroll action
                scrollBehavior = topBarBehavior

            )// end TopAppBar scope
        },//end topBar scope

        content = {
            // LazyVerticalGrid
            //LazyHorizontalGrid

            LazyVerticalGrid   (
                modifier = Modifier.padding(it),
                // GridCells is the Height of the grid row
                //rows = GridCells.Adaptive(250.dp)
                columns = GridCells.Adaptive(200.dp)
            ){

                items(
                    count = countryList.count(), // get # of items in list
                    itemContent = {index ->
                        val country = countryList[index]

                        //Card
                        Card(
                            onClick = {
                                Toast.makeText(
                                    myContext,
                                    "You selected ${country.countryName}",
                                    Toast.LENGTH_SHORT)
                                    .show()
                            },// end onClick scope
                            modifier = Modifier
                                //width not needed in Vert grid
                                //.width(170.dp)
                                //height not needed in horiz grid
                                .height(250.dp)
                                .padding(7.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = colorResource(id = R.color.purple_500)
                            ),
                            shape = RoundedCornerShape(10.dp),
                            elevation = CardDefaults.cardElevation(7.dp),
                            border = BorderStroke(2.dp, Color.Red)
                        ) {
                            // create main Top Column...card
                            Column (
                                modifier = Modifier.fillMaxSize()
                                    .padding(7.dp),
                                horizontalAlignment = Alignment
                                    .CenterHorizontally,
                                verticalArrangement = Arrangement.SpaceBetween

                            ){  // Top with components
                                Column(horizontalAlignment = Alignment
                                    .CenterHorizontally){

                                    // set up Image for flag
                                    Image(
                                        painter = painterResource(id =
                                        country.countryImage),
                                        contentDescription = country
                                            .countryName,
                                        modifier = Modifier
                                            .size(80.dp)
                                            .clip(RoundedCornerShape(100))
                                            .border(2.dp, Color.Red,
                                                RoundedCornerShape(100)),
                                        contentScale = ContentScale.Crop,
                                        alignment = Alignment.Center
                                    )// end Image content

                                    Column (modifier = Modifier
                                        .padding(top = 10.dp),
                                        horizontalAlignment = Alignment.CenterHorizontally){

                                        Text(text =
                                        country.countryName,
                                            fontSize = 20.sp,
                                            color = Color.White,
                                            textAlign = TextAlign.Center)

                                        Spacer(modifier = Modifier.height(3.dp))

                                        Text(text =
                                        country.countryDetail,
                                            fontSize = 16.sp,
                                            color = Color.White,
                                            textAlign = TextAlign.Center)



                                    }//end Bottom Column Scope
                                    // Columns are Stacked one after the
                                    // other
                                }// end Main Column

                                // Add button to end of second Column
                                // Image is first element, then texts, then
                                // Button
                                // is Next Element in colomn

                                Button(
                                    onClick = {
                                        // open SecondPage and pass countryId
                                        // to page
                                        navController.navigate("SecondPage/${country.countryId}")

                                    },
                                    colors = ButtonDefaults.buttonColors
                                        (containerColor = Color.White),
                                    border = BorderStroke(2.dp, Color.Red)) {

                                    Icon(
                                        Icons.Rounded.ArrowForward,
                                        contentDescription = "Details",
                                        tint = Color.Red
                                    )// end Icon scope
                                }//end Button scope
                            }//end Top Column Scope
                        }// end Column SCope


                    }//end itemContent scope

                )//end items content scope

            }// end LazyColumn Scope


        }// end content block

    )//end scaffold scope
}//end FirstPage Fun Scope
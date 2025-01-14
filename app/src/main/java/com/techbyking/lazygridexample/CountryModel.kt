package com.techbyking.lazygridexample

//David A. King  Nov 30 2024
// LazyGrid Example - Grid of Scrolling List of Cards
// Udemy Jetpack Compose Class - Lesson #37-43
// Oak Academy

//Data Model Class for the List
// Define what is in a data type contained in the list item


data class CountryModel (

    val countryId : Int,
    val countryName : String,
    val countryDetail : String,
    val countryImage : Int

)

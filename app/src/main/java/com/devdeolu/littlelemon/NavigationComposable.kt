package com.devdeolu.littlelemon

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation(){
    val context = LocalContext.current
    val navController = rememberNavController()
    val startDestination = if (isUserDataStored(context)) {
        Home.route
    } else {
        Onboarding.route
    }
    NavHost(navController = navController, startDestination = startDestination ){
        composable(Onboarding.route){
            Onboarding(navController)
        }
        composable(Home.route){
            Home(navController)
        }
        composable(Profile.route) {
            Profile()
        }
    }
}


fun isUserDataStored(context: Context): Boolean {
    // Step 1: Get the SharedPreferences instance with the name "MyPrefs".
    // MODE_PRIVATE means that the file can only be accessed by the calling application.
    val sharedPreferences = context.getSharedPreferences("Little_Lemon", Context.MODE_PRIVATE)

    // Step 2: Retrieve the value associated with the key "firstName" from SharedPreferences.
    // If the key doesn't exist, return null.
    val firstName = sharedPreferences.getString("firstName", null)

    // Step 3: Retrieve the value associated with the key "lastName" from SharedPreferences.
    // If the key doesn't exist, return null.
    val lastName = sharedPreferences.getString("lastName", null)

    // Step 4: Retrieve the value associated with the key "email" from SharedPreferences.
    // If the key doesn't exist, return null.
    val email = sharedPreferences.getString("email", null)

    // Step 5: Check if all three values (firstName, lastName, email) are not null or empty.
    // If all three values are not null or empty, return true; otherwise, return false.
    return !firstName.isNullOrEmpty() && !lastName.isNullOrEmpty() && !email.isNullOrEmpty()
}
package com.devdeolu.littlelemon

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation(
    menuItems: List<MenuItemRoom>,
    onSearch: (String) -> Unit,
    selectedCategory: String,
    onCategorySelected: (String) -> Unit,
    searchPhrase: String
){
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
            Home(
                navController,
                menuItems,
                onSearch,
                selectedCategory,
                onCategorySelected,
                searchPhrase
            )
        }
        composable(Profile.route) {
            Profile(navController)
        }
    }
}


fun isUserDataStored(context: Context): Boolean {
    val sharedPreferences = context.getSharedPreferences("Little_Lemon", Context.MODE_PRIVATE)
    val firstName = sharedPreferences.getString("firstName", null)
    val lastName = sharedPreferences.getString("lastName", null)
    val email = sharedPreferences.getString("email", null)
    return !firstName.isNullOrEmpty() && !lastName.isNullOrEmpty() && !email.isNullOrEmpty()
}
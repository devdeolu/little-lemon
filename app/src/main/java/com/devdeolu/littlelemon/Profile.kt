package com.devdeolu.littlelemon

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun Profile(navController: NavHostController){
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("Little_Lemon", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    // Retrieve the saved data from SharedPreferences
    val firstName = sharedPreferences.getString("firstName", "") ?: ""
    val lastName = sharedPreferences.getString("lastName", "") ?: ""
    val email = sharedPreferences.getString("email", "") ?: ""

    Column(
        modifier = Modifier
            .background(color = Color.White)
            .fillMaxSize()
    ) {
        Header()
        Text(text = "Personal Information",
            textAlign = TextAlign.Start,
            color = Color(0xFF495E57),
            fontSize = 24.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = FontFamily.Serif,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 80.dp, bottom = 40.dp)
        )

        ProfileInfoItem(label = "First Name", value = firstName)
        ProfileInfoItem(label = "Last Name", value = lastName)
        ProfileInfoItem(label = "Email", value = email)

        MasterButton(
            text = "Log Out",
            onClick = {
                editor.clear()
                editor.apply()
                navController.navigate(Onboarding.route)
                Toast.makeText(context, "Logged Out", Toast.LENGTH_SHORT).show()
            }
        )
    }
}

@Composable
fun ProfileInfoItem(label: String, value: String) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp)
    ) {
        Text(
            text = "$label:",
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = FontFamily.Serif,
            modifier = Modifier.padding(start = 18.dp),
            color = Color(0xFF495E57)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp)
                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                .padding(5.dp)
        ){
            Text(
                text = value,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = FontFamily.Serif,
                color = Color(0xFF495E57)
            )
        }

    }
}

@Preview
@Composable
fun PreviewProfileScreen() {
    val fakeNavController = rememberNavController()
    Profile(navController =  fakeNavController)
}
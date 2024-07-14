package com.devdeolu.littlelemon

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Profile(){
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("Little_Lemon", Context.MODE_PRIVATE)

    // Retrieve the saved data from SharedPreferences
    val firstName = sharedPreferences.getString("firstName", "") ?: ""
    val lastName = sharedPreferences.getString("lastName", "") ?: ""
    val email = sharedPreferences.getString("email", "") ?: ""

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Profile Information",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Serif,
            color = Color(0xFF495E57),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        ProfileInfoItem(label = "First Name", value = firstName)
        ProfileInfoItem(label = "Last Name", value = lastName)
        ProfileInfoItem(label = "Email", value = email)
    }
}

@Composable
fun ProfileInfoItem(label: String, value: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = "$label:",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = FontFamily.Serif,
            color = Color(0xFF495E57)
        )
        Text(
            text = value,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = FontFamily.Serif,
            color = Color(0xFF495E57)
        )
    }
}

@Preview
@Composable
fun PreviewProfileScreen() {
    Profile()
}
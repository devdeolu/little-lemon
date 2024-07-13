package com.devdeolu.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Onboarding() {
    Column (modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())
    ){
        Header()
        UserInfo()
    }
}

@Composable
fun Header(){
    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(color = Color(0xFFFFFFFF)),
        ) {
        Image(painter = painterResource(id = R.mipmap.logo),
            contentDescription = "Little Lemon Logo",
            modifier = Modifier.size(150.dp)
            )
    }
}

@Composable
fun UserInfo(){
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    Column {
        Text(text = "Let's get to know you",
            textAlign = TextAlign.Center,
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = FontFamily.Serif,
            modifier = Modifier
                .background(color = Color(0xFF495E57))
                .fillMaxWidth()
                .padding(40.dp)
        )
        Text(text = "Personal Information",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = FontFamily.Serif,
            modifier = Modifier
                .padding(start = 12.5.dp, top = 40.dp, bottom = 20.dp)
        )
        TextFields(
            value = firstName,
            onValueChange = {firstName = it},
            label = "First Name",
            keyboardType = KeyboardType.Text
        )
        TextFields(
            value = lastName,
            onValueChange = {lastName = it},
            label = "Last Name",
            keyboardType = KeyboardType.Text
        )
        TextFields(
            value = email ,
            onValueChange = {email = it},
            label = "Email",
            keyboardType = KeyboardType.Email
        )
        MasterButton(text = "Register", onClick = { /*TODO*/ })
    }
}

@Composable
fun TextFields(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    keyboardType: KeyboardType = KeyboardType.Text
){
    OutlinedTextField(value = value,
        onValueChange = onValueChange,
        label = { Text(text = label,
            fontSize = 16.sp,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Normal,
            color = Color(0xFF495E57)) },
        textStyle = TextStyle(
            color = Color(0xFF495E57),
            fontSize = 16.sp,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Normal),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, top = 20.dp, bottom = 20.dp, end = 12.5.dp)
            .height(60.dp),
        shape = RoundedCornerShape(16.dp),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
    )
}

@Composable
fun MasterButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color(0xFFF4CE14),
    contentColor: Color = Color.Black,
    fontSize: Int = 16,
    fontFamily: FontFamily = FontFamily.Serif,
    fontWeight: FontWeight = FontWeight.SemiBold,
    cornerSize: CornerSize = CornerSize(8.dp)
) {
    Button(onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = contentColor
        ),
        shape = RoundedCornerShape(corner = cornerSize),
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 15.dp, top = 60.dp, bottom = 20.dp, end = 12.5.dp))
    {
        Text(
            text = text,
            fontSize = fontSize.sp,
            fontFamily = fontFamily,
            fontWeight = fontWeight
        )
    }
}

@Preview
@Composable
fun OnboardingPreview() {
    Onboarding()
}
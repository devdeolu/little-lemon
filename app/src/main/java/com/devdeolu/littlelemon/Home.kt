package com.devdeolu.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.devdeolu.littlelemon.ui.theme.karla
import com.devdeolu.littlelemon.ui.theme.markazi

@Composable
fun Home(navController: NavHostController,
         menuItems: List<MenuItemRoom>,
         onSearch: (String) -> Unit,
         selectedCategory: String,
         onCategorySelected: (String) -> Unit,
         searchPhrase: String
) {
    val categories = listOf("All") + menuItems.map { it.category }.distinct()

    Column (modifier = Modifier
        .fillMaxSize()
        ){

        HomeHeader(navController = navController)
        Hero(onSearch = onSearch, searchPhrase = searchPhrase)
        CategoryButtons(categories = categories.filter { it != "All" },
            selectedCategory = selectedCategory,
            onCategorySelected = onCategorySelected)
        MenuItems(items = menuItems)
    }
}

@Composable
fun HomeHeader(navController: NavHostController){
    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .height(96.dp)
            .background(color = Color(0xFFFFFFFF)),
    ) {
        Row (verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center){
            Image(painter = painterResource(id = R.mipmap.logo),
                alignment = Alignment.Center,
                contentDescription = "Little Lemon Logo",
                modifier = Modifier
                    .size(150.dp)
                    .offset(x = 10.dp, y = 10.dp)
            )
            Image(painter = painterResource(id = R.mipmap.sarah),
                contentDescription = "profile image",
                modifier = Modifier
                    .offset(x = 80.dp, y = 10.dp)
                    .height(40.dp)
                    .clip(shape = RoundedCornerShape(50.dp))
                    .clickable(onClick = { navController.navigate("Profile") })

            )
        }

    }
}

@Composable
fun Hero(onSearch: (String) -> Unit, searchPhrase: String){
    Box(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(0.4f)
        .background(color = Color(0xFF495E57))
        .padding(horizontal = 16.dp)) {
        Column {
            Text(
                text = "Little lemon",
                fontSize = 64.sp,
                lineHeight = 64.sp,
                fontFamily = markazi,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFF4CE14),
            )

            Row {
                Column {
                    Text(
                        text = "Chicago",
                        fontSize = 40.sp,
                        fontFamily = markazi,
                        fontWeight = FontWeight.Normal,
                        color = Color(0xFFFFFFFF)
                    )
                    Text(
                        text = "We are a family owned \n" +
                                "Mediterranean restaurant, \nfocused on traditional " +
                                "\nrecipes served with a \nmodern twist.",
                        fontSize = 16.sp,
                        fontFamily = karla,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFFFFFFFF),
                        lineHeight = 16.sp,
                    )
                }
                Image(painter = painterResource(id = R.mipmap.hero_image),
                    contentDescription = "little lemon hero image",
                    modifier = Modifier
                        .height(171.dp)
                        .width(140.dp)
                        .offset(x = 22.dp)
                        .clip(RoundedCornerShape(16.dp)),
                    contentScale = ContentScale.FillBounds
                )
            }

            TextField(
                value = searchPhrase,
                onValueChange = {
                    onSearch(it)
                },
                placeholder = {
                    Text(
                        text = "Enter search phrase",
                        fontSize = 16.sp,
                        fontFamily = karla,
                        fontWeight = FontWeight.Normal,
                        color = Color(0xFF495E57)
                    )
                },
                textStyle = TextStyle(
                    color = Color(0xFF495E57),
                    fontSize = 18.sp,
                    fontFamily = karla,
                    fontWeight = FontWeight.Normal),
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_search),
                        contentDescription = "Search Icon"
                    )
                },
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .height(55.dp)
                    .border(
                        shape = RoundedCornerShape(8.dp),
                        width = 0.dp,
                        color = Color(0xFF495E57)
                    )
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.White),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                colors = TextFieldDefaults.colors(Color.White)
            )
        }

    }
}



@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MenuItems(items: List<MenuItemRoom>){
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
    ) {
        items(
            items = items,
            itemContent = { menuItem ->
                Box(modifier = Modifier
                    .height(124.dp)
                    .width(377.dp)) {
                    Column{
                        Text(text = menuItem.title,
                            fontFamily = karla,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column{
                                Text(
                                    text = menuItem.description,
                                    fontFamily = karla,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Normal,
                                    modifier = Modifier
                                        .width(300.dp),
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis
                                )
                                Text(
                                    text = "$" + "%.2f".format(menuItem.price),
                                    fontFamily = karla,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                            GlideImage(
                                model = menuItem.image,
                                contentDescription = menuItem.title,
                                modifier = Modifier
                                    .height(79.73.dp)
                                    .width(81.47.dp)
                                    .clip(RoundedCornerShape(8.dp)),
                                contentScale = ContentScale.FillBounds
                            )
                        }
                    }
                }
                HorizontalDivider(thickness = Dp.Hairline)
            }

        )
    }
}

@Composable
fun CategoryButtons(
    categories: List<String>,
    selectedCategory: String,
    onCategorySelected: (String) -> Unit
){
    Column (modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp, vertical = 16.dp)) {
        Text(text = "ORDER FOR DELIVERY!",
            fontFamily = karla,
            fontSize = 20.sp,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.padding(bottom = 4.dp))
        LazyRow(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            items(
                items= categories,
                itemContent = { category ->
                    val isSelected = category == selectedCategory
                    Button( onClick = { onCategorySelected(category) },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (isSelected)
                                Color(0xFF495E57) else Color(0xFFEDEFEE),
                            contentColor = if (isSelected)
                                Color(0xFFFFFFFF)else Color(0xFF495E57))
                    ) {
                        Text(text = category,
                            fontFamily = karla,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.ExtraBold,
                        )
                    }
                }
            )
        }
        HorizontalDivider(thickness = 1.dp)
    }

}

@Preview
@Composable
fun HomePreview(){
    val fakeNavController = rememberNavController()
    Home(
        navController = fakeNavController,
        menuItems = emptyList(),
        onSearch = {},
        onCategorySelected = {},
        selectedCategory = "",
        searchPhrase = ""
    )
}
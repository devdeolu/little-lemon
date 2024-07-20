package com.devdeolu.littlelemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.devdeolu.littlelemon.ui.theme.LittleLemonTheme
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val httpClient = HttpClient(Android) {
        install(ContentNegotiation) {
            json(contentType = ContentType("text", "plain"))
        }
    }
    private val database by lazy {
        Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database").build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            LittleLemonTheme {
                //database menu items
                val databaseMenuItems = database
                    .menuItemDao()
                    .getAll()
                    .observeAsState(emptyList())
                    .value

                var searchPhrase by remember {
                    mutableStateOf("")
                }
                var selectedCategory by remember {
                    mutableStateOf("All")
                }

                val filterHelper = FilterHelper()

                val categoryFilteredItems = filterHelper.filterMenu(
                    type = when (selectedCategory) {
                        "starters" -> FilterType.Starters
                        "mains" -> FilterType.Mains
                        "desserts" -> FilterType.Desserts
                        "drinks" -> FilterType.Drinks
                        else -> FilterType.All
                    },
                    menuList = databaseMenuItems
                )
                val filteredMenuItems = categoryFilteredItems.filter {
                    it.title.contains(searchPhrase, ignoreCase = true)
                }
                    AppNavigation(
                        menuItems = filteredMenuItems,
                        onSearch = { searchPhrase = it },
                        onCategorySelected = { category ->
                            selectedCategory = if (selectedCategory == category) "All" else category
                        },
                        selectedCategory = selectedCategory,
                        searchPhrase = searchPhrase,
                    )
            }
        }
        lifecycleScope.launch(Dispatchers.IO) {
            if (database.menuItemDao().isEmpty()) {
                val menuItems = fetchMenuItems()
                saveMenuToDatabase(menuItems)
            }
        }
    }

    private suspend fun fetchMenuItems(): List<MenuItemNetwork> {
        val response: MenuNetwork = httpClient
            .get("https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json")
            .body()
        return response.menu
        }

    private fun saveMenuToDatabase(menuItemsNetwork: List<MenuItemNetwork>) {
        val menuItemsRoom = menuItemsNetwork.map { it.toMenuItemRoom() }
        database.menuItemDao().insertAll(*menuItemsRoom.toTypedArray())
    }
}








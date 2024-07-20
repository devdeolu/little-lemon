package com.devdeolu.littlelemon

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MenuNetwork (
    @SerialName("menu")
    val menu: List<MenuItemNetwork>
)

@Serializable
data class MenuItemNetwork (
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("description")
    val description: String,
    @SerialName("price")
    val price: Double,
    @SerialName("image")
    val image: String,
    @SerialName("category")
    val category: String
){
    fun toMenuItemRoom() = MenuItemRoom(
        // add code here
        id = this.id,
        title = this.title,
        description = this.description,
        image = this.image,
        category = this.category,
        price = this.price.toDouble(),
    )
}
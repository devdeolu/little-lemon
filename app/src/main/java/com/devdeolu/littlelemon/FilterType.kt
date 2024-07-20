package com.devdeolu.littlelemon

sealed class FilterType {
    object All : FilterType()
    object Starters : FilterType()
    object Mains : FilterType()
    object Desserts : FilterType()
    object Drinks : FilterType()
}
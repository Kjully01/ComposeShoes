package com.br.karen.composeshoes.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbProduct")
data class Product(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String = "",
    val description: String = "",
    val price: Double = 0.0,
    val category: String? = "Todos",
    val image: Int? = null
)
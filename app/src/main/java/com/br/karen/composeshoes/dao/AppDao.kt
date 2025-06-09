package com.br.karen.composeshoes.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.br.karen.composeshoes.model.Product

@Dao
interface AppDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveProduct(product: Product)

    @Query("SELECT * FROM tbProduct")
    suspend fun getAllProducts(): List<Product>

    @Query("SELECT * FROM tbProduct WHERE name LIKE '%' || :filter || '%'")
    suspend fun getProducts(filter: String) : List<Product>

}
package com.br.karen.composeshoes.repository

import com.br.karen.composeshoes.dao.AppDao
import com.br.karen.composeshoes.model.Product

class AppRepository(private val dao: AppDao) {

    suspend fun getAllProducts(): List<Product> {
        return dao.getAllProducts()
    }

    suspend fun getProducts(filter: String, category: String): List<Product> {
        return dao.getProducts(filter, category)
    }

}
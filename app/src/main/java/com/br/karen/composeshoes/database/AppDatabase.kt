package com.br.karen.composeshoes.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.br.karen.composeshoes.dao.AppDao
import com.br.karen.composeshoes.model.Product

@Database(entities = [Product::class], version = 3, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {

    abstract fun appDao(): AppDao

}
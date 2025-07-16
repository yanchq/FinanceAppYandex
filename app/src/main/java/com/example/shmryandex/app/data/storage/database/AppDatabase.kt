package com.example.shmryandex.app.data.storage.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.core.data.storage.dao.AccountDao
import com.example.core.data.storage.dao.CategoriesDao
import com.example.core.data.storage.dao.TransactionsDao
import com.example.core.data.storage.entity.AccountDbModel
import com.example.core.data.storage.entity.CategoryDbModel
import com.example.core.data.storage.entity.TransactionDbModel

@Database(
    version = 5,
    entities = [
        AccountDbModel::class,
        CategoryDbModel::class,
        TransactionDbModel::class
    ],
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {

    abstract fun accountDao(): AccountDao

    abstract fun categoriesDao(): CategoriesDao

    abstract fun transactionsDao(): TransactionsDao
}
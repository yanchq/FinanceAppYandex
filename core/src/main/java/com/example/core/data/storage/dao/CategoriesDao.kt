package com.example.core.data.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.core.data.storage.entity.CategoryDbModel

@Dao
interface CategoriesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(categoryDbModel: CategoryDbModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategories(categoryDbModel: List<CategoryDbModel>)

    @Query("SELECT * FROM categories")
    suspend fun getAllCategories(): List<CategoryDbModel>

    @Query("DELETE FROM categories")
    suspend fun clearAll()
}
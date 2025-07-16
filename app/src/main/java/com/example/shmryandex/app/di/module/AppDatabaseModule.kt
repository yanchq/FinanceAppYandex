package com.example.shmryandex.app.di.module

import android.content.Context
import androidx.room.Room
import com.example.core.data.storage.dao.AccountDao
import com.example.core.data.storage.dao.CategoriesDao
import com.example.core.data.storage.dao.TransactionsDao
import com.example.shmryandex.app.data.storage.database.AppDatabase
import com.example.shmryandex.app.di.AppScope
import dagger.Module
import dagger.Provides

@Module
class AppDatabaseModule {

    @Provides
    @AppScope
    fun provideAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "finance_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideAccountDao(database: AppDatabase): AccountDao = database.accountDao()

    @Provides
    fun provideCategoryDao(database: AppDatabase): CategoriesDao = database.categoriesDao()

    @Provides
    fun provideTransactionDao(database: AppDatabase): TransactionsDao = database.transactionsDao()
}
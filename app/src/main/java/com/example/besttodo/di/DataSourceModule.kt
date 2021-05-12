package com.example.besttodo.di

import android.content.Context
import androidx.room.Room
import com.example.besttodo.data.disk.AppDatabase
import com.example.besttodo.data.disk.TodoDao
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton

@Module
class DataSourceModule {

    @Provides
    @Singleton
    fun provideAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, "todos.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideTodoDao(appDatabase: AppDatabase) = appDatabase.todoDao()

}
package com.example.dailytask.di

import android.app.Application
import androidx.room.Room
import com.example.dailytask.data.DailyTaskDao
import com.example.dailytask.data.DailyTaskDatabase
import com.example.dailytask.data.DailyTaskRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DailyTaskModule {

    @Provides
    @Singleton
    fun provideDailyTaskDatabase(application: Application): DailyTaskDatabase {
        return Room.databaseBuilder(
            application,
            DailyTaskDatabase::class.java,
            "daily_task_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideDailyTaskDao(database: DailyTaskDatabase) : DailyTaskDao = database.dailyTaskDao()

    @Provides
    @Singleton
    fun provideTodoRepository(dailyTaskDao : DailyTaskDao): DailyTaskRepository = DailyTaskRepository(dailyTaskDao)

}
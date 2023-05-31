package com.example.dailytask.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [DailyTaskEntity::class],
    version = 1
)
abstract class DailyTaskDatabase : RoomDatabase() {
    abstract fun dailyTaskDao(): DailyTaskDao
}
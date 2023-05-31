package com.example.dailytask.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface DailyTaskDao {

    @Insert
    suspend fun insertDailyTask(dailyTaskEntity: DailyTaskEntity)

    @Update
    suspend fun updateTask(dailyTaskEntity: DailyTaskEntity)

    @Query("SELECT * FROM daily_task_table")
    fun getDailyTaskList(): Flow<List<DailyTaskEntity>>
}
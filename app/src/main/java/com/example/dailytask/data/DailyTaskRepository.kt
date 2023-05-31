package com.example.dailytask.data

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DailyTaskRepository @Inject constructor(
   private val dailyTaskDao: DailyTaskDao
) {
    suspend fun insertDailyTask(dailyTaskEntity: DailyTaskEntity) {
        dailyTaskDao.insertDailyTask(dailyTaskEntity)
    }

    suspend fun updateDailyTask(dailyTaskEntity: DailyTaskEntity){
        dailyTaskDao.updateTask(dailyTaskEntity = dailyTaskEntity)
    }

    fun getTodoList(): Flow<List<DailyTaskEntity>> {
        return dailyTaskDao.getDailyTaskList()
    }
}
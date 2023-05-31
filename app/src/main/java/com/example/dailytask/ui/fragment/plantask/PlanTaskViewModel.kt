package com.example.dailytask.ui.fragment.plantask

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dailytask.data.DailyTaskEntity
import com.example.dailytask.data.DailyTaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PlanTaskViewModel @Inject constructor(
    private val dailyTaskRepository: DailyTaskRepository
) : ViewModel() {

    fun insertDailyTask(task: DailyTaskEntity){
        viewModelScope.launch {
            dailyTaskRepository.insertDailyTask(dailyTaskEntity = task)
        }
    }
}
package com.example.dailytask.ui.fragment.tasklist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dailytask.data.DailyTaskEntity
import com.example.dailytask.data.DailyTaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DailyTaskListViewModel @Inject constructor(
    private val dailyTaskRepository: DailyTaskRepository
): ViewModel() {

    private val _dailyTaskList = MutableStateFlow<List<DailyTaskEntity>>(emptyList())
    val dailyTaskList = _dailyTaskList.asStateFlow()

    init {
       getDailyTaskList()
    }

    private fun getDailyTaskList(){
        viewModelScope.launch {
            dailyTaskRepository.getTodoList().collect { dailyTaskList ->
                _dailyTaskList.value = dailyTaskList
            }
        }
    }

    fun updateTask(dailyTaskEntity: DailyTaskEntity){
        viewModelScope.launch {
            dailyTaskRepository.updateDailyTask(dailyTaskEntity = dailyTaskEntity)
        }
    }

}
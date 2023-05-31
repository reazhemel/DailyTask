package com.example.dailytask.ui.fragment.tasklist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dailytask.data.DailyTaskEntity
import com.example.dailytask.databinding.FragmentTaskListBinding
import com.example.dailytask.ui.fragment.tasklist.adapter.DailyTaskAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DailyTaskListFragment: Fragment() {

    private var _binding: FragmentTaskListBinding? = null
    private val binding get() = _binding!!

    private val tasksViewModel by viewModels<DailyTaskListViewModel>()

    private lateinit var tasksAdapter : DailyTaskAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       _binding = FragmentTaskListBinding.inflate(layoutInflater)
        tasksAdapter = DailyTaskAdapter(
            onCheckBoxClicked = { task, isDone ->
                tasksViewModel.updateTask(
                    dailyTaskEntity = DailyTaskEntity(
                        id = task.id,
                        taskName = task.taskName,
                        isTaskDone = isDone
                    )
                )
            }
        )

        binding.addTaskFabBtn.setOnClickListener {
            findNavController().navigate(DailyTaskListFragmentDirections.actionDailyTaskListFragmentToPlanTaskFragment())
        }

        setupDailyTasksAdapter()
        observeDailyTaskList()

        return binding.root
    }

    private fun setupDailyTasksAdapter(){
        binding.tasListRecyclerView.layoutManager = LinearLayoutManager(requireActivity())
        binding.tasListRecyclerView.setHasFixedSize(true)
        binding.tasListRecyclerView.adapter = tasksAdapter
    }

    private fun observeDailyTaskList(){
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED){
                tasksViewModel.dailyTaskList.collect { tasks ->
                    tasksAdapter.submitDailyTaskList(tasks)
                }
            }
        }
    }
}
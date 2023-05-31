package com.example.dailytask.ui.fragment.tasklist.adapter

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Paint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.compose.ui.graphics.Color
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.dailytask.R
import com.example.dailytask.data.DailyTaskEntity


class DailyTaskAdapter(
   val onCheckBoxClicked: (DailyTaskEntity ,Boolean) -> Unit
) : RecyclerView.Adapter<DailyTaskAdapter.DailyTaskViewHolder>() {

    private var dailyTaskList: List<DailyTaskEntity> = emptyList()

    inner class DailyTaskViewHolder(todoView: View) : RecyclerView.ViewHolder(todoView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyTaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false)
        return DailyTaskViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dailyTaskList.size
    }

    @SuppressLint("CutPasteId")
    override fun onBindViewHolder(holder: DailyTaskViewHolder, position: Int) {
        val task = dailyTaskList[position]

        if(task.isTaskDone){
            holder.itemView.findViewById<TextView>(R.id.task_name_text_view).text = task.taskName
            holder.itemView.findViewById<TextView>(R.id.task_name_text_view).paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            holder.itemView.findViewById<TextView>(R.id.task_name_text_view).setTextColor(ContextCompat.getColor(
                holder.itemView.context,
                R.color.done_task_color
            ))
            holder.itemView.findViewById<CheckBox>(R.id.task_done_check_box).buttonDrawable = ContextCompat.getDrawable(
                holder.itemView.context,
                R.drawable.ic_checked
            )
        }else {
            holder.itemView.findViewById<TextView>(R.id.task_name_text_view).text = task.taskName
            holder.itemView.findViewById<TextView>(R.id.task_name_text_view).paintFlags = Paint.LINEAR_TEXT_FLAG
            holder.itemView.findViewById<TextView>(R.id.task_name_text_view).setTextColor(ContextCompat.getColor(
                holder.itemView.context,
                R.color.todo_task_color
            ))
            holder.itemView.findViewById<CheckBox>(R.id.task_done_check_box).buttonDrawable = ContextCompat.getDrawable(
                holder.itemView.context,
                R.drawable.ic_unchecked
            )
        }

        holder.itemView.findViewById<CheckBox>(R.id.task_done_check_box).setOnClickListener {
            onCheckBoxClicked(task,!task.isTaskDone)
        }
    }


    @SuppressLint("NotifyDataSetChanged")
    fun submitDailyTaskList(dailyTaskList: List<DailyTaskEntity>){
        this.dailyTaskList = dailyTaskList
        notifyDataSetChanged()
    }


}
package com.example.Lab8

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.Lab8.databinding.RecycleViewHorizontalBinding


class HorizontalViewAdapter (private val items: List<String>):
    RecyclerView.Adapter<HorizontalViewAdapter.HorizontalViewHandler>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalViewHandler {
        val binding = RecycleViewHorizontalBinding.inflate(LayoutInflater.from(parent.context))
        return HorizontalViewHandler(binding)
    }

    override fun onBindViewHolder(holder: HorizontalViewHandler, position: Int) {
        val textName = items[position]
        holder.bind(textName)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class HorizontalViewHandler(private val binding: RecycleViewHorizontalBinding):
        RecyclerView.ViewHolder(binding.root) {

        fun bind(textName: String) {
            binding.textView.text = textName
        }
    }
}
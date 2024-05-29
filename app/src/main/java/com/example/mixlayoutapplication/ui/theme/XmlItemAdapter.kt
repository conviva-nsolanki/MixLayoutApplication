package com.example.mixlayoutapplication.ui.theme

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.mixlayoutapplication.databinding.ItemXmlBinding

class XmlItemAdapter: ListAdapter<String, XmlItemAdapter.XmlItemViewHolder>(TaskDiffCallBack()) {


    inner class  XmlItemViewHolder(binding: ItemXmlBinding): ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): XmlItemViewHolder {
        val binding = ItemXmlBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return XmlItemViewHolder(binding)

    }

    override fun onBindViewHolder(holder: XmlItemViewHolder, position: Int) {

    }

    class TaskDiffCallBack : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
//            Log.d(TAG,Thread.currentThread().name)
            return oldItem == newItem
        }
    }
}
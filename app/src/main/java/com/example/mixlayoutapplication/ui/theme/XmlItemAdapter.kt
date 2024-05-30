package com.example.mixlayoutapplication.ui.theme

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.mixlayoutapplication.data.NationalPark
import com.example.mixlayoutapplication.databinding.ItemXmlBinding

class XmlItemAdapter: ListAdapter<NationalPark, XmlItemAdapter.XmlItemViewHolder>(TaskDiffCallBack()) {


    inner class  XmlItemViewHolder(private val binding: ItemXmlBinding): ViewHolder(binding.root) {
        fun bind(parkInfo: NationalPark) {
            binding.tvTitle.text = parkInfo.name
            binding.tvDescription.text = parkInfo.description
            binding.ivPark.setImageURI(Uri.parse(parkInfo.imageUrl))

            binding.btnPark.setOnClickListener {
                Toast.makeText(binding.root.context, "${parkInfo.name} button clicked", Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): XmlItemViewHolder {
        val binding = ItemXmlBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return XmlItemViewHolder(binding)

    }

    override fun onBindViewHolder(holder: XmlItemViewHolder, position: Int) {
        getItem(position) ?: { item: NationalPark ->
            holder.bind(item)
        }
    }

    class TaskDiffCallBack : DiffUtil.ItemCallback<NationalPark>() {
        override fun areItemsTheSame(oldItem: NationalPark, newItem: NationalPark): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: NationalPark, newItem: NationalPark): Boolean {
            return oldItem == newItem
        }
    }
}
package com.example.mixlayoutapplication.ui.theme

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.example.mixlayoutapplication.data.NationalPark
import com.example.mixlayoutapplication.databinding.ItemXmlBinding

class XmlItemAdapter: ListAdapter<NationalPark, XmlItemAdapter.XmlItemViewHolder>(TaskDiffCallBack()) {

    private val data = mutableListOf<NationalPark>()
    inner class  XmlItemViewHolder(private val binding: ItemXmlBinding): ViewHolder(binding.root) {
        fun bind(parkInfo: NationalPark) {
            binding.tvTitle.text = parkInfo.name
            binding.tvDescription.text = parkInfo.description
            binding.ivPark.load(parkInfo.imageUrl)
            binding.btnParkVisit.text = "Visit ${parkInfo.name}"

            binding.btnParkVisit.setOnClickListener {
                Toast.makeText(binding.root.context, "Visit ${parkInfo.name}", Toast.LENGTH_SHORT).show()
            }

            binding.ivParkNavigate.setOnClickListener {
                Toast.makeText(binding.root.context, "Navigate ${parkInfo.name}", Toast.LENGTH_SHORT).show()
            }
        }

    }

    fun setData(items: List<NationalPark>) {
        data.clear()
        data.addAll(items)
        submitList(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): XmlItemViewHolder {
        val binding = ItemXmlBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return XmlItemViewHolder(binding)

    }

    override fun onBindViewHolder(holder: XmlItemViewHolder, position: Int) {
        holder.bind( data[position])
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
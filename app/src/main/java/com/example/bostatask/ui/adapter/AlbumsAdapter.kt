package com.example.bostatask.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bostatask.databinding.AlbumCardLayoutBinding
import com.example.bostatask.model.model.AlbumData
import com.example.bostatask.common.OnClickListener

class AlbumsAdapter(private val clickListener: OnClickListener<AlbumData>) :
    ListAdapter<AlbumData, AlbumsAdapter.ViewHolder>(AlbumCallBack) {

    companion object AlbumCallBack : DiffUtil.ItemCallback<AlbumData>() {
        override fun areItemsTheSame(oldItem: AlbumData, newItem: AlbumData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: AlbumData, newItem: AlbumData): Boolean {
            return oldItem == newItem
        }
    }

    inner class ViewHolder(private val binding: AlbumCardLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(album: AlbumData) {
            binding.albumName.text = album.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            AlbumCardLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val album = getItem(position)
        holder.bind(album)
        holder.itemView.setOnClickListener {
            clickListener.clickListener(album)
        }
    }
}

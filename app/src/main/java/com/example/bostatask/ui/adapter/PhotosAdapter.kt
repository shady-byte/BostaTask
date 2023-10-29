package com.example.bostatask.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bostatask.databinding.PhotoViewLayoutBinding
import com.example.bostatask.model.model.PhotoData
import com.example.bostatask.common.OnClickListener

class PhotosAdapter(private val clickListener: OnClickListener<PhotoData>) :
    ListAdapter<PhotoData, PhotosAdapter.ViewHolder>(AlbumCallBack) {

    companion object AlbumCallBack : DiffUtil.ItemCallback<PhotoData>() {
        override fun areItemsTheSame(oldItem: PhotoData, newItem: PhotoData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PhotoData, newItem: PhotoData): Boolean {
            return oldItem == newItem
        }
    }

    inner class ViewHolder(private val binding: PhotoViewLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(photo: PhotoData, view: View) {
            Glide.with(view.context)
                .load(photo.url)
                .into(binding.albumPhotoView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            PhotoViewLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val photo = getItem(position)
        holder.bind(photo, holder.itemView)
        holder.itemView.setOnClickListener {
            clickListener.clickListener(photo)
        }
    }
}


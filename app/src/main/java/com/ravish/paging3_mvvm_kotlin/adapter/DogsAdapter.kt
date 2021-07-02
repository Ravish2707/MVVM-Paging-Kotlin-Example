package com.ravish.paging3_mvvm_kotlin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.ravish.paging3_mvvm_kotlin.databinding.EachRowBinding
import com.ravish.paging3_mvvm_kotlin.model.Dogs
import javax.inject.Inject

class DogsAdapter @Inject constructor() :PagingDataAdapter<Dogs, DogsAdapter.DogsViewHolder>(Diff) {

    override fun onBindViewHolder(holder: DogsViewHolder, position: Int) {
        val dogs = getItem(position)
        if (dogs != null){
            holder.bind(dogs)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogsViewHolder {
        return DogsViewHolder(EachRowBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }


    class DogsViewHolder(private val  binding: EachRowBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(dogs: Dogs){
            binding.image.load(dogs.url)
        }
    }

    object Diff : DiffUtil.ItemCallback<Dogs>(){
        override fun areItemsTheSame(oldItem: Dogs, newItem: Dogs): Boolean = oldItem.url == newItem.url

        override fun areContentsTheSame(oldItem: Dogs, newItem: Dogs): Boolean = oldItem == newItem

    }

}
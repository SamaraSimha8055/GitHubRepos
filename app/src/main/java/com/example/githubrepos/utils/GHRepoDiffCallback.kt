package com.example.githubrepos.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.githubrepos.data.model.GHRepo

class GHRepoDiffCallback : DiffUtil.ItemCallback<GHRepo>() {
    override fun areItemsTheSame(oldItem: GHRepo, newItem: GHRepo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: GHRepo, newItem: GHRepo): Boolean {
        return oldItem == newItem
    }
}
package com.example.githubrepos.ui.main

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.githubrepos.data.model.GHRepo
import com.example.githubrepos.databinding.ItemRepoBinding
import com.example.githubrepos.ui.webview.WebViewActivity
import com.example.githubrepos.utils.GHRepoDiffCallback

class GHRepoAdapter : ListAdapter<GHRepo, GHRepoAdapter.GHRepoViewHolder>(GHRepoDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GHRepoViewHolder {
        val binding = ItemRepoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GHRepoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GHRepoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class GHRepoViewHolder(private val binding: ItemRepoBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(repo: GHRepo) {
            binding.repoName.text = repo.name
            binding.repoId.text = "ID: ${repo.id}"
            binding.root.setOnClickListener {
                Log.i("MainViewModel", "bind: $repo")
                try {
                    val intent = Intent(binding.root.context, WebViewActivity::class.java)
                    intent.putExtra("URL", repo.repoURL)
                    binding.root.context.startActivity(intent)
                } catch (ex: Exception) {
                    Log.e("MainViewModel", "bind: ", ex)
                }
            }
        }
    }
}
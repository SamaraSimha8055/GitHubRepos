package com.example.githubrepos.ui.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.githubrepos.data.model.GHRepo
import com.example.githubrepos.data.repository.GHRepoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = GHRepoRepository(application.applicationContext)
    private val _repos = MutableLiveData<List<GHRepo>>()
    val repos: LiveData<List<GHRepo>> get() = _repos

    // Fetch repositories in background
    fun fetchRepositories() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val fetchedRepos = repository.getRepositories()
                withContext(Dispatchers.Main) {
                    _repos.value = fetchedRepos
                }
            } catch (e: Exception) {
                Log.e("MainViewModel", "Error fetching repos: ${e.message}", e)
            }
        }
    }

    // Search repositories based on query
    fun searchRepositories(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val fetchedRepos = if (query.isEmpty()) {
                    repository.getRepositories() // Fetch all if no query
                } else {
                    repository.searchRepositories(query) // Search with query
                }
                withContext(Dispatchers.Main) {
                    _repos.value = fetchedRepos
                }
            } catch (e: Exception) {
                Log.e("MainViewModel", "Error searching repos: ${e.message}", e)
            }
        }
    }
}
package com.example.githubrepos.data.repository

import android.content.Context
import com.example.githubrepos.data.local.AppDatabase
import com.example.githubrepos.data.model.GHRepo
import com.example.githubrepos.data.network.NetworkService

class GHRepoRepository(context: Context) {

    private val db = AppDatabase.getDatabase(context)
    private val dao = db.ghRepoDao()
    private val networkService = NetworkService()

    suspend fun getRepositories(): List<GHRepo> {
        val cachedRepos = dao.getAllRepos()
        return if (cachedRepos.isNotEmpty()) {
            cachedRepos
        } else {
            val reposFromApi = networkService.fetchRepositories()
            dao.insertAll(reposFromApi)
            reposFromApi
        }
    }

    suspend fun searchRepositories(query: String): List<GHRepo> {
        return dao.searchRepos("%$query%")
    }
}
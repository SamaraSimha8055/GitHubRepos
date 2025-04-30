package com.example.githubrepos.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.githubrepos.data.model.GHRepo

@Dao
interface GHRepoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(repos: List<GHRepo>)

    @Query("SELECT * FROM GHRepo WHERE id LIKE :query OR name LIKE :query")
    suspend fun searchRepos(query: String): List<GHRepo>

    @Query("SELECT * FROM GHRepo")
    suspend fun getAllRepos(): List<GHRepo>
}
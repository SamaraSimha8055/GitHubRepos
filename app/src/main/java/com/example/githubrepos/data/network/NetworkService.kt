package com.example.githubrepos.data.network

import android.util.Log
import com.example.githubrepos.data.model.GHRepo
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

class NetworkService {

    fun fetchRepositories(): List<GHRepo> {
        val url = URL("https://api.github.com/search/repositories?q=language:swift&sort=stars&order=desc")
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "GET"

        return try {
            Log.i("MainViewModel", "fetchRepositories: "+connection.responseCode)
            if (connection.responseCode == 200) {
                val stream = connection.inputStream.bufferedReader().use { it.readText() }
                val json = JSONObject(stream)
                val items = json.getJSONArray("items")

                List(items.length()) { index ->
                    val item = items.getJSONObject(index)
                    GHRepo(
                        id = item.getInt("id"),
                        name = item.getString("name"),
                        repoURL = item.getString("html_url")
                    )
                }
            } else {
                emptyList()
            }
        } finally {
            connection.disconnect()
        }
    }
}
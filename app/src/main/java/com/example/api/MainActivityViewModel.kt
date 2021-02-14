package com.example.api

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*

class MainActivityViewModel : ViewModel() {


    suspend fun getApiResults() {
        return withContext(Dispatchers.IO) {
            val resultado = GlobalScope.async {
                DownloadManager.send()
            }
            resultado.await()
        }
    }
}
package org.d3if3019.modulo.ui.informasi

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if3019.modulo.model.HasilInfo
import org.d3if3019.modulo.network.ApiStatus
import org.d3if3019.modulo.network.InfoApi
import org.d3if3019.modulo.network.UpdateWorker
import java.util.concurrent.TimeUnit

class InformasiViewModel : ViewModel() {

        private val data = MutableLiveData<List<HasilInfo>>()
        private val status = MutableLiveData<ApiStatus>()

        init {
            retrieveData()
        }

        private fun retrieveData() {
            viewModelScope.launch (Dispatchers.IO) {
                status.postValue(ApiStatus.LOADING)
                try {
                    data.postValue(InfoApi.service.getInfo())
                    status.postValue(ApiStatus.SUCCESS)
                } catch (e: Exception) {
                    Log.d("InformasiViewModel", "Failure: ${e.message}")
                    status.postValue(ApiStatus.FAILED)
                }
            }
        }

        fun getData(): LiveData<List<HasilInfo>> = data

        fun getStatus(): LiveData<ApiStatus> = status

    fun scheduleUpdater(app: Application) {
        val request = OneTimeWorkRequestBuilder<UpdateWorker>()
            .setInitialDelay(1, TimeUnit.MINUTES)
            .build()
        WorkManager.getInstance(app).enqueueUniqueWork(
            UpdateWorker.WORK_NAME,
            ExistingWorkPolicy.REPLACE,
            request
        )
    }
}


package org.d3if3019.modulo.ui.histori

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if3019.modulo.db.BmiDao

class HistoriViewModel(private val db: BmiDao) : ViewModel() {
    val data = db.getLastModulus()

    fun hapusData() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            db.hapusData()
        }
    }
}
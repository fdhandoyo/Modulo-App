package org.d3if3019.modulo.ui.hitung

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if3019.modulo.db.BmiDao
import org.d3if3019.modulo.db.BmiEntity
import org.d3if3019.modulo.model.HasilModulus
import org.d3if3019.modulo.model.hitungModulus

class HitungViewModel(private val db: BmiDao): ViewModel() {
    private val hasilModulus = MutableLiveData<HasilModulus?>()

    fun hitungModulus(angka1: Float, angka2: Float) {
        val dataModulus = BmiEntity(
            angka1 = angka1,
            angka2 = angka2
        )

        hasilModulus.value = dataModulus.hitungModulus()
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                db.insert(dataModulus)
            }
        }
    }
    fun getHasil(): LiveData<HasilModulus?> = hasilModulus
}
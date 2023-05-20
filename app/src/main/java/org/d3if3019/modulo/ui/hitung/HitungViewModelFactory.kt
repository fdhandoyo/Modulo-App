package org.d3if3019.modulo.ui.hitung

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.d3if3019.modulo.db.BmiDao

class HitungViewModelFactory (
        private val db: BmiDao
        ): ViewModelProvider.Factory {
            @Suppress("Unchecked Test")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(HitungViewModel::class.java)) {
                    return HitungViewModel(db) as T
                }
                throw IllegalArgumentException("Unknonw ViewModel class")
            }
        }
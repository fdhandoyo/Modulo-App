package org.d3if3019.modulo.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "modulus")
data class BmiEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var tanggal: Long = System.currentTimeMillis(),
    var angka1: Float,
    var angka2: Float
)
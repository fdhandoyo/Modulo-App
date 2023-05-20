package org.d3if3019.modulo.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BmiDao {
    @Insert
    fun insert(bmi: BmiEntity)

    @Query("SELECT * FROM modulus ORDER BY id DESC")
    fun getLastModulus(): LiveData<List<BmiEntity>>

    @Query("DELETE FROM modulus")
    fun hapusData()
}
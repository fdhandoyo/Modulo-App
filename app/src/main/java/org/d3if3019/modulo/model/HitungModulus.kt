package org.d3if3019.modulo.model

import org.d3if3019.modulo.db.BmiEntity

fun BmiEntity.hitungModulus(): HasilModulus {
    val hasil = angka1 % angka2
    return HasilModulus(hasil)
}
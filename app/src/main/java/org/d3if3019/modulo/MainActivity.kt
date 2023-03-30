package org.d3if3019.modulo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import org.d3if3019.modulo.databinding.ActivityMainBinding
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.hitung.setOnClickListener{hitungModulus()}
    }

    private fun hitungModulus() {

        val angka1 = binding.angkaPertamaInp.text.toString()
        if (TextUtils.isEmpty(angka1)) {
            Toast.makeText(this, "Masukkan Angka Pertama!", Toast.LENGTH_LONG).show()
            return
        }

        val angka2 = binding.angkaModulusInp.text.toString()
        if (TextUtils.isEmpty(angka2)) {
            Toast.makeText(this, "Masukkan Angka Modulus!", Toast.LENGTH_SHORT).show()
            return
        }


        val angkaPertama = binding.angkaPertamaInp.text.toString().toInt()
        val angkaModulus = binding.angkaModulusInp.text.toString().toInt()

        val hasilModulus = angkaPertama % angkaModulus

        binding.hasilModulus.text = "Hasil = $hasilModulus"

    }
}
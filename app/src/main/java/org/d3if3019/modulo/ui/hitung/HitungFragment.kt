package org.d3if3019.modulo.ui.hitung

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import org.d3if3019.modulo.R
import org.d3if3019.modulo.databinding.FragmentHitungBinding
import org.d3if3019.modulo.db.BmiDb
import org.d3if3019.modulo.model.HasilModulus

class HitungFragment: Fragment() {
    private lateinit var binding: FragmentHitungBinding
    private val viewModel: HitungViewModel by lazy {
        val db = BmiDb.getInstance(requireContext())
        val factory = HitungViewModelFactory(db.dao)
        ViewModelProvider(this, factory)[HitungViewModel::class.java]
    }

    override fun onStart() {
        super.onStart()
        Log.i("HitungFragment", "onStart dilakukan")
    }

    override fun onResume() {
        super.onResume()
        Log.i("HitungFragment", "onResume dilakukan")
    }

    override fun onPause() {
        super.onPause()
        Log.i("HitungFragment", "onPause dilakukan")
    }

    override fun onStop() {
        super.onStop()
        Log.i("HitungFragment", "onStop dilakukan")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.i("HitungFragment", "onDestroyView dilakukan")
    }

    override fun onDestroy() {
        Log.i("HitungFragment", "onDestroy dilakukan")
        super.onDestroy()
    }

    override fun onDetach() {
        Log.i("HitungFragment", "onDetach dilakukan")
        super.onDetach()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.i("HitungFragment", "onAttach dilakukan")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("HitungFragment", "onCreate dilakukan")
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_about -> {
                findNavController().navigate(
                    R.id.action_hitungFragment_to_aboutFragment
                )
                return true

            } R.id.menu_histori -> {
                findNavController().navigate(
                    R.id.action_hitungFragment_to_historiFragment
                )
                return true
            }
            R.id.menu_info -> {
                findNavController().navigate(
                    R.id.action_hitungFragment_to_informasiFragment
                )
                return true
            }

        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding = FragmentHitungBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.hitung.setOnClickListener { hitungModulus() }
        binding.buttonBagikan.setOnClickListener { sharedData() }

        viewModel.getHasil().observe(requireActivity(), { showResult(it) })
    }

    private fun hitungModulus() {
        val angka1 = binding.angkaPertamaInp.text.toString()
        if (TextUtils.isEmpty(angka1)) {
            Toast.makeText(context, R.string.angka1_invalid, Toast.LENGTH_LONG).show()
            return
        }

        val angka2 = binding.angkaModulusInp.text.toString()
        if (TextUtils.isEmpty(angka2)) {
            Toast.makeText(context, R.string.angka1_invalid, Toast.LENGTH_SHORT).show()
            return
        }

        viewModel.hitungModulus(
            angka1.toFloat(),
            angka2.toFloat()
        )
    }

    private fun showResult(result: HasilModulus?) {
        if (result == null) return
        binding.hasilModulus.text = getString(R.string.hasil, result.modulus)
        binding.buttonBagikan.visibility = View.VISIBLE
    }

    private fun sharedData() {
        val message = getString(R.string.bagikan_template,
            binding.angkaPertamaInp.text,
            binding.angkaModulusInp.text,
            binding.hasilModulus.text
        )

        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain").putExtra(Intent.EXTRA_TEXT, message)
        if (shareIntent.resolveActivity(
                requireActivity().packageManager) != null) {
            startActivity(shareIntent)
        }
    }
}
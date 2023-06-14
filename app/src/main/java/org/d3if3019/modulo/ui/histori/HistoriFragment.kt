package org.d3if3019.modulo.ui.histori

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.d3if3019.modulo.R
import org.d3if3019.modulo.databinding.FragmentHistoriBinding
import org.d3if3019.modulo.db.BmiDb



class HistoriFragment: Fragment() {
    private val viewModel: HistoriViewModel by lazy {
        val db = BmiDb.getInstance(requireContext())
        val factory = HistoriViewModelFactory(db.dao)
        ViewModelProvider(this, factory)[HistoriViewModel::class.java]
    }

    private lateinit var binding: FragmentHistoriBinding
    private lateinit var myAdapter: HistoriAdapter

    override fun onStart() {
        super.onStart()
        Log.i("HistoryFragment", "onStart dilakukan")
    }

    override fun onResume() {
        super.onResume()
        Log.i("HistoryFragment", "onResume dilakukan")
    }

    override fun onPause() {
        super.onPause()
        Log.i("HistoryFragment", "onPause dilakukan")
    }

    override fun onStop() {
        super.onStop()
        Log.i("HistoryFragment", "onStop dilakukan")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.i("HistoryFragment", "onDestroyView dilakukan")
    }

    override fun onDestroy() {
        Log.i("HistoryFragment", "onDestroy dilakukan")
        super.onDestroy()
    }

    override fun onDetach() {
        Log.i("HistoryFragment", "onDetach dilakukan")
        super.onDetach()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.i("HistoryFragment", "onAttach dilakukan")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("HistoryFragment", "onCreate dilakukan")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding = FragmentHistoriBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        myAdapter = HistoriAdapter()
        with(binding.recyclerView) {
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
            adapter = myAdapter
            setHasFixedSize(true)
        }
        viewModel.data.observe(viewLifecycleOwner, {
            binding.emptyView.visibility = if (it.isEmpty())
                View.VISIBLE else View.GONE
            myAdapter.submitList(it)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.histori_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_hapus) {
            hapusData()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun hapusData() {
        MaterialAlertDialogBuilder(requireContext())
            .setMessage(R.string.konfirmasi_hapus)
            .setPositiveButton(getString(R.string.hapus)) { _, _ ->
                viewModel.hapusData()
            }
            .setNegativeButton(getString(R.string.batal)) { dialog, _ ->
                dialog.cancel()
            }
            .show()
    }
}
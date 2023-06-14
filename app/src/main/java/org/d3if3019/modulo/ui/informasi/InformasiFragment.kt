package org.d3if3019.mobpro1.ui.main

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import org.d3if3019.modulo.MainActivity
import org.d3if3019.modulo.ui.informasi.InformasiViewModel
import org.d3if3019.modulo.databinding.FragmentInformasiBinding
import org.d3if3019.modulo.network.ApiStatus
import org.d3if3019.modulo.network.InfoApi

class InformasiFragment : Fragment() {

    private val viewModel: InformasiViewModel by lazy {
        ViewModelProvider(this)[InformasiViewModel::class.java]
    }

    private lateinit var binding: FragmentInformasiBinding
    private lateinit var myAdapter: InformasiAdapter

    override fun onStart() {
        super.onStart()
        Log.i("InformasiFragment", "onStart dilakukan")
    }

    override fun onResume() {
        super.onResume()
        Log.i("InformasiFragment", "onResume dilakukan")
    }

    override fun onPause() {
        super.onPause()
        Log.i("InformasiFragment", "onPause dilakukan")
    }

    override fun onStop() {
        super.onStop()
        Log.i("InformasiFragment", "onStop dilakukan")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.i("InformasiFragment", "onDestroyView dilakukan")
    }

    override fun onDestroy() {
        Log.i("InformasiFragment", "onDestroy dilakukan")
        super.onDestroy()
    }

    override fun onDetach() {
        Log.i("InformasiFragment", "onDetach dilakukan")
        super.onDetach()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.i("InformasiFragment", "onAttach dilakukan")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("InformasiFragment", "onCreate dilakukan")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInformasiBinding.inflate(layoutInflater, container, false)
        myAdapter = InformasiAdapter()
        with(binding.recyclerView) {
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    RecyclerView.VERTICAL
                )
            )
            adapter = myAdapter
            setHasFixedSize(true)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getData().observe(viewLifecycleOwner) {
            myAdapter.updateData(it)
        }

        viewModel.getStatus().observe(viewLifecycleOwner) {
            updateProgress(it)
        }

        viewModel.scheduleUpdater(requireActivity().application)
    }

    private fun updateProgress(status: ApiStatus) {
        when (status) {
            ApiStatus.LOADING -> {
                binding.progressBar.visibility = View.VISIBLE
            }
            ApiStatus.SUCCESS -> {
                binding.progressBar.visibility = View.GONE

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    requestNotificationPermission()
                }

            }
            ApiStatus.FAILED -> {
                binding.progressBar.visibility = View.GONE
                binding.networkError.visibility = View.VISIBLE
            }
        }
    }

    @RequiresApi
        (Build.VERSION_CODES.TIRAMISU)
    private fun requestNotificationPermission() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                MainActivity.PERMISSION_REQUEST_CODE
            )
        }
    }

}

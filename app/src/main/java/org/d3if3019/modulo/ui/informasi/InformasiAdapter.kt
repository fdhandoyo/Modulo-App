package org.d3if3019.mobpro1.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.d3if3019.modulo.R
import org.d3if3019.modulo.databinding.ListNameBinding
import org.d3if3019.modulo.model.HasilInfo
import org.d3if3019.modulo.network.InfoApi

class InformasiAdapter : RecyclerView.Adapter<InformasiAdapter.ViewHolder>() {

    private val data = mutableListOf<HasilInfo>()

    fun updateData(newData: List<HasilInfo>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ListNameBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(info: HasilInfo) = with(binding) {
            judulTextView.text = info.judul
            deskripsiTextView.text = info.penjelasan
            Glide.with(imageView.context)
                .load(InfoApi.getInfoUrl(info.imageId))
                .error(R.drawable.baseline_broken_image_24)
                .into(imageView)

            root.setOnClickListener {
                val message = root.context.getString(R.string.message, info.judul)
                Toast.makeText(root.context, message, Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListNameBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}

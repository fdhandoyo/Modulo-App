package org.d3if3019.modulo.ui.histori

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ListAdapter
import org.d3if3019.modulo.R
import org.d3if3019.modulo.databinding.ItemHistoriBinding
import org.d3if3019.modulo.db.BmiEntity
import org.d3if3019.modulo.model.hitungModulus
import java.text.FieldPosition
import java.text.SimpleDateFormat
import java.util.*

class HistoriAdapter: ListAdapter<BmiEntity, HistoriAdapter.ViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK =
        object : DiffUtil.ItemCallback<BmiEntity>() {
            override fun areItemsTheSame(
                oldData: BmiEntity, newData: BmiEntity
            ): Boolean {
                return oldData.id == newData.id
            }

            override fun areContentsTheSame(
                oldData: BmiEntity, newData: BmiEntity
            ): Boolean {
                return oldData == newData
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHistoriBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
    private val binding: ItemHistoriBinding
    ): RecyclerView.ViewHolder(binding.root) {
        private val dateFormatter = SimpleDateFormat("dd MMMM yyyy",
        Locale("id", "ID"))

        fun bind(item:BmiEntity) = with(binding) {
            val hasilModulus = item.hitungModulus()
            tanggalTextView.text = dateFormatter.format(Date(item.tanggal))
            modulusTextView.text = root.context.getString(R.string.hasil,
            hasilModulus.modulus)
        }
    }
}
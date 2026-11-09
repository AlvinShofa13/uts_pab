package com.example.utsapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.utsapp.data.Item058
import com.example.utsapp.databinding.ItemBarang058Binding
import java.text.NumberFormat
import java.util.Locale

class ItemAdapter058(
    private var itemList058: List<Item058>,
    private val onItemClick058: (Item058) -> Unit,
) : RecyclerView.Adapter<ItemAdapter058.ItemViewHolder058>() {

    class ItemViewHolder058(val binding058: ItemBarang058Binding) :
        RecyclerView.ViewHolder(binding058.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder058 {
        val binding = ItemBarang058Binding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false,
        )
        return ItemViewHolder058(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder058, position: Int) {
        val item = itemList058[position]
        with(holder.binding058) {
            tvNamaBarang058.text = item.nama_058
            tvJumlahBarang058.text = "Jumlah: ${item.jumlah_058} Pcs"
            tvHargaBarang058.text = "Harga: ${formatRupiah058(item.harga_058)}"

            val subtotal = item.jumlah_058 * item.harga_058
            tvSubtotal058.text = "Subtotal: ${formatRupiah058(subtotal)}"

            root.setOnClickListener {
                onItemClick058(item)
            }
        }
    }

    override fun getItemCount(): Int = itemList058.size

    fun updateData058(newList: List<Item058>) {
        itemList058 = newList
        notifyDataSetChanged()
    }

    private fun formatRupiah058(amount: Double): String {
        val localeId = Locale.Builder().setLanguage("id").setRegion("ID").build()
        val format = NumberFormat.getCurrencyInstance(localeId)
        return format.format(amount)
    }
}

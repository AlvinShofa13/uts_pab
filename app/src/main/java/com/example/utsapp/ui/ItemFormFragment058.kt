package com.example.utsapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.utsapp.R
import com.example.utsapp.data.AppDatabase058
import com.example.utsapp.data.InventarisRepository058
import com.example.utsapp.data.Item058
import com.example.utsapp.data.SessionManager058
import com.example.utsapp.databinding.FragmentItemForm058Binding
import kotlinx.coroutines.launch

class ItemFormFragment058 : Fragment() {

    private var _binding058: FragmentItemForm058Binding? = null
    private val binding058 get() = _binding058!!

    private lateinit var repository058: InventarisRepository058
    private lateinit var sessionManager058: SessionManager058

    private var itemId058: Int = -1
    private var currentItem058: Item058? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding058 = FragmentItemForm058Binding.inflate(inflater, container, false)
        return binding058.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val db = AppDatabase058.getDatabase058(requireContext())
        repository058 = InventarisRepository058(db)
        sessionManager058 = SessionManager058(requireContext())

        itemId058 = arguments?.getInt("itemId058", -1) ?: -1

        if (itemId058 > 0) {
            // Edit mode
            binding058.tvTitleForm058.text = getString(R.string.title_edit_item_058)
            binding058.btnDeleteItem058.visibility = View.VISIBLE
            loadItemDetails058(itemId058)
        } else {
            // Add mode
            binding058.tvTitleForm058.text = getString(R.string.title_add_item_058)
            binding058.btnDeleteItem058.visibility = View.GONE
        }

        binding058.btnSaveItem058.setOnClickListener {
            saveItem058()
        }

        binding058.btnDeleteItem058.setOnClickListener {
            deleteItem058()
        }
    }

    private fun loadItemDetails058(id: Int) {
        lifecycleScope.launch {
            val item = repository058.getItemById058(id)
            if (item != null) {
                currentItem058 = item
                binding058.etNamaBarang058.setText(item.nama_058)
                binding058.etJumlahBarang058.setText(item.jumlah_058.toString())
                binding058.etHargaBarang058.setText(item.harga_058.toString())
            }
        }
    }

    private fun saveItem058() {
        val nama = binding058.etNamaBarang058.text.toString().trim()
        val jumlahStr = binding058.etJumlahBarang058.text.toString().trim()
        val hargaStr = binding058.etHargaBarang058.text.toString().trim()

        if (nama.isEmpty()) {
            binding058.tilNamaBarang058.error = getString(R.string.msg_field_empty_058)
            return
        } else {
            binding058.tilNamaBarang058.error = null
        }

        if (jumlahStr.isEmpty()) {
            binding058.tilJumlahBarang058.error = getString(R.string.msg_field_empty_058)
            return
        } else {
            binding058.tilJumlahBarang058.error = null
        }

        if (hargaStr.isEmpty()) {
            binding058.tilHargaBarang058.error = getString(R.string.msg_field_empty_058)
            return
        } else {
            binding058.tilHargaBarang058.error = null
        }

        val jumlah = jumlahStr.toIntOrNull() ?: 0
        val harga = hargaStr.toDoubleOrNull() ?: 0.0
        val userId = sessionManager058.getUserId058()

        lifecycleScope.launch {
            if ((itemId058 > 0) && (currentItem058 != null)) {
                // Update
                val updatedItem = currentItem058!!.copy(
                    nama_058 = nama,
                    jumlah_058 = jumlah,
                    harga_058 = harga,
                )
                repository058.updateItem058(updatedItem)
                Toast.makeText(requireContext(), getString(R.string.msg_item_updated_058), Toast.LENGTH_SHORT).show()
            } else {
                // Insert
                val newItem = Item058(
                    userId_058 = userId,
                    nama_058 = nama,
                    jumlah_058 = jumlah,
                    harga_058 = harga,
                )
                repository058.insertItem058(newItem)
                Toast.makeText(requireContext(), getString(R.string.msg_item_saved_058), Toast.LENGTH_SHORT).show()
            }
            findNavController().navigateUp()
        }
    }

    private fun deleteItem058() {
        val item = currentItem058 ?: return
        lifecycleScope.launch {
            repository058.deleteItem058(item)
            Toast.makeText(requireContext(), getString(R.string.msg_item_deleted_058), Toast.LENGTH_SHORT).show()
            findNavController().navigateUp()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding058 = null
    }
}

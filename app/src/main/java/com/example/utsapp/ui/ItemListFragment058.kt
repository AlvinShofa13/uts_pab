package com.example.utsapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.utsapp.R
import com.example.utsapp.data.AppDatabase058
import com.example.utsapp.data.InventarisRepository058
import com.example.utsapp.data.SessionManager058
import com.example.utsapp.databinding.FragmentItemList058Binding

class ItemListFragment058 : Fragment() {

    private var _binding058: FragmentItemList058Binding? = null
    private val binding058 get() = _binding058!!

    private lateinit var repository058: InventarisRepository058
    private lateinit var sessionManager058: SessionManager058
    private lateinit var adapter058: ItemAdapter058

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding058 = FragmentItemList058Binding.inflate(inflater, container, false)
        return binding058.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val db = AppDatabase058.getDatabase058(requireContext())
        repository058 = InventarisRepository058(db)
        sessionManager058 = SessionManager058(requireContext())

        if (!sessionManager058.isLoggedIn058()) {
            findNavController().navigate(R.id.action_itemListFragment058_to_itemFormFragment058)
            return
        }

        setupRecyclerView058()
        loadUserItems058()

        binding058.fabAddItem058.setOnClickListener {
            val bundle = Bundle().apply { putInt("itemId058", -1) }
            findNavController().navigate(R.id.action_itemListFragment058_to_itemFormFragment058, bundle)
        }
    }

    private fun setupRecyclerView058() {
        adapter058 = ItemAdapter058(emptyList()) { selectedItem ->
            val bundle = Bundle().apply { putInt("itemId058", selectedItem.id_058) }
            findNavController().navigate(R.id.action_itemListFragment058_to_itemFormFragment058, bundle)
        }

        binding058.rvItems058.layoutManager = LinearLayoutManager(requireContext())
        binding058.rvItems058.adapter = adapter058
    }

    private fun loadUserItems058() {
        val userId = sessionManager058.getUserId058()

        // Real-time Data Isolation: observe items belonging ONLY to logged in userId
        repository058.getItemsByUserId058(userId).observe(viewLifecycleOwner) { items ->
            if (items.isNullOrEmpty()) {
                binding058.rvItems058.visibility = View.GONE
                binding058.tvEmptyState058.visibility = View.VISIBLE
            } else {
                binding058.rvItems058.visibility = View.VISIBLE
                binding058.tvEmptyState058.visibility = View.GONE
                adapter058.updateData058(items)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding058 = null
    }
}

package com.example.utsapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.utsapp.R
import com.example.utsapp.data.AppDatabase058
import com.example.utsapp.data.InventarisRepository058
import com.example.utsapp.data.SessionManager058
import com.example.utsapp.databinding.FragmentDashboard058Binding
import java.text.NumberFormat
import java.util.Locale

class DashboardFragment058 : Fragment() {

    private var _binding058: FragmentDashboard058Binding? = null
    private val binding058 get() = _binding058!!

    private lateinit var repository058: InventarisRepository058
    private lateinit var sessionManager058: SessionManager058

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding058 = FragmentDashboard058Binding.inflate(inflater, container, false)
        return binding058.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val db = AppDatabase058.getDatabase058(requireContext())
        repository058 = InventarisRepository058(db)
        sessionManager058 = SessionManager058(requireContext())

        if (!sessionManager058.isLoggedIn058()) {
            findNavController().navigate(R.id.action_dashboardFragment058_to_loginFragment058)
            return
        }

        val username = sessionManager058.getUsername058()
        val userId = sessionManager058.getUserId058()

        binding058.tvWelcomeUser058.text = getString(R.string.welcome_user_058, username)

        // Bonus: Observe Real-time Total Nilai Inventaris (SUM of jumlah * harga)
        repository058.getTotalNilaiInventaris058(userId).observe(viewLifecycleOwner) { totalNilai ->
            val value = totalNilai ?: 0.0
            binding058.tvTotalNilai058.text = formatRupiah058(value)
        }

        // Observe Real-time Total Jenis Barang
        repository058.getTotalJumlahItem058(userId).observe(viewLifecycleOwner) { count ->
            val totalCount = count ?: 0
            binding058.tvTotalJenis058.text = getString(R.string.total_jenis_format_058, totalCount)
        }

        binding058.btnViewList058.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment058_to_itemListFragment058)
        }

        binding058.btnAddItem058.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment058_to_itemFormFragment058)
        }

        binding058.btnLogout058.setOnClickListener {
            sessionManager058.clearSession058()
            Toast.makeText(requireContext(), getString(R.string.msg_logout_success_058), Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_dashboardFragment058_to_loginFragment058)
        }
    }

    private fun formatRupiah058(amount: Double): String {
        val localeId = Locale.Builder().setLanguage("in").setRegion("ID").build()
        val format = NumberFormat.getCurrencyInstance(localeId)
        return format.format(amount)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding058 = null
    }
}

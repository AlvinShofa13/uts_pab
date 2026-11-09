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
import com.example.utsapp.data.SessionManager058
import com.example.utsapp.databinding.FragmentLogin058Binding
import kotlinx.coroutines.launch

class LoginFragment058 : Fragment() {

    private var _binding058: FragmentLogin058Binding? = null
    private val binding058 get() = _binding058!!

    private lateinit var repository058: InventarisRepository058
    private lateinit var sessionManager058: SessionManager058

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding058 = FragmentLogin058Binding.inflate(inflater, container, false)
        return binding058.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val db = AppDatabase058.getDatabase058(requireContext())
        repository058 = InventarisRepository058(db)
        sessionManager058 = SessionManager058(requireContext())

        // Check if user is already logged in -> auto-login to Dashboard
        if (sessionManager058.isLoggedIn058()) {
            findNavController().navigate(R.id.action_loginFragment058_to_dashboardFragment058)
            return
        }

        binding058.btnLogin058.setOnClickListener {
            handleLogin058()
        }

        binding058.tvRegisterLink058.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment058_to_registerFragment058)
        }
    }

    private fun handleLogin058() {
        val username = binding058.etUsername058.text.toString().trim()
        val password = binding058.etPassword058.text.toString().trim()

        if (username.isEmpty()) {
            binding058.tilUsername058.error = getString(R.string.msg_field_empty_058)
            return
        } else {
            binding058.tilUsername058.error = null
        }

        if (password.isEmpty()) {
            binding058.tilPassword058.error = getString(R.string.msg_field_empty_058)
            return
        } else {
            binding058.tilPassword058.error = null
        }

        lifecycleScope.launch {
            val user = repository058.loginUser058(username, password)
            if (user != null) {
                // Save session
                sessionManager058.saveSession058(user.id_058, user.username_058)
                Toast.makeText(requireContext(), "Login Berhasil! Selamat datang ${user.username_058}", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_loginFragment058_to_dashboardFragment058)
            } else {
                Toast.makeText(requireContext(), getString(R.string.msg_login_failed_058), Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding058 = null
    }
}

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
import com.example.utsapp.databinding.FragmentRegister058Binding
import kotlinx.coroutines.launch

class RegisterFragment058 : Fragment() {

    private var _binding058: FragmentRegister058Binding? = null
    private val binding058 get() = _binding058!!

    private lateinit var repository058: InventarisRepository058

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding058 = FragmentRegister058Binding.inflate(inflater, container, false)
        return binding058.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val db = AppDatabase058.getDatabase058(requireContext())
        repository058 = InventarisRepository058(db)

        binding058.btnRegister058.setOnClickListener {
            handleRegister058()
        }

        binding058.tvLoginLink058.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment058_to_loginFragment058)
        }
    }

    private fun handleRegister058() {
        val username = binding058.etUsername058.text.toString().trim()
        val password = binding058.etPassword058.text.toString().trim()
        val confirmPassword = binding058.etConfirmPassword058.text.toString().trim()

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

        if (password != confirmPassword) {
            binding058.tilConfirmPassword058.error = getString(R.string.msg_password_mismatch_058)
            return
        } else {
            binding058.tilConfirmPassword058.error = null
        }

        lifecycleScope.launch {
            val result = repository058.registerUser058(username, password)
            if (result == -1L) {
                Toast.makeText(requireContext(), getString(R.string.msg_username_exists_058), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), getString(R.string.msg_register_success_058), Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_registerFragment058_to_loginFragment058)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding058 = null
    }
}

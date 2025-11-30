package com.projeto.caphelper.ui.auth

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.projeto.caphelper.R

class LoginFragment : Fragment(R.layout.fragment_login) {

    private val userRepository: UserRepository by lazy {
        UserRepositoryImpl(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnLogin = view.findViewById<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener {

            if (userRepository.hasPerfil()) {
               
                findNavController().navigate(
                    R.id.action_loginFragment_to_homeFragment
                )
            } else {

                findNavController().navigate(
                    R.id.action_loginFragment_to_cadastroFragment
                )
            }
        }
    }
}

package com.projeto.caphelper.ui.auth

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.projeto.caphelper.R

class AuthFragment : Fragment(R.layout.fragment_auth) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnEntrar = view.findViewById<Button>(R.id.btnEntrar)
        val btnCriarConta = view.findViewById<Button>(R.id.btnCriarConta)

        btnEntrar.setOnClickListener {
            findNavController().navigate(
                R.id.action_authFragment_to_loginFragment
            )
        }

        btnCriarConta.setOnClickListener {
            findNavController().navigate(
                R.id.action_authFragment_to_cadastroFragment
            )
        }
    }
}

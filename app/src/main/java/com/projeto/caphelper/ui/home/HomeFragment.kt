package com.projeto.caphelper.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.projeto.caphelper.R

class HomeFragment : Fragment(R.layout.fragment_home) {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val btnInfo: Button = view.findViewById(R.id.btnHomeInfo)
        val btnDenuncia: Button = view.findViewById(R.id.btnHomeDenuncia)
        val btnRegistros: Button = view.findViewById(R.id.btnHomeRegistros)
        val btnIndicadores: Button = view.findViewById(R.id.btnHomeIndicadores)
        val btnBoOnline: Button = view.findViewById(R.id.btnHomeBoOnline)
        val btnConfig: Button = view.findViewById(R.id.btnHomeConfig)
        val btnMeuPerfil = view.findViewById<Button>(R.id.btnMeuPerfil)

        btnInfo.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_infoFragment)
        }

        btnDenuncia.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_denunciaFragment)
        }

        btnRegistros.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_registrosFragment)
        }

        btnIndicadores.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_indicadoresFragment)
        }

        btnBoOnline.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_boOnlineFragment)
        }

        btnConfig.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_configFragment)
        }

        btnMeuPerfil.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_cadastroFragment)
        }
    }
}
package com.projeto.caphelper.ui.config

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.projeto.caphelper.R
import com.projeto.caphelper.data.DenunciaRepository

class ConfigFragment : Fragment(R.layout.fragment_config) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnLimparRegistros = view.findViewById<Button>(R.id.btnLimparRegistros)

        btnLimparRegistros.setOnClickListener {
            // Limpa apenas o histórico local da tela "Meus registros"
            DenunciaRepository.limparHistorico()

            Toast.makeText(
                requireContext(),
                getString(R.string.config_msg_limpo),
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}

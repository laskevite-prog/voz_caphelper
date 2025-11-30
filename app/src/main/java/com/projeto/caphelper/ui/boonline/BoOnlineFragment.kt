package com.projeto.caphelper.ui.boonline

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.projeto.caphelper.R

class BoOnlineFragment : Fragment(R.layout.fragment_bo_online) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnDelegaciaVirtual = view.findViewById<Button>(R.id.btnAbrirDelegaciaVirtual)
        val btnLigar180 = view.findViewById<Button>(R.id.btnLigar180)

        btnDelegaciaVirtual.setOnClickListener {
            Toast.makeText(
                requireContext(),
                "Abrindo Delegacia Virtual...",
                Toast.LENGTH_SHORT
            ).show()

            val urlDelegaciaVirtual = "https://delegaciavirtual.sinesp.gov.br/"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(urlDelegaciaVirtual))

            try {
                startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(
                    requireContext(),
                    "Não foi encontrado um navegador para abrir a Delegacia Virtual.",
                    Toast.LENGTH_SHORT
                ).show()
            } catch (e: Exception) {
                Toast.makeText(
                    requireContext(),
                    "Não foi possível abrir a Delegacia Virtual.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        btnLigar180.setOnClickListener {
            Toast.makeText(
                requireContext(),
                "Abrindo discador com 180...",
                Toast.LENGTH_SHORT
            ).show()

            val uri = Uri.parse("tel:180")
            val intent = Intent(Intent.ACTION_DIAL, uri)

            try {
                startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(
                    requireContext(),
                    "Não foi encontrado um app de telefone para ligar para o 180.",
                    Toast.LENGTH_SHORT
                ).show()
            } catch (e: Exception) {
                Toast.makeText(
                    requireContext(),
                    "Não foi possível abrir o discador para o 180.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}

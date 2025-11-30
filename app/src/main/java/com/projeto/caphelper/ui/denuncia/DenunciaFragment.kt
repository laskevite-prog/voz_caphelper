package com.projeto.caphelper.ui.denuncia

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.projeto.caphelper.R
import com.projeto.caphelper.data.DenunciaRepository
import com.projeto.caphelper.model.Denuncia
import com.projeto.caphelper.model.TipoViolencia

class DenunciaFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_denuncia, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val etAgressorNome: EditText = view.findViewById(R.id.etAgressorNome)
        val etRelacao: EditText = view.findViewById(R.id.etRelacao)
        val etTipoViolencia: EditText = view.findViewById(R.id.etTipoViolencia)
        val etLocal: EditText = view.findViewById(R.id.etLocal)
        val etDescricao: EditText = view.findViewById(R.id.etDescricao)
        val btnEnviar: Button = view.findViewById(R.id.btnEnviarDenuncia)

        btnEnviar.setOnClickListener {
            val agressorNome = etAgressorNome.text.toString().trim()
            val relacao = etRelacao.text.toString().trim()
            val tipoViolenciaTexto = etTipoViolencia.text.toString().trim()
            val local = etLocal.text.toString().trim()
            val descricao = etDescricao.text.toString().trim()

            if (agressorNome.isEmpty() || tipoViolenciaTexto.isEmpty() || local.isEmpty()) {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.denuncia_msg_campos_obrigatorios),
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            val tipoViolenciaEnum = when (tipoViolenciaTexto.lowercase()) {
                "física", "fisica" -> TipoViolencia.FISICA
                "sexual" -> TipoViolencia.SEXUAL
                "psicológica", "psicologica" -> TipoViolencia.PSICOLOGICA
                "moral" -> TipoViolencia.MORAL
                "patrimonial" -> TipoViolencia.PATRIMONIAL
                else -> TipoViolencia.OUTRA
            }

            val denuncia = Denuncia(
                agressorNome = agressorNome,
                relaçãoComAgressor = if (relacao.isEmpty()) "Não informado" else relacao,
                tipoViolencia = tipoViolenciaEnum, // ✅ agora é enum, não String
                localOcorrencia = local,
                descricao = if (descricao.isEmpty()) "Sem descrição detalhada" else descricao
            )

            DenunciaRepository.adicionarDenuncia(denuncia)

            Toast.makeText(
                requireContext(),
                getString(R.string.denuncia_msg_sucesso),
                Toast.LENGTH_SHORT
            ).show()
            
            findNavController().navigate(R.id.registrosFragment)
        }
    }
}

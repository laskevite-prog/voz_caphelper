package com.projeto.caphelper.ui.auth

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.projeto.caphelper.R

class CadastroFragment : Fragment(R.layout.fragment_cadastro) {

    private val userRepository: UserRepository by lazy {
        UserRepositoryImpl(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Ligando as views do XML aos objetos Kotlin
        val etNomeCompleto = view.findViewById<EditText>(R.id.etNomeCompleto)
        val rgGenero = view.findViewById<RadioGroup>(R.id.rgGenero)
        val rbGeneroCis = view.findViewById<RadioButton>(R.id.rbGeneroCis)
        val rbGeneroTrans = view.findViewById<RadioButton>(R.id.rbGeneroTrans)
        val etDataNascimento = view.findViewById<EditText>(R.id.etDataNascimento)
        val etIdade = view.findViewById<EditText>(R.id.etIdade)
        val etEstadoCivil = view.findViewById<EditText>(R.id.etEstadoCivil)
        val etRegiao = view.findViewById<EditText>(R.id.etRegiao)
        val cbJaSofreuAbuso = view.findViewById<CheckBox>(R.id.cbJaSofreuAbuso)
        val etRelacaoAgressor = view.findViewById<EditText>(R.id.etRelacaoAgressor)
        val cbAceitoTermos = view.findViewById<CheckBox>(R.id.cbAceitoTermos)
        val btnSalvarCadastro = view.findViewById<Button>(R.id.btnSalvarCadastro)

        btnSalvarCadastro.setOnClickListener {
            val nome = etNomeCompleto.text.toString().trim()
            val dataNascimento = etDataNascimento.text.toString().trim()
            val idadeStr = etIdade.text.toString().trim()
            val estadoCivil = etEstadoCivil.text.toString().trim()
            val regiao = etRegiao.text.toString().trim()
            val relacaoAgressor = etRelacaoAgressor.text.toString().trim()
            val aceitouTermos = cbAceitoTermos.isChecked

            // Validações básicas

            if (nome.isEmpty()) {
                etNomeCompleto.error = getString(R.string.cadastro_erro_nome_obrigatorio)
                return@setOnClickListener
            }

            if (rgGenero.checkedRadioButtonId == -1) {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.cadastro_erro_genero_obrigatorio),
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            if (dataNascimento.isEmpty()) {
                etDataNascimento.error = getString(R.string.cadastro_erro_data_obrigatoria)
                return@setOnClickListener
            }

            if (estadoCivil.isEmpty()) {
                etEstadoCivil.error = getString(R.string.cadastro_erro_estado_civil_obrigatorio)
                return@setOnClickListener
            }

            if (regiao.isEmpty()) {
                etRegiao.error = getString(R.string.cadastro_erro_regiao_obrigatoria)
                return@setOnClickListener
            }

            if (relacaoAgressor.isEmpty()) {
                etRelacaoAgressor.error = getString(R.string.cadastro_erro_relacao_agressor_obrigatoria)
                return@setOnClickListener
            }

            if (!aceitouTermos) {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.cadastro_erro_termos_obrigatorio),
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            // Converte idade (se preenchida)
            val idade: Int? = idadeStr.toIntOrNull()

            // Define o gênero
            val genero = when {
                rbGeneroCis.isChecked -> Genero.CIS
                rbGeneroTrans.isChecked -> Genero.TRANS
                else -> Genero.CIS // fallback, mas não deve chegar aqui por causa da validação
            }

            // Monta o Perfil completo
            val perfil = Perfil(
                nomeCompleto = nome,
                genero = genero,
                dataNascimento = dataNascimento,
                idade = idade,
                estadoCivil = estadoCivil,
                regiao = regiao,
                jaSofreuAbuso = cbJaSofreuAbuso.isChecked,
                relacaoComAgressor = relacaoAgressor
            )

            // Salva o perfil
            userRepository.salvarPerfil(perfil)

            // Navega para a Home
            findNavController().navigate(
                R.id.action_cadastroFragment_to_homeFragment
            )
        }
    }
}

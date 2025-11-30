package com.projeto.caphelper.ui.auth

import android.content.Context

// 1) Perfil completo
data class Perfil(
    val nomeCompleto: String,
    val genero: Genero,          // CIS ou TRANS
    val dataNascimento: String,  // ex.: "01/01/1990"
    val idade: Int?,             // opcional, pode derivar da data depois se quiser
    val estadoCivil: String,     // "Solteira", "Casada", etc.
    val regiao: String,          // bairro/cidade/região
    val jaSofreuAbuso: Boolean,
    val relacaoComAgressor: String
)
// 2) Enum para Gênero
enum class Genero {
    CIS,
    TRANS
}

interface UserRepository {
    fun hasPerfil(): Boolean
    fun salvarPerfil(perfil: Perfil)
    fun getPerfil(): Perfil?
}

class UserRepositoryImpl(context: Context) : UserRepository {

    private val prefs = context.getSharedPreferences("caphelper_prefs", Context.MODE_PRIVATE)

    override fun hasPerfil(): Boolean {
        return prefs.contains("perfil_nome_completo")
    }

    override fun salvarPerfil(perfil: Perfil) {
        prefs.edit()
            .putString("perfil_nome_completo", perfil.nomeCompleto)
            .putString("perfil_genero", perfil.genero.name)
            .putString("perfil_data_nascimento", perfil.dataNascimento)
            .putInt("perfil_idade", perfil.idade ?: -1)
            .putString("perfil_estado_civil", perfil.estadoCivil)
            .putString("perfil_regiao", perfil.regiao)
            .putBoolean("perfil_ja_sofreu_abuso", perfil.jaSofreuAbuso)
            .putString("perfil_relacao_agressor", perfil.relacaoComAgressor)
            .apply()
    }

    override fun getPerfil(): Perfil? {
        if (!hasPerfil()) return null

        val nome = prefs.getString("perfil_nome_completo", null) ?: return null
        val generoStr = prefs.getString("perfil_genero", null) ?: return null
        val dataNascimento = prefs.getString("perfil_data_nascimento", "") ?: ""
        val idade = prefs.getInt("perfil_idade", -1).takeIf { it >= 0 }
        val estadoCivil = prefs.getString("perfil_estado_civil", "") ?: ""
        val regiao = prefs.getString("perfil_regiao", "") ?: ""
        val jaSofreuAbuso = prefs.getBoolean("perfil_ja_sofreu_abuso", false)
        val relacaoAgg = prefs.getString("perfil_relacao_agressor", "") ?: ""

        val genero = try {
            Genero.valueOf(generoStr)
        } catch (e: IllegalArgumentException) {
            Genero.CIS // fallback básico
        }

        return Perfil(
            nomeCompleto = nome,
            genero = genero,
            dataNascimento = dataNascimento,
            idade = idade,
            estadoCivil = estadoCivil,
            regiao = regiao,
            jaSofreuAbuso = jaSofreuAbuso,
            relacaoComAgressor = relacaoAgg
        )
    }
}
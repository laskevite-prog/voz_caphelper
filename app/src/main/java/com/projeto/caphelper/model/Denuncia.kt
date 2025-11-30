package com.projeto.caphelper.model

data class Denuncia(
    val id: Long = 0L,
    val agressorNome: String,
    val relaçãoComAgressor: String,
    val tipoViolencia: TipoViolencia,
    val localOcorrencia: String,
    val descricao: String,
    val criadoEmMillis: Long = System.currentTimeMillis()
)
enum class TipoViolencia {
    FISICA,
    SEXUAL,
    PSICOLOGICA,
    MORAL,
    PATRIMONIAL,
    OUTRA
}
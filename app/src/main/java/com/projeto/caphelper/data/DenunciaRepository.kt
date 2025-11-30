package com.projeto.caphelper.data

import com.projeto.caphelper.model.Denuncia

object DenunciaRepository {


    private val denunciasIndicadores = mutableListOf<Denuncia>()

    // Histórico local
    private val denunciasHistorico = mutableListOf<Denuncia>()

    /**
     * Adiciona uma denúncia nas duas coleções:
     * - indicadores (para estatísticas)
     * - histórico (para consulta da usuária)
     */
    fun adicionarDenuncia(denuncia: Denuncia) {
        denunciasIndicadores.add(denuncia)
        denunciasHistorico.add(denuncia)
    }

    /**
     * Lista usada pela tela de Indicadores.
     * Esta lista NÃO é afetada pelo "limpar histórico" da tela de Config.
     */
    fun listarDenunciasIndicadores(): List<Denuncia> {
        return denunciasIndicadores.toList()
    }

    /**
     * Lista usada pela tela "Meus registros".
     * Esta lista pode ser limpa pela tela de Config.
     */
    fun listarDenunciasHistorico(): List<Denuncia> {
        return denunciasHistorico.toList()
    }

    /**
     * Limpa apenas o histórico local (Meus registros),
     * mantendo a base de indicadores intacta.
     */
    fun limparHistorico() {
        denunciasHistorico.clear()
    }

    /**
     * (Opcional) Limpar tudo – se um dia quiser usar:
     * apaga tanto indicadores quanto histórico.
     */
    fun limparTudo() {
        denunciasIndicadores.clear()
        denunciasHistorico.clear()
    }
}

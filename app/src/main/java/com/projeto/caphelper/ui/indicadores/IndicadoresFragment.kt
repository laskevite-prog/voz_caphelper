package com.projeto.caphelper.ui.indicadores

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.projeto.caphelper.R
import com.projeto.caphelper.data.DenunciaRepository
import com.projeto.caphelper.model.TipoViolencia
import java.util.Locale

class IndicadoresFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_indicadores, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvEmpty: TextView = view.findViewById(R.id.tvIndicadoresEmpty)
        val layoutDados: LinearLayout = view.findViewById(R.id.layoutIndicadoresDados)

        val tvTotal: TextView = view.findViewById(R.id.tvTotalDenuncias)
        val tvParceiro: TextView = view.findViewById(R.id.tvParceiro)
        val tvNaoParceiro: TextView = view.findViewById(R.id.tvNaoParceiro)
        val tvSexualParceiro: TextView = view.findViewById(R.id.tvSexualParceiro)
        val tvSexualNaoParceiro: TextView = view.findViewById(R.id.tvSexualNaoParceiro)

        val denuncias = DenunciaRepository.listarDenunciasIndicadores()

        val total = denuncias.size

        if (total == 0) {
            // Não há dados
            tvEmpty.visibility = View.VISIBLE
            layoutDados.visibility = View.GONE
            return
        }

        tvEmpty.visibility = View.GONE
        layoutDados.visibility = View.VISIBLE


        val palavrasParceiro = listOf(
            "companheiro", "marido", "esposo", "namorado", "parceiro", "ex"
        )

        val denunciasParceiro = denuncias.filter { denuncia ->
            val rel = denuncia.relaçãoComAgressor.lowercase(Locale.getDefault())
            palavrasParceiro.any { palavra -> rel.contains(palavra) }
        }

        val denunciasNaoParceiro = denuncias - denunciasParceiro.toSet()

        val totalParceiro = denunciasParceiro.size
        val totalNaoParceiro = denunciasNaoParceiro.size


        fun isViolenciaSexual(tipo: TipoViolencia): Boolean {
            return tipo == TipoViolencia.SEXUAL
        }

        val sexualParceiro = denunciasParceiro.count { isViolenciaSexual(it.tipoViolencia) }
        val sexualNaoParceiro = denunciasNaoParceiro.count { isViolenciaSexual(it.tipoViolencia) }

        fun formatPercent(parte: Int, total: Int): String {
            if (total == 0) return "0%"
            val perc = (parte.toDouble() / total.toDouble()) * 100.0
            return String.format(Locale.getDefault(), "%.1f%%", perc)
        }

        // Preenche os textos
        tvTotal.text = "Total de denúncias: $total"

        val percParceiro = formatPercent(totalParceiro, total)
        val percNaoParceiro = formatPercent(totalNaoParceiro, total)

        tvParceiro.text = "Com parceiro/ex (ODS 5.2.1): $totalParceiro ($percParceiro)"
        tvNaoParceiro.text = "Outros agressores (ODS 5.2.2): $totalNaoParceiro ($percNaoParceiro)"

        tvSexualParceiro.text =
            "Violência sexual com parceiro/ex: $sexualParceiro caso(s)"
        tvSexualNaoParceiro.text =
            "Violência sexual com outros agressores: $sexualNaoParceiro caso(s)"
    }
}

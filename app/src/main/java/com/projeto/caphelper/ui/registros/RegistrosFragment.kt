package com.projeto.caphelper.ui.registros

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.projeto.caphelper.R
import com.projeto.caphelper.data.DenunciaRepository
import com.projeto.caphelper.model.Denuncia
import com.projeto.caphelper.model.TipoViolencia

class RegistrosFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_registros, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvEmpty: TextView = view.findViewById(R.id.tvRegistrosEmpty)
        val layoutLista: LinearLayout = view.findViewById(R.id.layoutListaRegistros)


        val denuncias: List<Denuncia> = DenunciaRepository.listarDenunciasHistorico()

        if (denuncias.isEmpty()) {
            tvEmpty.visibility = View.VISIBLE
            layoutLista.visibility = View.GONE
            return
        }

        tvEmpty.visibility = View.GONE
        layoutLista.visibility = View.VISIBLE

        val inflater = LayoutInflater.from(requireContext())

        denuncias.forEach { denuncia ->
            val itemView = inflater.inflate(
                R.layout.item_registro_denuncia,
                layoutLista,
                false
            )

            val tvAgressor = itemView.findViewById<TextView>(R.id.tvRegistroAgressor)
            val tvRelacao = itemView.findViewById<TextView>(R.id.tvRegistroRelacao)
            val tvTipo = itemView.findViewById<TextView>(R.id.tvRegistroTipo)
            val tvLocal = itemView.findViewById<TextView>(R.id.tvRegistroLocal)
            val tvDescricao = itemView.findViewById<TextView>(R.id.tvRegistroDescricao)

            tvAgressor.text = "Agressor: ${denuncia.agressorNome}"
            tvRelacao.text = "Relação: ${denuncia.relaçãoComAgressor}"
            tvTipo.text = "Tipo de violência: ${formatarTipoViolencia(denuncia.tipoViolencia)}"
            tvLocal.text = "Local: ${denuncia.localOcorrencia}"

            val desc = if (denuncia.descricao.length > 120) {
                denuncia.descricao.substring(0, 117) + "..."
            } else {
                denuncia.descricao
            }
            tvDescricao.text = "Descrição: $desc"

            layoutLista.addView(itemView)
        }
    }

    private fun formatarTipoViolencia(tipo: TipoViolencia): String {
        return when (tipo) {
            TipoViolencia.FISICA -> "Física"
            TipoViolencia.SEXUAL -> "Sexual"
            TipoViolencia.PSICOLOGICA -> "Psicológica"
            TipoViolencia.MORAL -> "Moral"
            TipoViolencia.PATRIMONIAL -> "Patrimonial"
            TipoViolencia.OUTRA -> "Outra"
        }
    }
}

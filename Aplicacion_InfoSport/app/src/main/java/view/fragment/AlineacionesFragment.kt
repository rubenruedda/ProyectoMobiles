package view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.aplicacion_infosport.databinding.FragmentAlineacionesBinding

class AlineacionesFragment : Fragment() {

    private var _binding: FragmentAlineacionesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAlineacionesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Obtener alineaciones
        val local = arguments?.getStringArrayList(ARG_LOCAL) ?: emptyList()
        val visitante = arguments?.getStringArrayList(ARG_VISITANTE) ?: emptyList()

        // Simplemente mostramos los nombres de los jugadores
        binding.tvAlineacionLocal.text = "Local:\n${local.joinToString("\n")}"
        binding.tvAlineacionVisitante.text = "Visitante:\n${visitante.joinToString("\n")}"

        // TODO: En una versión final, usar un diseño más complejo con Grid/RecyclerView
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val ARG_LOCAL = "alineacion_local"
        private const val ARG_VISITANTE = "alineacion_visitante"

        fun newInstance(local: List<String>, visitante: List<String>) =
            AlineacionesFragment().apply {
                arguments = Bundle().apply {
                    putStringArrayList(ARG_LOCAL, ArrayList(local))
                    putStringArrayList(ARG_VISITANTE, ArrayList(visitante))
                }
            }
    }
}
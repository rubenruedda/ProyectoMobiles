package view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.aplicacion_infosport.databinding.FragmentPartidosLigaBinding
import view.Liga
import model.Partido
import view.Info_Partido
import view.adapter.PartidoAdapter
import viewModel.LigaViewModel

class PartidosLigaFragment : Fragment() {

    private var _binding: FragmentPartidosLigaBinding? = null
    private val binding get() = _binding!!

    // 1. Obtener ViewModel compartido
    private val viewModel: LigaViewModel by activityViewModels()

    private lateinit var partidoAdapter: PartidoAdapter
    private var tipo: String? = null

    companion object {
        const val TIPO_RESULTADOS = "RESULTADOS"
        const val TIPO_PROXIMOS = "PROXIMOS"
        private const val ARG_TIPO = "tipo_fragment"

        // Factory method para pasar el tipo
        fun newInstance(tipo: String): PartidosLigaFragment {
            val fragment = PartidosLigaFragment()
            val args = Bundle()
            args.putString(ARG_TIPO, tipo)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Recuperar el tipo
        tipo = arguments?.getString(ARG_TIPO)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPartidosLigaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 2. Configurar RecyclerView (reutilizamos PartidoAdapter)
        setupRvPartidos()

        // 3. Observar el LiveData correcto según el tipo
        when (tipo) {
            TIPO_RESULTADOS -> {
                viewModel.resultados.observe(viewLifecycleOwner) { partidos ->
                    partidoAdapter.submitList(partidos)
                }
            }
            TIPO_PROXIMOS -> {
                viewModel.partidosProximos.observe(viewLifecycleOwner) { partidos ->
                    partidoAdapter.submitList(partidos)
                }
            }
        }
    }

    private fun setupRvPartidos() {
        partidoAdapter = PartidoAdapter { partido ->
            lanzarActivityInfoPartido(partido)
        }
        binding.rvPartidosLiga.adapter = partidoAdapter
    }

    private fun lanzarActivityInfoPartido(partido: Partido) {
        val intent = Intent(requireContext(), Info_Partido::class.java)
        intent.putExtra("PARTIDO_ID", partido.id)
        val ligaNombre = (activity as? Liga)?.binding?.toolbarLiga?.title?.toString()
        if (ligaNombre != null) {
            intent.putExtra("LIGA_NOMBRE", ligaNombre)
        }
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
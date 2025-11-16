package view.fragment


import view.adapter.ClasificacionAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.aplicacion_infosport.databinding.FragmentClasificacionBinding
import viewModel.LigaViewModel


class ClasificacionFragment : Fragment() {

    private var _binding: FragmentClasificacionBinding? = null
    private val binding get() = _binding!!

    // 1. OBTENER EL VIEWMODEL DE LA ACTIVITY (COMPARTIDO)
    private val viewModel: LigaViewModel by activityViewModels()

    private lateinit var clasificacionAdapter: ClasificacionAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentClasificacionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 2. Configurar RecyclerView
        setupRvClasificacion()

        // 3. Observar el LiveData del ViewModel compartido
        // El ID de la liga ya fue seteado por LigaActivity
        viewModel.clasificacion.observe(viewLifecycleOwner) { clasificacion ->
            clasificacionAdapter.submitList(clasificacion)
        }
    }

    private fun setupRvClasificacion() {
        clasificacionAdapter = ClasificacionAdapter()
        binding.rvClasificacion.adapter = clasificacionAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
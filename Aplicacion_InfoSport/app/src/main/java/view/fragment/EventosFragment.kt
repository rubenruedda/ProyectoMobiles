package view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.aplicacion_infosport.databinding.FragmentEventosBinding
import view.adapter.EventoAdapter
import viewModel.InfoPartidoViewModel

class EventosFragment : Fragment() {

    private var _binding: FragmentEventosBinding? = null
    private val binding get() = _binding!!

    // ViewModel compartido de la InfoPartidoActivity
    private val viewModel: InfoPartidoViewModel by activityViewModels()
    private lateinit var eventoAdapter: EventoAdapter
    private var partidoId: Int = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEventosBinding.inflate(inflater, container, false)

        // Recuperamos el ID del partido de los argumentos de la Activity
        partidoId = activity?.intent?.getIntExtra("PARTIDO_ID", -1) ?: -1

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRvEventos()

        if (partidoId != -1) {
            viewModel.getEventos(partidoId).observe(viewLifecycleOwner) { eventos ->
                eventoAdapter.submitList(eventos)
            }
        }
    }

    private fun setupRvEventos() {
        eventoAdapter = EventoAdapter()
        binding.rvEventosFragment.adapter = eventoAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
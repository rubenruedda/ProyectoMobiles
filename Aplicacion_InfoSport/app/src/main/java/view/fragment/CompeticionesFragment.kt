package view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.aplicacion_infosport.databinding.FragmentCompeticionesBinding
import model.Liga
import view.adapter.CompeticionAdapter
import viewModel.CompeticionesViewModel
import view.Liga as LigaActivity

class CompeticionesFragment : Fragment() {

    private var _binding: FragmentCompeticionesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CompeticionesViewModel by viewModels()
    private lateinit var competicionAdapter: CompeticionAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCompeticionesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRvCompeticiones()
        setupSearchView() // Conectamos el SearchView

        // Observamos la nueva lista filtrada
        viewModel.listaFiltradaYAgrupada.observe(viewLifecycleOwner) { lista ->
            competicionAdapter.submitList(lista)
        }
    }

    private fun setupRvCompeticiones() {
        competicionAdapter = CompeticionAdapter { liga ->
            lanzarActivityLiga(liga)
        }
        binding.rvCompeticiones.adapter = competicionAdapter
    }

    private fun setupSearchView() {
        binding.searchViewCompeticiones.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.searchViewCompeticiones.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Actualiza el ViewModel con la nueva consulta
                viewModel.setSearchQuery(newText.orEmpty())
                return true
            }
        })
    }

    // 5. NAVEGACIÓN (Sin cambios)
    private fun lanzarActivityLiga(liga: Liga){
        val intent = Intent(requireContext(), LigaActivity::class.java)
        intent.putExtra("LIGA_ID", liga.id)
        intent.putExtra("LIGA_NOMBRE", liga.nombre)
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
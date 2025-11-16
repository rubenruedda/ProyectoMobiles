package view.fragment

import android.content.Intent
import view.adapter.PartidoAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aplicacion_infosport.databinding.FragmentInicioBinding
import model.Liga
import model.Partido
import view.Info_Partido
import view.adapter.LigaAdapter
import view.Liga as LigaActivity
import viewModel.InicioViewModel

class InicioFragment : Fragment() {

    private var _binding: FragmentInicioBinding? = null
    private val binding get() = _binding!!

    private val viewModel: InicioViewModel by viewModels()

    private lateinit var partidoAdapter: PartidoAdapter
    private lateinit var ligaAdapter: LigaAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInicioBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRvPartidos()
        setupRvLigas()
        setupSearchView() // Conectamos el SearchView

        // Observamos los partidos filtrados
        viewModel.partidosFiltrados.observe(viewLifecycleOwner) { partidos ->
            partidoAdapter.submitList(partidos)
        }

        viewModel.ligasPrincipales.observe(viewLifecycleOwner) { ligas ->
            ligaAdapter.submitList(ligas)
        }
    }

    private fun setupRvPartidos() {
        partidoAdapter = PartidoAdapter { partido ->
            lanzarActivityInfoPartido(partido)
        }
        binding.rvPartidosHoy.adapter = partidoAdapter
    }

    private fun setupRvLigas() {
        ligaAdapter = LigaAdapter { liga ->
            lanzarActivityLiga(liga)
            binding.tvTituloPartidos.text = "Partidos de ${liga.nombre}"
        }
        binding.rvLigasPrincipales.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvLigasPrincipales.adapter = ligaAdapter
    }

    private fun setupSearchView() {
        binding.searchViewInicio.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.searchViewInicio.clearFocus()
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.setSearchQuery(newText.orEmpty())
                return true
            }
        })
    }

    private fun lanzarActivityInfoPartido(partido: Partido) {
        val intent = Intent(requireContext(), Info_Partido::class.java)
        // Añadir datos "Extra" [cite: 321, 322]
        intent.putExtra("PARTIDO_ID", partido.id)
        startActivity(intent)
    }

    private fun lanzarActivityLiga(liga: Liga) {
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
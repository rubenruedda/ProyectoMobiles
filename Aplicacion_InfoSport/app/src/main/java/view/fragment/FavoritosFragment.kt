package view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.aplicacion_infosport.databinding.FragmentFavoritosBinding
import model.Liga
import view.Liga as LigaActivity
import view.adapter.EquipoSimpleAdapter
import view.adapter.LigaSimpleAdapter
import viewModel.FavoritoViewModel

class FavoritosFragment : Fragment() {

    private var _binding: FragmentFavoritosBinding? = null
    private val binding get() = _binding!!

    // 1. Obtener el ViewModel
    private val viewModel: FavoritoViewModel by viewModels()

    // 2. Declarar los dos adaptadores
    private lateinit var ligaAdapter: LigaSimpleAdapter
    private lateinit var equipoAdapter: EquipoSimpleAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 3. Configurar ambos RecyclerViews
        setupRvLigas()
        setupRvEquipos()

        // 4. Observar los LiveData
        viewModel.ligasFavoritas.observe(viewLifecycleOwner) { ligas ->
            ligaAdapter.submitList(ligas)
        }

        viewModel.equiposFavoritos.observe(viewLifecycleOwner) { equipos ->
            equipoAdapter.submitList(equipos)
        }
    }

    private fun setupRvLigas() {
        // Reutilizamos el LigaSimpleAdapter
        ligaAdapter = LigaSimpleAdapter { liga ->
            lanzarActivityLiga(liga)
        }
        binding.rvLigasFavoritas.adapter = ligaAdapter
    }

    private fun setupRvEquipos() {
        equipoAdapter = EquipoSimpleAdapter { equipo ->
            // Al hacer clic en un equipo, vamos a la página de su liga
            lanzarActivityLiga(equipo.ligaId.toInt(), equipo.nombre) // Asumimos que ligaId es Int
        }
        binding.rvEquiposFavoritos.adapter = equipoAdapter
    }

    // 5. NAVEGACIÓN CON INTENT EXPLÍCITO
    private fun lanzarActivityLiga(liga: Liga) {
        val intent = Intent(requireContext(), LigaActivity::class.java)
        intent.putExtra("LIGA_ID", liga.id)
        intent.putExtra("LIGA_NOMBRE", liga.nombre)
        startActivity(intent)
    }

    // Sobrecarga para navegar desde un equipo
    private fun lanzarActivityLiga(ligaId: Int, nombreEquipo: String) {
        val intent = Intent(requireContext(), LigaActivity::class.java)
        intent.putExtra("LIGA_ID", ligaId)
        // Pasamos el nombre del equipo para el título, ya que no tenemos el nombre de la liga
        intent.putExtra("LIGA_NOMBRE", "Liga de $nombreEquipo")
        startActivity(intent)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
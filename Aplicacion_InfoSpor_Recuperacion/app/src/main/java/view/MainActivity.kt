package view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aplicacion_infosport.R
import com.example.aplicacion_infosport.databinding.ActivityMainBinding
import view.adapter.LigaAdapter
import view.adapter.PartidoAdapter
import viewModel.InicioViewModel

class MainActivity : BaseActivity() { // Hereda de BaseActivity

    private lateinit var binding: ActivityMainBinding
    private val viewModel: InicioViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = getString(R.string.menu_inicio)

        // Navegación (Usando la función de BaseActivity)
        setupBottomNavigation(binding.bottomNavigation, R.id.nav_inicio)

        // 1. RecyclerView Ligas (Horizontal)
        // Usamos LigaAdapter como pediste
        val ligaAdapter = LigaAdapter { liga ->
            val intent = Intent(this, LigaActivity::class.java)
            intent.putExtra("LIGA_ID", liga.id)
            intent.putExtra("LIGA_NOMBRE", liga.nombre)
            startActivity(intent)
        }
        binding.rvLigas.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvLigas.adapter = ligaAdapter

        // 2. RecyclerView Partidos (Vertical)
        // CORRECCIÓN: Quitamos 'emptyList()'. El constructor solo pide la lambda.
        val partidoAdapter = PartidoAdapter { partido ->
            val intent = Intent(this, InfoPartidoActivity::class.java)
            intent.putExtra("PARTIDO_ID", partido.id)
            startActivity(intent)
        }
        binding.rvPartidos.layoutManager = LinearLayoutManager(this)
        binding.rvPartidos.adapter = partidoAdapter

        // Observadores
        viewModel.ligasPrincipales.observe(this) { ligas ->
            ligaAdapter.submitList(ligas)
        }

        viewModel.partidosFiltrados.observe(this) { partidos ->
            // CORRECCIÓN: Usamos submitList en lugar de actualizarDatos
            partidoAdapter.submitList(partidos)

            if (partidos.isEmpty()) {
                Toast.makeText(this, getString(R.string.sin_datos), Toast.LENGTH_SHORT).show()
            }
        }

        // Búsqueda
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.setSearchQuery(newText.orEmpty())
                return true
            }
        })
    }
}
package view

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aplicacion_infosport.R
import com.example.aplicacion_infosport.databinding.ActivityMainBinding
import utils.PreCargaDatos
import view.adapter.LigaAdapter
import view.adapter.NoticiaAdapter
import view.adapter.PartidoAdapter
import viewModel.*

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private val IviewModel: InicioViewModel by viewModels()
    private val PviewModel: InfoPartidoViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            displayOptions = androidx.appcompat.app.ActionBar.DISPLAY_SHOW_CUSTOM
            setCustomView(R.layout.titulo_centrado)
            val tituloPersonalizado = customView.findViewById<TextView>(R.id.action_bar_title)
            tituloPersonalizado.text = getString(R.string.menu_inicio)
        }
        setupBottomNavigation(binding.bottomNavigation, R.id.nav_inicio)
        val precarga = PreCargaDatos(this)
        precarga.cargarDatos()
        val ligaAdapter = LigaAdapter (
            onItemSelected = { liga ->
                // Clic normal: Ir a detalle
                val intent = Intent(this, LigaActivity::class.java)
                intent.putExtra("LIGA_ID", liga.id)
                intent.putExtra("LIGA_NOMBRE", liga.nombre)
                startActivity(intent)
            },
            onLigaClick = { liga ->
                // Clic Favorito: Guardar en BD
                IviewModel.actualizarFavoritoLiga(liga)
                Toast.makeText(this, "Favorito actualizado", Toast.LENGTH_SHORT).show()
            }
        )
        binding.rvLigas.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvLigas.adapter = ligaAdapter
        val partidoAdapter = PartidoAdapter(
            onItemSelected = { partido ->
                val intent = Intent(this, InfoPartidoActivity::class.java)
                intent.putExtra("PARTIDO_ID", partido.id)
                startActivity(intent)
            },
            onPartidoClick = { partido ->
                IviewModel.actualizarFavoritoPartido(partido)
            }
        )
        binding.rvPartidos.layoutManager = LinearLayoutManager(this)
        binding.rvPartidos.adapter = partidoAdapter

        IviewModel.ligasPrincipales.observe(this) { ligas ->
            ligaAdapter.submitList(ligas)
        }
        IviewModel.partidosFiltrados.observe(this) { partidos ->
            partidoAdapter.submitList(partidos)

            if (partidos.isEmpty()) {
                Toast.makeText(this, getString(R.string.sin_datos), Toast.LENGTH_SHORT).show()
            }
        }
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                IviewModel.setSearchQuery(newText.orEmpty())
                return true
            }
        })
    }
}
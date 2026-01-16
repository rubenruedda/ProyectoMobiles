package view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aplicacion_infosport.R
import com.example.aplicacion_infosport.databinding.ActivityMainBinding
import view.adapter.LigaAdapter
import view.adapter.PartidoAdapter
import viewModel.InicioViewModel

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: InicioViewModel by viewModels()
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: Iniciando MainActivity")
        
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
        
        // Configurar adaptador de ligas
        val ligaAdapter = LigaAdapter { liga ->
            Log.d(TAG, "onLigaClick: Liga seleccionada - ${liga.nombre}")
            val intent = Intent(this, LigaActivity::class.java)
            intent.putExtra("LIGA_ID", liga.id)
            intent.putExtra("LIGA_NOMBRE", liga.nombre)
            startActivity(intent)
            Toast.makeText(this, "Abriendo ${liga.nombre}", Toast.LENGTH_SHORT).show()
        }
        binding.rvLigas.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvLigas.adapter = ligaAdapter
        
        // Configurar adaptador de partidos
        val partidoAdapter = PartidoAdapter { partido ->
            Log.d(TAG, "onPartidoClick: Partido seleccionado - ID ${partido.id}")
            val intent = Intent(this, InfoPartidoActivity::class.java)
            intent.putExtra("PARTIDO_ID", partido.id)
            startActivity(intent)
        }
        binding.rvPartidos.layoutManager = LinearLayoutManager(this)
        binding.rvPartidos.adapter = partidoAdapter
        
        // Observar ligas principales
        viewModel.ligasPrincipales.observe(this) { ligas ->
            Log.d(TAG, "Ligas cargadas: ${ligas.size}")
            if (ligas.isEmpty()) {
                Log.w(TAG, "No se encontraron ligas en la base de datos")
                Toast.makeText(this, getString(R.string.error_cargar_datos), Toast.LENGTH_SHORT).show()
            }
            ligaAdapter.submitList(ligas)
        }
        
        // Observar partidos filtrados
        viewModel.partidosFiltrados.observe(this) { partidos ->
            Log.d(TAG, "Partidos filtrados: ${partidos.size}")
            partidoAdapter.submitList(partidos)

            if (partidos.isEmpty()) {
                Log.i(TAG, "No hay partidos para mostrar hoy")
                Toast.makeText(this, getString(R.string.sin_datos), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "${partidos.size} ${getString(R.string.partidos_hoy)}", Toast.LENGTH_SHORT).show()
            }
        }
        
        // Configurar búsqueda
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.d(TAG, "Búsqueda enviada: $query")
                binding.searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Log.d(TAG, "Texto de búsqueda cambiado: $newText")
                viewModel.setSearchQuery(newText.orEmpty())
                return true
            }
        })
        
        Log.d(TAG, "onCreate: MainActivity inicializada correctamente")
        Toast.makeText(this, getString(R.string.welcome_message), Toast.LENGTH_SHORT).show()
    }
    
    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: MainActivity visible")
    }
    
    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: MainActivity en pausa")
    }
}
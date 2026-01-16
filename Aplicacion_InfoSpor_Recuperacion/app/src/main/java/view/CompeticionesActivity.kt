package view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aplicacion_infosport.R
import com.example.aplicacion_infosport.databinding.ActivityCompeticionesBinding
import view.adapter.CompeticionesAdapter
import viewModel.CompeticionesViewModel

class CompeticionesActivity : BaseActivity() {

    private lateinit var binding: ActivityCompeticionesBinding
    private val viewModel: CompeticionesViewModel by viewModels()
    private val TAG = "CompeticionesActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: Iniciando CompeticionesActivity")
        
        binding = ActivityCompeticionesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.title = getString(R.string.menu_competiciones)
        setSupportActionBar(binding.toolbar)

        setupBottomNavigation(binding.bottomNavigation, R.id.nav_competiciones)

        val adapter = CompeticionesAdapter { liga ->
            Log.d(TAG, "Liga seleccionada: ${liga.nombre}")
            val intent = Intent(this, LigaActivity::class.java)
            intent.putExtra("LIGA_ID", liga.id)
            intent.putExtra("LIGA_NOMBRE", liga.nombre)
            startActivity(intent)
            Toast.makeText(this, "Abriendo ${liga.nombre}", Toast.LENGTH_SHORT).show()
        }
        binding.rvCompeticiones.layoutManager = LinearLayoutManager(this)
        binding.rvCompeticiones.adapter = adapter

        viewModel.listaAgrupada.observe(this) { ligas ->
            Log.d(TAG, "Ligas agrupadas cargadas")
            if (ligas.isEmpty()) {
                Log.w(TAG, "No se encontraron ligas")
                Toast.makeText(this, getString(R.string.sin_datos), Toast.LENGTH_SHORT).show()
            }
            adapter.submitList(ligas)
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?) = false
            override fun onQueryTextChange(newText: String?): Boolean {
                Log.d(TAG, "Búsqueda: $newText")
                viewModel.setSearchQuery(newText.orEmpty())
                return true
            }
        })
        
        Log.d(TAG, "onCreate: CompeticionesActivity inicializada")
    }
    
    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: CompeticionesActivity visible")
    }
}
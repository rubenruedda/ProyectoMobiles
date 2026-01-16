package view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aplicacion_infosport.R
import com.example.aplicacion_infosport.databinding.ActivityFavoritosBinding
import view.adapter.LigaAdapter
import viewModel.FavoritoViewModel

class FavoritosActivity : BaseActivity() {

    private lateinit var binding: ActivityFavoritosBinding
    private val viewModel: FavoritoViewModel by viewModels()
    private lateinit var adapter: LigaAdapter
    private val TAG = "FavoritosActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: Iniciando FavoritosActivity")
        
        binding = ActivityFavoritosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.title = getString(R.string.menu_favoritos)
        setSupportActionBar(binding.toolbar)

        setupBottomNavigation(binding.bottomNavigation, R.id.nav_favoritos)

        adapter = LigaAdapter { liga ->
            Log.d(TAG, "Liga favorita seleccionada: ${liga.nombre}")
            val intent = Intent(this, LigaActivity::class.java)
            intent.putExtra("LIGA_ID", liga.id)
            intent.putExtra("LIGA_NOMBRE", liga.nombre)
            startActivity(intent)
        }

        binding.rvFavoritos.layoutManager = LinearLayoutManager(this)
        binding.rvFavoritos.adapter = adapter

        viewModel.ligasFavoritas.observe(this) { ligas ->
            Log.d(TAG, "Favoritos cargados: ${ligas.size}")
            if (ligas.isEmpty()) {
                Log.i(TAG, "No hay favoritos guardados")
                Toast.makeText(this, getString(R.string.no_favoritos), Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "${ligas.size} favoritos guardados", Toast.LENGTH_SHORT).show()
            }
            adapter.submitList(ligas)
        }
        
        Log.d(TAG, "onCreate: FavoritosActivity inicializada")
    }
    
    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: FavoritosActivity visible")
    }
}
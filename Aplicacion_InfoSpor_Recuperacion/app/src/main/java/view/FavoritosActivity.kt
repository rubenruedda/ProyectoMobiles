package view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aplicacion_infosport.R
import com.example.aplicacion_infosport.databinding.ActivityFavoritosBinding
import view.adapter.LigaAdapter
import view.adapter.NoticiaFavoritaAdapter
import view.adapter.PartidoAdapter
import viewModel.FavoritoViewModel

class FavoritosActivity : BaseActivity() {

    private lateinit var binding: ActivityFavoritosBinding
    private val viewModel: FavoritoViewModel by viewModels()
    private lateinit var ligaAdapter: LigaAdapter
    private lateinit var partidoAdapter: PartidoAdapter
    private lateinit var noticiaAdapter: NoticiaFavoritaAdapter
    private val TAG = "FavoritosActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: Iniciando FavoritosActivity")
        
        binding = ActivityFavoritosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.title = getString(R.string.menu_favoritos)
        setSupportActionBar(binding.toolbar)

        setupBottomNavigation(binding.bottomNavigation, R.id.nav_favoritos)

        setupAdapters()
        observeData()
        
        Log.d(TAG, "onCreate: FavoritosActivity inicializada")
    }
    
    private fun setupAdapters() {
        // Adapter para ligas
        ligaAdapter = LigaAdapter { liga ->
            Log.d(TAG, "Liga favorita seleccionada: ${liga.nombre}")
            val intent = Intent(this, LigaActivity::class.java)
            intent.putExtra("LIGA_ID", liga.id)
            intent.putExtra("LIGA_NOMBRE", liga.nombre)
            startActivity(intent)
        }
        binding.rvLigasFavoritas.layoutManager = LinearLayoutManager(this)
        binding.rvLigasFavoritas.adapter = ligaAdapter

        // Adapter para partidos
        partidoAdapter = PartidoAdapter { partido ->
            Log.d(TAG, "Partido favorito seleccionado: ${partido.id}")
            val intent = Intent(this, InfoPartidoActivity::class.java)
            intent.putExtra("PARTIDO_ID", partido.id)
            startActivity(intent)
        }
        binding.rvPartidosFavoritos.layoutManager = LinearLayoutManager(this)
        binding.rvPartidosFavoritos.adapter = partidoAdapter

        // Adapter para noticias (con botón de favorito)
        noticiaAdapter = NoticiaFavoritaAdapter(
            onNoticiaClick = { noticia ->
                Log.d(TAG, "Noticia favorita seleccionada: ${noticia.id}")
                val intent = Intent(this, NoticiaExpandidaActivity::class.java)
                intent.putExtra("NOTICIA", noticia)
                startActivity(intent)
            },
            onFavoritoClick = { noticia ->
                viewModel.toggleNoticiaFavorita(noticia)
                Toast.makeText(this, 
                    if (noticia.esFavorita) getString(R.string.eliminado_favoritos) 
                    else getString(R.string.agregado_favoritos), 
                    Toast.LENGTH_SHORT).show()
            }
        )
        binding.rvNoticiasFavoritas.layoutManager = LinearLayoutManager(this)
        binding.rvNoticiasFavoritas.adapter = noticiaAdapter
    }
    
    private fun observeData() {
        // Observar ligas favoritas
        viewModel.ligasFavoritas.observe(this) { ligas ->
            Log.d(TAG, "Ligas favoritas: ${ligas.size}")
            if (ligas.isEmpty()) {
                binding.tvNoLigas.visibility = View.VISIBLE
                binding.rvLigasFavoritas.visibility = View.GONE
            } else {
                binding.tvNoLigas.visibility = View.GONE
                binding.rvLigasFavoritas.visibility = View.VISIBLE
                ligaAdapter.submitList(ligas)
            }
        }

        // Observar partidos favoritos
        viewModel.partidosFavoritos.observe(this) { partidos ->
            Log.d(TAG, "Partidos favoritos: ${partidos.size}")
            if (partidos.isEmpty()) {
                binding.tvNoPartidos.visibility = View.VISIBLE
                binding.rvPartidosFavoritos.visibility = View.GONE
            } else {
                binding.tvNoPartidos.visibility = View.GONE
                binding.rvPartidosFavoritos.visibility = View.VISIBLE
                partidoAdapter.submitList(partidos)
            }
        }

        // Observar noticias favoritas
        viewModel.noticiasFavoritas.observe(this) { noticias ->
            Log.d(TAG, "Noticias favoritas: ${noticias.size}")
            if (noticias.isEmpty()) {
                binding.tvNoNoticias.visibility = View.VISIBLE
                binding.rvNoticiasFavoritas.visibility = View.GONE
            } else {
                binding.tvNoNoticias.visibility = View.GONE
                binding.rvNoticiasFavoritas.visibility = View.VISIBLE
                noticiaAdapter.submitList(noticias)
            }
        }
    }
    
    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: FavoritosActivity visible")
    }
}
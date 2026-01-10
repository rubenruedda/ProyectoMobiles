package view

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aplicacion_infosport.R
import com.example.aplicacion_infosport.databinding.ActivityFavoritosBinding
import view.adapter.*
import viewModel.FavoritoViewModel


class FavoritosActivity : BaseActivity() { // Hereda de BaseActivity

    private lateinit var binding: ActivityFavoritosBinding
    private val viewModel: FavoritoViewModel by viewModels()
    private lateinit var adapterLiga: LigaAdapter
    private lateinit var adapterNoticia: NoticiaAdapter
    private lateinit var adapterPartido: PartidoAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoritosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.title = getString(R.string.menu_favoritos)
        setSupportActionBar(binding.toolbar)

        setupBottomNavigation(binding.bottomNavigation, R.id.nav_favoritos)

        // CORRECCIÓN AQUÍ: No pasamos lista, solo el listener
        adapterLiga = LigaAdapter { liga ->
            val intent = Intent(this, LigaActivity::class.java)
            intent.putExtra("LIGA_ID", liga.id)
            intent.putExtra("LIGA_NOMBRE", liga.nombre)
            startActivity(intent)
        }

        adapterNoticia = NoticiaAdapter { noticia ->
            val intent = Intent(this, NoticiaExpandidaActivity::class.java)
            intent.putExtra("NOTICIA_ID", noticia.id)
            startActivity(intent)
        }

        adapterPartido = PartidoAdapter { partido ->
            val intent = Intent(this, InfoPartidoActivity::class.java)
            intent.putExtra("PARTIDO_ID", partido.id)
            startActivity(intent)
        }

        binding.rvFavoritos.layoutManager = LinearLayoutManager(this)
        binding.rvFavoritos.adapter = adapter

        viewModel.ligasFavoritas.observe(this) { ligas ->
            adapterLiga.submitList(ligas) // Usamos submitList
        }

        viewModel.noticiasFavoritas.observe(this) { noticias ->
            adapterNoticia.submitList(noticias) // Usamos submitList
        }

        viewModel.partidosFavoritos.observe(this) { partidos ->
            adapterPartido.submitList(partidos) // Usamos submitList
        }
    }
}
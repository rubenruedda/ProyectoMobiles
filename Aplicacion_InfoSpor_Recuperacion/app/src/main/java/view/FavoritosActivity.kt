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


class FavoritosActivity : BaseActivity() {

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
        val ligaAdapter = LigaAdapter(
            onItemSelected = { liga ->
                // Clic normal: Ir al detalle
                val intent = Intent(this, LigaActivity::class.java)
                intent.putExtra("LIGA_ID", liga.id)
                intent.putExtra("LIGA_NOMBRE", liga.nombre)
                startActivity(intent)
            },
            onLigaClick = { liga ->
                // Clic Corazón: Quitar de favoritos
                viewModel.actualizarLiga(liga)
                // No hace falta hacer nada más, la lista se actualizará sola al desaparecer el ítem
            }
        )

        val noticiaAdapter = NoticiaAdapter(
            onItemSelected = { noticia ->
                val intent = Intent(this, NoticiaExpandidaActivity::class.java)
                intent.putExtra("NOTICIA_ID", noticia.id)
                startActivity(intent)
            },
            onNoticiaClick = { noticia ->
                viewModel.actualizarNoticia(noticia)
            }
        )

        val partidoAdapter = PartidoAdapter(
            onItemSelected = { partido ->
                val intent = Intent(this, InfoPartidoActivity::class.java)
                intent.putExtra("PARTIDO_ID", partido.id)
                startActivity(intent)
            },
            onPartidoClick = { partido ->
                viewModel.actualizarPartido(partido)
            }
        )

        binding.rvFavLigas.layoutManager = LinearLayoutManager(this)
        binding.rvFavLigas.adapter = ligaAdapter
        binding.rvFavNoticias.layoutManager = LinearLayoutManager(this)
        binding.rvFavNoticias.adapter = noticiaAdapter
        binding.rvFavPartidos.layoutManager = LinearLayoutManager(this)
        binding.rvFavPartidos.adapter = partidoAdapter

        viewModel.ligasFavoritas.observe(this) { ligas ->
            // Solo actualizamos si el adaptador ya existe
            if (::adapterLiga.isInitialized) {
                adapterLiga.submitList(ligas)
            }
        }

        viewModel.partidosFavoritos.observe(this) { partidos ->
            if (::adapterPartido.isInitialized) {
                adapterPartido.submitList(partidos)
            }
        }

        viewModel.noticiasFavoritas.observe(this) { noticias ->
            if (::adapterNoticia.isInitialized) {
                adapterNoticia.submitList(noticias)
            }
        }
    }
}
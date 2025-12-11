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
import view.adapter.LigaAdapter
import viewModel.FavoritoViewModel


class FavoritosActivity : BaseActivity() {

    private lateinit var binding: ActivityFavoritosBinding
    private val viewModel: FavoritoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoritosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.title = getString(R.string.menu_favoritos)
        setSupportActionBar(binding.toolbar)

        setupBottomNavigation(binding.bottomNav, R.id.nav_favoritos)

        val adapter = LigaAdapter(emptyList()) { liga ->
            val intent = Intent(this, LigaActivity::class.java)
            intent.putExtra("LIGA_ID", liga.id)
            intent.putExtra("LIGA_NOMBRE", liga.nombre)
            startActivity(intent)
        }
        binding.rvFavoritos.layoutManager = LinearLayoutManager(this)
        binding.rvFavoritos.adapter = adapter

        viewModel.ligasFavoritas.observe(this) { adapter.actualizarDatos(it) }
    }
}
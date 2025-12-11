package view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aplicacion_infosport.R
import com.example.aplicacion_infosport.databinding.ActivityMainBinding
import model.Liga
import view.adapter.LigaAdapter
import view.adapter.PartidoAdapter
import viewModel.InicioViewModel


class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: InicioViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Toolbar
        binding.toolbar.title = getString(R.string.menu_inicio)
        setSupportActionBar(binding.toolbar)

        // Navegación
        setupBottomNavigation(binding.bottomNavigation, R.id.nav_inicio)

        // RecyclerViews
        val ligaAdapter = LigaAdapter { liga ->
            val intent = Intent(this, LigaActivity::class.java)
            intent.putExtra("LIGA_ID", liga.id)
            intent.putExtra("LIGA_NOMBRE", liga.nombre)
            startActivity(intent)
        }
        binding.rvLigas.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvLigas.adapter = ligaAdapter

        val partidoAdapter = PartidoAdapter(emptyList()) { partido ->
            val intent = Intent(this, InfoPartidoActivity::class.java)
            intent.putExtra("PARTIDO_ID", partido.id)
            startActivity(intent)
        }
        binding.rvPartidos.layoutManager = LinearLayoutManager(this)
        binding.rvPartidos.adapter = partidoAdapter

        // Observadores
        viewModel.ligasPrincipales.observe(this) {
            ligaAdapter.submitList(it)
        }
        viewModel.partidosFiltrados.observe(this) {
            partidoAdapter.actualizarDatos(it)
            if (it.isEmpty()) Toast.makeText(this, R.string.sin_datos, Toast.LENGTH_SHORT).show()
        }

        // Búsqueda
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?) = false
            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.setSearchQuery(newText.orEmpty())
                return true
            }
        })
    }
}

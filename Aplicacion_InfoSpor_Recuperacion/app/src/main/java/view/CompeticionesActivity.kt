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
import com.example.aplicacion_infosport.databinding.ActivityCompeticionesBinding
import view.adapter.LigaAdapter
import viewModel.CompeticionesViewModel
import viewModel.InicioViewModel

class CompeticionesActivity : BaseActivity() {

    private lateinit var binding: ActivityCompeticionesBinding
    private val viewModel: CompeticionesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCompeticionesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.title = getString(R.string.menu_competiciones)
        setSupportActionBar(binding.toolbar)

        setupBottomNavigation(binding.bottomNavigation, R.id.nav_competiciones)

        val adapter = CompeticionAdapter { liga ->
            val intent = Intent(this, LigaActivity::class.java)
            intent.putExtra("LIGA_ID", liga.id)
            intent.putExtra("LIGA_NOMBRE", liga.nombre)
            startActivity(intent)
        }
        binding.rvCompeticiones.layoutManager = LinearLayoutManager(this)
        binding.rvCompeticiones.adapter = adapter

        viewModel.listaAgrupada.observe(this) { adapter.submitList(it) }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?) = false
            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.setSearchQuery(newText.orEmpty())
                return true
            }
        })
    }
}
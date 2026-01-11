package view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aplicacion_infosport.R
import com.example.aplicacion_infosport.databinding.ActivityNoticiasBinding
import view.adapter.NoticiaAdapter
import viewModel.NoticiaViewModel

class NoticiasActivity : BaseActivity() {

    private lateinit var binding: ActivityNoticiasBinding
    private val viewModel: NoticiaViewModel by viewModels()
    private lateinit var adapter: NoticiaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoticiasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Configurar Toolbar
        binding.toolbar.title = getString(R.string.menu_noticias)
        setSupportActionBar(binding.toolbar)

        // 2. Configurar Navegación (BaseActivity)
        setupBottomNavigation(binding.bottomNavigation, R.id.nav_noticias)

        // 3. Configurar RecyclerView y Adapter
        // CAMBIO PRINCIPAL: Quitamos emptyList() del constructor
        adapter = NoticiaAdapter { noticia ->
            val intent = Intent(this, NoticiaExpandidaActivity::class.java)
            intent.putExtra("NOTICIA_ID", noticia.id)
            startActivity(intent)
        }

        binding.rvNoticias.layoutManager = LinearLayoutManager(this)
        binding.rvNoticias.adapter = adapter

        // 4. Observar datos del ViewModel
        // Asegúrate de que tu ViewModel exponga 'noticias' o 'todasLasNoticias' (ajusta el nombre si es necesario)
        viewModel.noticias.observe(this) { listaNoticias ->
            // CAMBIO PRINCIPAL: Usamos submitList en lugar de actualizarDatos
            adapter.submitList(listaNoticias)
        }

        // 5. Configurar Buscador
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.setSearchQuery(newText.orEmpty())
                return true
            }
        })
    }
}
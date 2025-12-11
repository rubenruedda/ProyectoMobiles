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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoticiasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.title = getString(R.string.menu_noticias)
        setSupportActionBar(binding.toolbar)

        setupBottomNavigation(binding.bottomNavigation, R.id.nav_noticias)

        val adapter = NoticiaAdapter(emptyList()) { noticia ->
            val intent = Intent(this, NoticiaExpandidaActivity::class.java)
            intent.putExtra("NOTICIA_ID", noticia.id)
            startActivity(intent)
        }
        binding.rvNoticias.layoutManager = LinearLayoutManager(this)
        binding.rvNoticias.adapter = adapter

        viewModel.noticias.observe(this) { adapter.actualizarDatos(it) }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?) = false
            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.setSearchQuery(newText.orEmpty())
                return true
            }
        })
    }
}
package view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
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
    private val TAG = "NoticiasActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: Iniciando NoticiasActivity")
        
        binding = ActivityNoticiasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.title = getString(R.string.menu_noticias)
        setSupportActionBar(binding.toolbar)

        setupBottomNavigation(binding.bottomNavigation, R.id.nav_noticias)

        adapter = NoticiaAdapter(
            listaNoticias = emptyList(),
            onNoticiaClick = { noticia ->
                Log.d(TAG, "Noticia seleccionada: ${noticia.titulo}")
                val intent = Intent(this, NoticiaExpandidaActivity::class.java)
                intent.putExtra("NOTICIA_ID", noticia.id)
                startActivity(intent)
            },
            onFavoritoClick = { noticia ->
                viewModel.toggleNoticiaFavorita(noticia)
                val mensaje = if (noticia.esFavorita) 
                    getString(R.string.eliminado_favoritos) 
                else 
                    getString(R.string.agregado_favoritos)
                Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
            }
        )
        binding.rvNoticias.layoutManager = LinearLayoutManager(this)
        binding.rvNoticias.adapter = adapter

        viewModel.noticias.observe(this) { noticias ->
            Log.d(TAG, "Noticias cargadas: ${noticias.size}")
            if (noticias.isEmpty()) {
                Log.w(TAG, "No se encontraron noticias")
                Toast.makeText(this, getString(R.string.sin_datos), Toast.LENGTH_SHORT).show()
            }
            adapter.actualizarDatos(noticias)
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?) = false
            override fun onQueryTextChange(newText: String?): Boolean {
                Log.d(TAG, "Búsqueda: $newText")
                viewModel.setSearchQuery(newText.orEmpty())
                return true
            }
        })
        
        Log.d(TAG, "onCreate: NoticiasActivity inicializada")
    }
    
    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: NoticiasActivity visible")
    }
}
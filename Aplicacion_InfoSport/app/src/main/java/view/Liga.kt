package view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.aplicacion_infosport.R
import com.example.aplicacion_infosport.databinding.ActivityLigaBinding
import com.google.android.material.tabs.TabLayoutMediator
import view.adapter.LigaViewPagerAdapter
import viewModel.LigaViewModel

class Liga : AppCompatActivity() {

    lateinit var binding: ActivityLigaBinding
    private val viewModel: LigaViewModel by viewModels()
    private lateinit var viewPagerAdapter: LigaViewPagerAdapter
    private var menuFavorito: MenuItem? = null // Para guardar la referencia al ítem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLigaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val ligaId = intent.getStringExtra("LIGA_ID")
        val ligaNombre = intent.getStringExtra("LIGA_NOMBRE") ?: "Competición"

        if (ligaId == null) {
            finish()
            return
        }

        // Configurar la Toolbar (ahora soporta menú)
        setupToolbar(ligaNombre)

        // Informar al ViewModel
        viewModel.setLigaId(ligaId)

        // Configurar ViewPager
        setupViewPager()

        // Observar el estado de favorito
        observarLiga()
    }

    private fun setupToolbar(titulo: String) {
        binding.toolbarLiga.title = titulo
        setSupportActionBar(binding.toolbarLiga) // Necesario para inflar el menú
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    private fun setupViewPager() {
        viewPagerAdapter = LigaViewPagerAdapter(this)
        binding.viewPagerLiga.adapter = viewPagerAdapter

        TabLayoutMediator(binding.tabLayoutLiga, binding.viewPagerLiga) { tab, position ->
            tab.text = viewPagerAdapter.titulos[position]
        }.attach()
    }

    // --- NUEVA FUNCIÓN ---
    private fun observarLiga() {
        viewModel.liga.observe(this) { liga ->
            if (liga != null) {
                // Actualiza el estado del icono del menú
                menuFavorito?.isChecked = liga.esFavorita
            }
        }
    }

    // --- CÓDIGO NUEVO PARA EL MENÚ ---
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.liga_menu, menu)
        menuFavorito = menu.findItem(R.id.menu_toggle_favorito)

        // Si la liga ya se cargó, actualiza el icono
        viewModel.liga.value?.let {
            menuFavorito?.isChecked = it.esFavorita
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish() // Botón "Atrás"
                true
            }
            R.id.menu_toggle_favorito -> {
                // Llama al ViewModel para cambiar el estado
                viewModel.toggleFavorito()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
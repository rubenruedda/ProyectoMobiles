package view

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aplicacion_infosport.R
import com.example.aplicacion_infosport.databinding.ActivityLigaBinding
import view.adapter.PartidoAdapter

class LigaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLigaBinding
    private val viewModel: LigaViewModel by viewModels()
    private lateinit var partidosAdapter: PartidoAdapter
    private lateinit var clasificacionAdapter: ClasificacionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLigaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val ligaId = intent.getStringExtra("LIGA_ID") ?: return
        val ligaNombre = intent.getStringExtra("LIGA_NOMBRE")

        binding.toolbar.title = ligaNombre
        binding.toolbar.setNavigationOnClickListener { finish() }

        // Menú de favoritos en la toolbar
        binding.toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_toggle_favorito -> {
                    viewModel.toggleFavorito()
                    true
                }
                else -> false
            }
        }

        viewModel.setLigaId(ligaId)

        // Configurar Recycler de Partidos (Resultados)
        partidosAdapter = PartidoAdapter { /* Click en partido */ } // Asegúrate de que PartidoAdapter también sea ListAdapter
        binding.rvPartidosLiga.layoutManager = LinearLayoutManager(this)
        binding.rvPartidosLiga.adapter = partidosAdapter

        // Configurar Recycler de Clasificación
        clasificacionAdapter = ClasificacionAdapter()
        binding.rvClasificacion.layoutManager = LinearLayoutManager(this)
        binding.rvClasificacion.adapter = clasificacionAdapter

        // Lógica de botones "Pestañas"
        binding.btnResultados.setOnClickListener {
            binding.rvPartidosLiga.visibility = View.VISIBLE
            binding.rvClasificacion.visibility = View.GONE
        }
        binding.btnClasificacion.setOnClickListener {
            binding.rvPartidosLiga.visibility = View.GONE
            binding.rvClasificacion.visibility = View.VISIBLE
        }

        // Observar datos
        viewModel.resultados.observe(this) { partidos ->
            // Si PartidoAdapter es ListAdapter usa submitList, si no, actualiza lista interna
            // partidosAdapter.submitList(partidos)
            // Si usas el adapter antiguo con lista manual:
            partidosAdapter.actualizarDatos(partidos)
        }

        viewModel.clasificacion.observe(this) { clasificacion ->
            clasificacionAdapter.submitList(clasificacion)
        }

        viewModel.liga.observe(this) { liga ->
            // Actualizar icono de favorito
            val icon = if (liga.esFavorita) R.drawable.favorito else R.drawable.favorito_borde
            binding.toolbar.menu.findItem(R.id.menu_toggle_favorito)?.setIcon(icon)
        }
    }
}
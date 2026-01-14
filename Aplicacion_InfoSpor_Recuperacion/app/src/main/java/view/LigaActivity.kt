package view

import android.content.Intent
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
import view.adapter.ClasificacionAdapter
import view.adapter.PartidoAdapter
import viewModel.LigaViewModel

class LigaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLigaBinding
    private val viewModel: LigaViewModel by viewModels()

    // Declaramos los adaptadores
    private lateinit var partidosAdapter: PartidoAdapter
    private lateinit var clasificacionAdapter: ClasificacionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLigaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Recoger datos del Intent
        val ligaId = intent.getStringExtra("LIGA_ID") ?: return
        val ligaNombre = intent.getStringExtra("LIGA_NOMBRE")

        // 2. Configurar Toolbar
        binding.toolbar.title = ligaNombre
        binding.toolbar.setNavigationOnClickListener { finish() }

        // Configurar clic en el menú (Corazón de favorito)
        binding.toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_toggle_favorito -> {
                    viewModel.toggleFavorito(ligaId.toInt(), menuItem.isChecked)
                    true
                }
                else -> false
            }
        }

        // 3. Inicializar ViewModel
        viewModel.setLigaId(ligaId)

        // 4. Configurar RecyclerView de Partidos (Ahora PartidoAdapter es ListAdapter)
        partidosAdapter = PartidoAdapter(
            onItemSelected = { partido ->
                val intent = Intent(this, InfoPartidoActivity::class.java)
                intent.putExtra("PARTIDO_ID", partido.id)
                startActivity(intent)
            },
            onPartidoClick = { partido ->
                viewModel.actualizarFavoritoPartido(partido)
            }
        )
        binding.rvPartidosLiga.layoutManager = LinearLayoutManager(this)
        binding.rvPartidosLiga.adapter = partidosAdapter

        // 5. Configurar RecyclerView de Clasificación
        clasificacionAdapter = ClasificacionAdapter()
        binding.rvClasificacion.layoutManager = LinearLayoutManager(this)
        binding.rvClasificacion.adapter = clasificacionAdapter

        // 6. Lógica de botones (Pestañas manuales)
        binding.btnResultados.setOnClickListener {
            binding.rvPartidosLiga.visibility = View.VISIBLE
            binding.rvClasificacion.visibility = View.GONE
            // Cambiar estilo de botones opcionalmente para indicar selección
        }

        binding.btnClasificacion.setOnClickListener {
            binding.rvPartidosLiga.visibility = View.GONE
            binding.rvClasificacion.visibility = View.VISIBLE
        }

        // 7. Observar datos del ViewModel
        viewModel.resultados.observe(this) { partidos ->
            // Ahora sí funciona submitList porque actualizamos el Adapter
            partidosAdapter.submitList(partidos)
        }

        viewModel.clasificacion.observe(this) { clasificacion ->
            clasificacionAdapter.submitList(clasificacion)
        }

        viewModel.liga.observe(this) { liga ->
            // Cambiar el icono del corazón según estado
            val iconRes = if (liga.esFavorita) R.drawable.favorito else R.drawable.favorito_borde
            binding.toolbar.menu.findItem(R.id.menu_toggle_favorito)?.setIcon(iconRes)
        }
    }
}
package view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aplicacion_infosport.R
import com.example.aplicacion_infosport.databinding.ActivityLigaBinding
import view.adapter.ClasificacionAdapter
import view.adapter.PartidoAdapter
import viewModel.LigaViewModel

class LigaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLigaBinding
    private val viewModel: LigaViewModel by viewModels()
    private val TAG = "LigaActivity"

    // Declaramos los adaptadores
    private lateinit var partidosAdapter: PartidoAdapter
    private lateinit var clasificacionAdapter: ClasificacionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: Iniciando LigaActivity")
        
        binding = ActivityLigaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Recoger datos del Intent
        val ligaId = intent.getStringExtra("LIGA_ID")
        val ligaNombre = intent.getStringExtra("LIGA_NOMBRE")
        
        if (ligaId == null) {
            Log.e(TAG, "Error: ligaId es null")
            Toast.makeText(this, getString(R.string.error_cargar_datos), Toast.LENGTH_SHORT).show()
            finish()
            return
        }
        
        Log.d(TAG, "Liga recibida: ID=$ligaId, Nombre=$ligaNombre")

        // 2. Configurar Toolbar
        binding.toolbar.title = ligaNombre
        binding.toolbar.setNavigationOnClickListener { 
            Log.d(TAG, "Navegación atrás presionada")
            finish() 
        }

        // Configurar clic en el menú (Corazón de favorito)
        binding.toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_toggle_favorito -> {
                    Log.d(TAG, "Toggle favorito presionado")
                    viewModel.toggleFavorito()
                    true
                }
                else -> false
            }
        }

        // 3. Inicializar ViewModel
        viewModel.setLigaId(ligaId)

        // 4. Configurar RecyclerView de Partidos
        partidosAdapter = PartidoAdapter { partido ->
            Log.d(TAG, "Partido seleccionado: ${partido.nombreLocal} vs ${partido.nombreVisitante}")
            // Navegar al detalle del partido
            val intent = Intent(this, InfoPartidoActivity::class.java)
            intent.putExtra("PARTIDO_ID", partido.id)
            startActivity(intent)
        }
        binding.rvPartidosLiga.layoutManager = LinearLayoutManager(this)
        binding.rvPartidosLiga.adapter = partidosAdapter

        // 5. Configurar RecyclerView de Clasificación
        clasificacionAdapter = ClasificacionAdapter()
        binding.rvClasificacion.layoutManager = LinearLayoutManager(this)
        binding.rvClasificacion.adapter = clasificacionAdapter

        // 6. Lógica de botones (Pestañas manuales)
        binding.btnResultados.setOnClickListener {
            Log.d(TAG, "Vista de resultados seleccionada")
            binding.rvPartidosLiga.visibility = View.VISIBLE
            binding.rvClasificacion.visibility = View.GONE
            
            // Resaltar botón seleccionado
            binding.btnResultados.setBackgroundColor(getColor(R.color.primary_green))
            binding.btnClasificacion.setBackgroundColor(getColor(R.color.primary_green_light))
        }

        binding.btnClasificacion.setOnClickListener {
            Log.d(TAG, "Vista de clasificación seleccionada")
            binding.rvPartidosLiga.visibility = View.GONE
            binding.rvClasificacion.visibility = View.VISIBLE
            
            // Resaltar botón seleccionado
            binding.btnClasificacion.setBackgroundColor(getColor(R.color.primary_green))
            binding.btnResultados.setBackgroundColor(getColor(R.color.primary_green_light))
        }

        // 7. Observar datos del ViewModel
        viewModel.resultados.observe(this) { partidos ->
            Log.d(TAG, "Partidos cargados para liga $ligaId: ${partidos.size}")
            if (partidos.isEmpty()) {
                Log.w(TAG, "No se encontraron partidos para esta liga")
                Toast.makeText(this, getString(R.string.sin_datos), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "${partidos.size} partidos encontrados", Toast.LENGTH_SHORT).show()
            }
            partidosAdapter.submitList(partidos)
        }

        viewModel.clasificacion.observe(this) { clasificacion ->
            Log.d(TAG, "Clasificación cargada: ${clasificacion.size} equipos")
            if (clasificacion.isEmpty()) {
                Log.w(TAG, "No se encontró clasificación para esta liga")
            }
            clasificacionAdapter.submitList(clasificacion)
        }

        viewModel.liga.observe(this) { liga ->
            // Cambiar el icono del corazón según estado
            val iconRes = if (liga.esFavorita) R.drawable.favorito else R.drawable.favorito_borde
            binding.toolbar.menu.findItem(R.id.menu_toggle_favorito)?.setIcon(iconRes)
            
            val mensaje = if (liga.esFavorita) getString(R.string.favorito_agregado) else getString(R.string.favorito_eliminado)
            Log.d(TAG, "Estado favorito actualizado: ${liga.esFavorita}")
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
        }
        
        Log.d(TAG, "onCreate: LigaActivity inicializada correctamente")
    }
    
    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: LigaActivity visible")
    }
}
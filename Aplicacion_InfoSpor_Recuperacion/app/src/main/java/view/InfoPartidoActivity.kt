package view

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.aplicacion_infosport.R
import com.example.aplicacion_infosport.databinding.ActivityInfoPartidoBinding
import view.adapter.EventoAdapter
import viewModel.InfoPartidoViewModel

class InfoPartidoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInfoPartidoBinding
    private val viewModel: InfoPartidoViewModel by viewModels()
    private val TAG = "InfoPartidoActivity"
    private lateinit var eventoAdapter: EventoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: Iniciando InfoPartidoActivity")
        
        binding = ActivityInfoPartidoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val partidoId = intent.getIntExtra("PARTIDO_ID", -1)
        if (partidoId == -1) {
            Log.e(TAG, "Error: partidoId inválido")
            Toast.makeText(this, getString(R.string.error_cargar_datos), Toast.LENGTH_SHORT).show()
            finish()
            return
        }
        
        Log.d(TAG, "Partido ID recibido: $partidoId")

        // Configurar toolbar
        binding.toolbar.setNavigationOnClickListener { 
            Log.d(TAG, "Navegación atrás presionada")
            finish() 
        }

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
        
        // Configurar RecyclerView de eventos
        eventoAdapter = EventoAdapter()
        binding.rvEventos.apply {
            layoutManager = LinearLayoutManager(this@InfoPartidoActivity)
            adapter = eventoAdapter
        }

        // Establecer ID del partido en el ViewModel
        viewModel.setPartidoId(partidoId)
        
        // Observar datos del partido
        viewModel.partido.observe(this) { partido ->
            if (partido == null) {
                Log.e(TAG, "Partido no encontrado en la base de datos")
                Toast.makeText(this, getString(R.string.error_cargar_datos), Toast.LENGTH_LONG).show()
                finish()
                return@observe
            }
            
            Log.d(TAG, "Partido cargado: ${partido.nombreLocal} vs ${partido.nombreVisitante}")
            
            binding.tvLocal.text = partido.nombreLocal
            binding.tvVisitante.text = partido.nombreVisitante
            
            if (partido.marcadorLocal != null && partido.marcadorVisitante != null) {
                binding.tvResultado.text = "${partido.marcadorLocal} - ${partido.marcadorVisitante}"
                Log.d(TAG, "Marcador: ${partido.marcadorLocal} - ${partido.marcadorVisitante}")
            } else {
                binding.tvResultado.text = partido.hora
                Log.d(TAG, "Partido no jugado, hora: ${partido.hora}")
            }

            Glide.with(this)
                .load(partido.escudoLocal)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(binding.imgLocal)
                
            Glide.with(this)
                .load(partido.escudoVisitante)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(binding.imgVisitante)
            
            // Actualizar icono de favorito
            val iconRes = if (partido.esFavorita) R.drawable.favorito else R.drawable.favorito_borde
            binding.toolbar.menu.findItem(R.id.menu_toggle_favorito)?.setIcon(iconRes)
            
            Toast.makeText(this, getString(R.string.detalles_partido), Toast.LENGTH_SHORT).show()
        }
        
        // Observar eventos del partido
        viewModel.getEventos(partidoId).observe(this) { eventos ->
            Log.d(TAG, "Eventos cargados: ${eventos.size}")
            if (eventos.isEmpty()) {
                Log.i(TAG, "No hay eventos para este partido")
                Toast.makeText(this, getString(R.string.sin_eventos), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "${eventos.size} eventos registrados", Toast.LENGTH_SHORT).show()
            }
            eventoAdapter.submitList(eventos)
        }
        
        Log.d(TAG, "onCreate: InfoPartidoActivity inicializada correctamente")
    }
    
    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: InfoPartidoActivity visible")
    }
    
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: InfoPartidoActivity destruida")
    }
}
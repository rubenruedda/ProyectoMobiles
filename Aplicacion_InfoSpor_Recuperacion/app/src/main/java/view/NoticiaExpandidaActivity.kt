package view

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.aplicacion_infosport.R
import com.example.aplicacion_infosport.databinding.ActivityNoticiaExpandidaBinding
import viewModel.NoticiaViewModel

class NoticiaExpandidaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNoticiaExpandidaBinding
    private val viewModel: NoticiaViewModel by viewModels()
    private val TAG = "NoticiaExpandidaActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: Iniciando NoticiaExpandidaActivity")
        
        binding = ActivityNoticiaExpandidaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val noticiaId = intent.getIntExtra("NOTICIA_ID", -1)
        if (noticiaId == -1) {
            Log.e(TAG, "Error: noticiaId inválido")
            Toast.makeText(this, getString(R.string.error_cargar_datos), Toast.LENGTH_SHORT).show()
            finish()
            return
        }
        
        Log.d(TAG, "Noticia ID recibido: $noticiaId")

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

        viewModel.obtenerNoticiaPorId(noticiaId).observe(this) { noticia ->
            if (noticia != null) {
                Log.d(TAG, "Noticia cargada: ${noticia.titulo}")
                binding.tvTituloDetalle.text = noticia.titulo
                binding.tvCuerpo.text = noticia.cuerpo
                binding.tvFuenteFechaDetalle.text = "${noticia.fuente} | ${noticia.fechaPublicacion}"

                Glide.with(this)
                    .load(noticia.urlImagen)
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .into(binding.ivImagenDetalle)
                    
                Toast.makeText(this, "Leyendo: ${noticia.titulo}", Toast.LENGTH_SHORT).show()
            } else {
                Log.e(TAG, "Noticia no encontrada en la base de datos")
                Toast.makeText(this, getString(R.string.error_cargar_datos), Toast.LENGTH_SHORT).show()
                finish()
            }
        }
        
        Log.d(TAG, "onCreate: NoticiaExpandidaActivity inicializada")
    }
    
    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: NoticiaExpandidaActivity visible")
    }
}
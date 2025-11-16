package view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.aplicacion_infosport.databinding.ActivityNoticiaExpandidaBinding
import viewModel.NoticiaViewModel

class NoticiaExpandida : AppCompatActivity() {

    private lateinit var binding: ActivityNoticiaExpandidaBinding

    // 1. Obtener el ViewModel
    private val viewModel: NoticiaViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoticiaExpandidaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 2. Configurar Toolbar
        binding.toolbarNoticia.setNavigationOnClickListener {
            finish() // Cierra la actividad al pulsar "atrás"
        }

        // 3. RECIBIR DATOS DEL INTENT
        val noticiaId = intent.getIntExtra("NOTICIA_ID", -1)

        if (noticiaId == -1) {
            finish() // Salir si no hay ID
            return
        }

        // 4. Observar el LiveData
        // Usamos la función del ViewModel para obtener UNA noticia
        viewModel.obtenerNoticiaPorId(noticiaId).observe(this) { noticia ->
            if (noticia != null) {
                // 5. Poblar la UI
                binding.collapsingToolbar.title = noticia.fuente // Título de la barra
                binding.tvTituloNoticia.text = noticia.titulo
                binding.tvCuerpoNoticia.text = noticia.cuerpo

                Glide.with(this)
                    .load(noticia.urlImagen)
                    .into(binding.ivImagenNoticia)
            }
        }
    }
}
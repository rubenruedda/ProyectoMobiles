package view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.aplicacion_infosport.R
import com.example.aplicacion_infosport.databinding.ActivityNoticiaExpandidaBinding
import viewModel.NoticiaViewModel

class NoticiaExpandidaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNoticiaExpandidaBinding
    private val viewModel: NoticiaViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoticiaExpandidaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val noticiaId = intent.getIntExtra("NOTICIA_ID", -1)
        if (noticiaId == -1) finish()

        binding.toolbar.setNavigationOnClickListener { finish() }
        binding.toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_toggle_favorito -> {
                    viewModel.toggleFavorito()
                    true
                }
                else -> false
            }
        }

        viewModel.obtenerNoticiaPorId(noticiaId).observe(this) { noticia ->
            if (noticia != null) {
                binding.tvTituloDetalle.text = noticia.titulo
                binding.tvCuerpo.text = noticia.cuerpo
                binding.tvFuenteFechaDetalle.text = "${noticia.fuente} | ${noticia.fechaPublicacion}"

                Glide.with(this).load(noticia.urlImagen).into(binding.ivImagenDetalle)
            }
        }

        viewModel.noticia.observe(this) { noticia ->
            // Cambiar el icono del corazón según estado
            val iconRes = if (noticia.esFavorita) R.drawable.favorito else R.drawable.favorito_borde
            binding.toolbar.menu.findItem(R.id.menu_toggle_favorito)?.setIcon(iconRes)
        }
    }
}
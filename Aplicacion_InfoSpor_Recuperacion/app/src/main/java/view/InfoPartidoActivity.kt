package view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.aplicacion_infosport.R
import com.example.aplicacion_infosport.databinding.ActivityInfoPartidoBinding
import viewModel.InfoPartidoViewModel

class InfoPartidoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInfoPartidoBinding
    private val viewModel: InfoPartidoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoPartidoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val partidoId = intent.getIntExtra("PARTIDO_ID", -1)
        if (partidoId == -1) finish()

        binding.btnBack.setOnClickListener { finish() }

        viewModel.setPartidoId(partidoId)
        viewModel.partido.observe(this) { partido ->
            partido?.let {
                binding.tvLocal.text = it.nombreLocal
                binding.tvVisitante.text = it.nombreVisitante
                binding.tvResultado.text = if (it.golesLocal != null) "${it.golesLocal} - ${it.golesVisitante}" else it.hora

                Glide.with(this).load(it.escudoLocal).into(binding.imgLocal)
                Glide.with(this).load(it.escudoVisitante).into(binding.imgVisitante)
            }
        }
    }
}
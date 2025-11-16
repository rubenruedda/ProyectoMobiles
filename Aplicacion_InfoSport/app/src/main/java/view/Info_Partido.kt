package view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.aplicacion_infosport.R
import com.example.aplicacion_infosport.databinding.ActivityInfoPartidoBinding
import com.google.android.material.tabs.TabLayoutMediator
import model.Partido
import view.adapter.EventoAdapter
import view.adapter.InfoPartidoAdapter
import viewModel.InfoPartidoViewModel


class Info_Partido : AppCompatActivity() {
private lateinit var binding: ActivityInfoPartidoBinding
private val viewModel: InfoPartidoViewModel by viewModels()
private lateinit var viewPagerAdapter: InfoPartidoAdapter

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityInfoPartidoBinding.inflate(layoutInflater)
    setContentView(binding.root)

    val partidoId = intent.getIntExtra("PARTIDO_ID", -1)

    // MODIFICACIÓN: Recibe el nombre de la liga (ver punto 4)
    val ligaNombre = intent.getStringExtra("LIGA_NOMBRE")

    if (partidoId == -1) {
        finish()
        return
    }

    // Configurar ViewPager (Pestañas)
    setupViewPager()

    // Informar al ViewModel
    viewModel.setPartidoId(partidoId)

    // Observar solo el partido principal
    observarPartido(ligaNombre)
}

private fun setupViewPager() {
    viewPagerAdapter = InfoPartidoAdapter(this)
    binding.viewPagerPartido.adapter = viewPagerAdapter

    TabLayoutMediator(binding.tabLayoutPartido, binding.viewPagerPartido) { tab, position ->
        tab.text = viewPagerAdapter.titulos[position]
    }.attach()
}

private fun observarPartido(ligaNombre: String?) {
    viewModel.partido.observe(this) { partido ->
        if (partido != null) {
            actualizarMarcadorUI(partido)

            // Muestra el nombre de la liga si lo recibimos
            if (ligaNombre != null) {
                binding.tvLigaInfo.text = ligaNombre
            } else {
                // Si no, ocultamos el texto
                binding.tvLigaInfo.visibility = View.GONE
            }
        }
    }
}

private fun actualizarMarcadorUI(partido: Partido) {
    // (Este código es el mismo que antes)
    val marcadorBinding = binding.marcadorLayout
    marcadorBinding.tvNombreLocal.text = partido.nombreLocal
    marcadorBinding.tvNombreVisitante.text = partido.nombreVisitante
    Glide.with(this).load(partido.escudoLocal).into(marcadorBinding.ivEscudoLocal)
    Glide.with(this).load(partido.escudoVisitante).into(marcadorBinding.ivEscudoVisitante)

    if (partido.marcadorLocal != null) {
        marcadorBinding.tvMarcadorHora.text = "${partido.marcadorLocal} - ${partido.marcadorVisitante}"
    } else {
        marcadorBinding.tvMarcadorHora.text = partido.hora
    }
}
}
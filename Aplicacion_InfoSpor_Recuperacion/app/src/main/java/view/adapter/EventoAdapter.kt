package view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.aplicacion_infosport.R
import com.example.aplicacion_infosport.databinding.ItemEventoPartidoBinding
import model.Evento

class EventoAdapter : RecyclerView.Adapter<EventoAdapter.EventoViewHolder>() {

    private var eventos = listOf<Evento>()

    fun submitList(newEventos: List<Evento>) {
        eventos = newEventos
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventoViewHolder {
        val binding = ItemEventoPartidoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return EventoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventoViewHolder, position: Int) {
        holder.bind(eventos[position])
    }

    override fun getItemCount(): Int = eventos.size

    inner class EventoViewHolder(private val binding: ItemEventoPartidoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(evento: Evento) {
            binding.tvMinuto.text = "${evento.minuto}'"
            
            // Construir texto del jugador con tipo de evento
            val textoEvento = "${evento.jugador ?: "Desconocido"} (${evento.tipo})"
            binding.tvNombreJugador.text = textoEvento
            
            // Seleccionar icono según tipo de evento
            val iconRes = when (evento.tipo.lowercase()) {
                "gol" -> R.drawable.ic_launcher_foreground // Idealmente tendrías un icono de gol
                "tarjeta amarilla" -> R.drawable.yellow_card
                "tarjeta roja" -> R.drawable.yellow_card // Idealmente tendrías un icono de tarjeta roja
                else -> R.drawable.ic_launcher_foreground
            }
            binding.ivIconoEvento.setImageResource(iconRes)
        }
    }
}

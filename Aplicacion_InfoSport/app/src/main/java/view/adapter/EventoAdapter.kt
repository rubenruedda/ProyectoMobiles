package view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.aplicacion_infosport.R
import com.example.aplicacion_infosport.databinding.ItemEventoPartidoBinding
import model.Evento

class EventoDiffCallback : DiffUtil.ItemCallback<Evento>() {
    override fun areItemsTheSame(oldItem: Evento, newItem: Evento): Boolean = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Evento, newItem: Evento): Boolean = oldItem == newItem
}

class EventoAdapter : ListAdapter<Evento, EventoAdapter.EventoViewHolder>(EventoDiffCallback()) {

    class EventoViewHolder(private val binding: ItemEventoPartidoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(evento: Evento) {
            binding.tvMinuto.text = "${evento.minuto}'"

            val detalle = when (evento.tipo.lowercase()) {
                "gol" -> {
                    binding.ivIconoEvento.setImageResource(R.drawable.competiciones) // Necesitas este icono
                    "Gol de ${evento.nombreJugador}"
                }
                "tarjeta amarilla" -> {
                    binding.ivIconoEvento.setImageResource(R.drawable.yellow_card) // Necesitas este icono
                    "Tarjeta amarilla para ${evento.nombreJugador}"
                }
                // ... más tipos
                else -> evento.nombreJugador
            }
            binding.tvDetalleEvento.text = detalle
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventoViewHolder {
        val binding = ItemEventoPartidoBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return EventoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
package view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aplicacion_infosport.databinding.ItemPartidoBinding
import model.Partido

class PartidoDiffCallback : DiffUtil.ItemCallback<Partido>() {
    override fun areItemsTheSame(oldItem: Partido, newItem: Partido): Boolean = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Partido, newItem: Partido): Boolean = oldItem == newItem
}

class PartidoAdapter(private val onPartidoClick: (Partido) -> Unit) :
    ListAdapter<Partido, PartidoAdapter.PartidoViewHolder>(PartidoDiffCallback()) {

    // El ViewHolder
    class PartidoViewHolder(private val binding: ItemPartidoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(partido: Partido, onPartidoClick: (Partido) -> Unit) {
            binding.tvNombreLocal.text = partido.nombreLocal
            binding.tvNombreVisitante.text = partido.nombreVisitante

            // Cargar escudos
            Glide.with(itemView.context).load(partido.escudoLocal).into(binding.ivEscudoLocal)
            Glide.with(itemView.context).load(partido.escudoVisitante).into(binding.ivEscudoVisitante)

            // Mostrar marcador o la hora
            if (partido.marcadorLocal != null) {
                binding.tvMarcadorHora.text = "${partido.marcadorLocal} - ${partido.marcadorVisitante}"
            } else {
                binding.tvMarcadorHora.text = partido.hora
            }

            // Click listener para el item
            binding.root.setOnClickListener {
                onPartidoClick(partido)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PartidoViewHolder {
        val binding = ItemPartidoBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return PartidoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PartidoViewHolder, position: Int) {
        holder.bind(getItem(position), onPartidoClick)
    }
}
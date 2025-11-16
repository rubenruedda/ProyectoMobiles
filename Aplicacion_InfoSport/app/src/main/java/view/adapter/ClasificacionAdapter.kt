package view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aplicacion_infosport.databinding.ItemClasificacionBinding
import model.Clasificacion

class ClasificacionDiffCallback : DiffUtil.ItemCallback<Clasificacion>() {
    override fun areItemsTheSame(oldItem: Clasificacion, newItem: Clasificacion): Boolean {
        return oldItem.equipoId == newItem.equipoId && oldItem.ligaId == newItem.ligaId
    }
    override fun areContentsTheSame(oldItem: Clasificacion, newItem: Clasificacion): Boolean {
        return oldItem == newItem
    }
}

class ClasificacionAdapter :
    ListAdapter<Clasificacion, ClasificacionAdapter.ClasificacionViewHolder>(ClasificacionDiffCallback()) {

    class ClasificacionViewHolder(private val binding: ItemClasificacionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Clasificacion) {
            binding.tvPosicion.text = item.posicion.toString()
            binding.tvNombreEquipo.text = item.nombreEquipo
            binding.tvPuntos.text = item.puntos.toString()
            binding.tvPartidosJugados.text = item.partidosJugados.toString()
            binding.tvGanados.text = item.ganados.toString()
            binding.tvEmpatados.text = item.empatados.toString()
            binding.tvPerdidos.text = item.perdidos.toString()

            Glide.with(itemView.context).load(item.escudoEquipo).into(binding.ivEscudoEquipo)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClasificacionViewHolder {
        val binding = ItemClasificacionBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ClasificacionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ClasificacionViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
package view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aplicacion_infosport.databinding.ItemClasificacionBinding
import model.Clasificacion

class ClasificacionAdapter : ListAdapter<Clasificacion, ClasificacionAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemClasificacionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val binding: ItemClasificacionBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Clasificacion) {
            binding.tvPosicion.text = item.posicion.toString()
            binding.tvNombreEquipo.text = item.nombreEquipo
            binding.tvPuntos.text = item.puntos.toString()
            binding.tvPJ.text = item.partidosJugados.toString() // Asegúrate de que en el XML el ID sea tvPJ o tvPartidosJugados

            // Si tienes escudo en el modelo Clasificacion
            Glide.with(binding.root.context).load(item.escudoEquipo).into(binding.ivEscudoEquipo)
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Clasificacion>() {
        override fun areItemsTheSame(oldItem: Clasificacion, newItem: Clasificacion): Boolean =
            oldItem.equipoId == newItem.equipoId
        override fun areContentsTheSame(oldItem: Clasificacion, newItem: Clasificacion): Boolean =
            oldItem == newItem
    }
}
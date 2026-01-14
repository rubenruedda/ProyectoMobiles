package view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aplicacion_infosport.databinding.ItemPartidoBinding
import model.Partido

class PartidoAdapter(
    private val onItemSelected: (Partido) -> Unit,
    private val onPartidoClick: (Partido) -> Unit
) : ListAdapter<Partido, PartidoAdapter.PartidoViewHolder>(PartidoDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PartidoViewHolder {
        val binding = ItemPartidoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PartidoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PartidoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class PartidoViewHolder(private val binding: ItemPartidoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(partido: Partido) {
            binding.tvLocal.text = partido.nombreLocal
            binding.tvVisitante.text = partido.nombreVisitante

            // Lógica visual: Si tiene marcador, muéstralo. Si no, muestra la hora.
            if (partido.marcadorLocal != null) {
                binding.tvResultado.text = "${partido.marcadorLocal} - ${partido.marcadorVisitante}"
                binding.tvHora.text = "Finalizado"
            } else {
                binding.tvResultado.text = "VS"
                binding.tvHora.text = partido.hora
            }

            Glide.with(binding.root.context).load(partido.escudoLocal).into(binding.imgLocal)
            Glide.with(binding.root.context).load(partido.escudoVisitante).into(binding.imgVisitante)

            binding.root.setOnClickListener { onItemSelected(partido) }
        }
    }

    class PartidoDiffCallback : DiffUtil.ItemCallback<Partido>() {
        override fun areItemsTheSame(oldItem: Partido, newItem: Partido): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Partido, newItem: Partido): Boolean = oldItem == newItem
    }
}
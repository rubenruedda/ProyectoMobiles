package view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aplicacion_infosport.databinding.ItemEquipoSimpleBinding
import model.Equipo

class EquipoDiffCallback : DiffUtil.ItemCallback<Equipo>() {
    override fun areItemsTheSame(oldItem: Equipo, newItem: Equipo): Boolean = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Equipo, newItem: Equipo): Boolean = oldItem == newItem
}

class EquipoSimpleAdapter(private val onEquipoClick: (Equipo) -> Unit) :
    ListAdapter<Equipo, EquipoSimpleAdapter.EquipoViewHolder>(EquipoDiffCallback()) {

    class EquipoViewHolder(private val binding: ItemEquipoSimpleBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(equipo: Equipo, onEquipoClick: (Equipo) -> Unit) {
            binding.tvNombreEquipo.text = equipo.nombre
            Glide.with(itemView.context)
                .load(equipo.escudoUrl)
                .into(binding.ivEscudoEquipo)

            binding.root.setOnClickListener {
                onEquipoClick(equipo)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EquipoViewHolder {
        val binding = ItemEquipoSimpleBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return EquipoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EquipoViewHolder, position: Int) {
        holder.bind(getItem(position), onEquipoClick)
    }
}
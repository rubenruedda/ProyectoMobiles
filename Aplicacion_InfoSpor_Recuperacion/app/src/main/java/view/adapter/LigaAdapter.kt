package view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aplicacion_infosport.databinding.ItemLigaBinding
import model.Liga

class LigaAdapter(
    private val onLigaClick: (Liga) -> Unit
) : ListAdapter<Liga, LigaAdapter.LigaViewHolder>(LigaDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LigaViewHolder {
        val binding = ItemLigaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LigaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LigaViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class LigaViewHolder(private val binding: ItemLigaBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(liga: Liga) {
            binding.tvNombreLiga.text = liga.nombre
            binding.tvPais.text = liga.paisNombre

            Glide.with(binding.root.context)
                .load(liga.logoUrl)
                .into(binding.ivLogoLiga)

            binding.root.setOnClickListener { onLigaClick(liga) }
        }
    }

    class LigaDiffCallback : DiffUtil.ItemCallback<Liga>() {
        override fun areItemsTheSame(oldItem: Liga, newItem: Liga): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Liga, newItem: Liga): Boolean = oldItem == newItem
    }
}
package view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aplicacion_infosport.databinding.ItemLigaSimpleBinding
import com.example.aplicacion_infosport.databinding.ItemPaisHeaderBinding
import model.Liga
import viewModel.CompeticionItem

private const val VIEW_TYPE_PAIS = 0
private const val VIEW_TYPE_LIGA = 1

class CompeticionAdapter(private val onLigaClick: (Liga) -> Unit) :
    ListAdapter<CompeticionItem, RecyclerView.ViewHolder>(CompeticionDiffCallback()) {

    // --- ViewHolder para la Cabecera (País) ---
    class PaisViewHolder(private val binding: ItemPaisHeaderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(header: CompeticionItem.PaisHeader) {
            binding.tvPaisNombre.text = header.nombre
        }
    }

    // --- ViewHolder para el Item (Liga) ---
    class LigaViewHolder(private val binding: ItemLigaSimpleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CompeticionItem.LigaItem, onLigaClick: (Liga) -> Unit) {
            val liga = item.liga
            binding.tvNombreLiga.text = liga.nombre
            Glide.with(itemView.context).load(liga.logoUrl).into(binding.ivLogoLiga)
            binding.root.setOnClickListener {
                onLigaClick(liga)
            }
        }
    }

    // --- Lógica del Adaptador ---

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is CompeticionItem.PaisHeader -> VIEW_TYPE_PAIS
            is CompeticionItem.LigaItem -> VIEW_TYPE_LIGA
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_PAIS -> {
                val binding = ItemPaisHeaderBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                PaisViewHolder(binding)
            }
            VIEW_TYPE_LIGA -> {
                val binding = ItemLigaSimpleBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                LigaViewHolder(binding)
            }
            else -> throw IllegalArgumentException("Tipo de vista desconocido")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = getItem(position)) {
            is CompeticionItem.PaisHeader -> (holder as PaisViewHolder).bind(item)
            is CompeticionItem.LigaItem -> (holder as LigaViewHolder).bind(item, onLigaClick)
        }
    }
}

// --- DiffUtil Callback ---
class CompeticionDiffCallback : DiffUtil.ItemCallback<CompeticionItem>() {
    override fun areItemsTheSame(oldItem: CompeticionItem, newItem: CompeticionItem): Boolean {
        return oldItem == newItem
    }
    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: CompeticionItem, newItem: CompeticionItem): Boolean {
        return oldItem.toString() == newItem.toString() // Comparación simple
    }
}
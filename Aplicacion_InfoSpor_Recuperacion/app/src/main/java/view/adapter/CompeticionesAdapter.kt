package view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aplicacion_infosport.R
import com.example.aplicacion_infosport.databinding.ItemLigaBinding
import com.example.aplicacion_infosport.databinding.ItemPaisHeaderBinding
import model.Liga
import viewModel.CompeticionItem

private const val TIPO_PAIS = 0
private const val TIPO_LIGA = 1

class CompeticionesAdapter(
    private val onLigaClick: (Liga) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items = listOf<CompeticionItem>()

    fun submitList(nuevaLista: List<CompeticionItem>) {
        items = nuevaLista
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is CompeticionItem.PaisHeader -> TIPO_PAIS
            is CompeticionItem.LigaItem -> TIPO_LIGA
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TIPO_PAIS -> {
                val binding = ItemPaisHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                PaisViewHolder(binding)
            }
            else -> {
                val binding = ItemLigaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                LigaViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = items[position]) {
            is CompeticionItem.PaisHeader -> (holder as PaisViewHolder).bind(item)
            is CompeticionItem.LigaItem -> (holder as LigaViewHolder).bind(item, onLigaClick)
        }
    }

    override fun getItemCount(): Int = items.size

    class PaisViewHolder(private val binding: ItemPaisHeaderBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CompeticionItem.PaisHeader) {
            binding.tvPaisNombre.text = item.nombre
        }
    }

    class LigaViewHolder(private val binding: ItemLigaBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CompeticionItem.LigaItem, clickListener: (Liga) -> Unit) {
            binding.tvNombreLiga.text = item.liga.nombre
            binding.tvPais.text = item.liga.paisNombre
            Glide.with(binding.root.context)
                .load(item.liga.logoUrl)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(binding.ivLogoLiga)
            binding.root.setOnClickListener { clickListener(item.liga) }
        }
    }
}
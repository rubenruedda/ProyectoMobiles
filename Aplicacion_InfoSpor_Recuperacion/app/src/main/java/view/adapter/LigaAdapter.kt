package view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aplicacion_infosport.databinding.ItemLigaBinding
import model.Liga

class LigaAdapter(
    private var listaLigas: List<Liga>,
    private val onLigaClick: (Liga) -> Unit
) : RecyclerView.Adapter<LigaAdapter.LigaViewHolder>() {

    fun actualizarDatos(nuevaLista: List<Liga>) {
        listaLigas = nuevaLista
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LigaViewHolder {
        val binding = ItemLigaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LigaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LigaViewHolder, position: Int) {
        holder.bind(listaLigas[position])
    }

    override fun getItemCount(): Int = listaLigas.size

    inner class LigaViewHolder(private val binding: ItemLigaBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(liga: Liga) {
            binding.tvNombreLiga.text = liga.nombre
            binding.tvPais.text = liga.paisNombre

            Glide.with(binding.root.context)
                .load(liga.logoUrl)
                .placeholder(android.R.drawable.ic_menu_gallery) // Placeholder simple
                .into(binding.ivLogoLiga)

            binding.root.setOnClickListener { onLigaClick(liga) }
        }
    }
}
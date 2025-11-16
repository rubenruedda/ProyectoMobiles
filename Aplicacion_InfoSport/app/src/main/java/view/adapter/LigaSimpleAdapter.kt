package view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
// Asegúrate de que esta importación sea la de tu ViewBinding
import com.example.aplicacion_infosport.databinding.ItemLigaSimpleBinding
import model.Liga // <-- Importa el modelo

class LigaDiffCallback : DiffUtil.ItemCallback<Liga>() {
    override fun areItemsTheSame(oldItem: Liga, newItem: Liga): Boolean = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Liga, newItem: Liga): Boolean = oldItem == newItem
}

class LigaSimpleAdapter(private val onLigaClick: (Liga) -> Unit) :
    ListAdapter<Liga, LigaSimpleAdapter.LigaViewHolder>(LigaDiffCallback()) {

    // ESTA CLASE NO DEBE SOBREESCRIBIR getItemCount()
    // ListAdapter lo gestiona solo.

    class LigaViewHolder(private val binding: ItemLigaSimpleBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(liga: Liga, onLigaClick: (Liga) -> Unit) {
            binding.tvNombreLiga.text = liga.nombre
            Glide.with(itemView.context).load(liga.logoUrl).into(binding.ivLogoLiga)
            binding.root.setOnClickListener {
                onLigaClick(liga)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LigaViewHolder {
        val binding = ItemLigaSimpleBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return LigaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LigaViewHolder, position: Int) {
        holder.bind(getItem(position), onLigaClick)
    }
}
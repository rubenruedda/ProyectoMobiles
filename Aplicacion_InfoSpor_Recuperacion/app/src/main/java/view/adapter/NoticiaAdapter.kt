package view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aplicacion_infosport.R
import com.example.aplicacion_infosport.databinding.ItemNoticiaBinding
import model.Noticia

class NoticiaAdapter(
    private val onItemSelected: (Noticia) -> Unit,
    private val onNoticiaClick: (Noticia) -> Unit
) : ListAdapter<Noticia, NoticiaAdapter.NoticiaViewHolder>(NoticiaDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticiaViewHolder {
        val binding = ItemNoticiaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoticiaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoticiaViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class NoticiaViewHolder(private val binding: ItemNoticiaBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(noticia: Noticia) {
            binding.tvTitulo.text = noticia.titulo
            binding.tvFuenteFecha.text = "${noticia.fuente} • ${noticia.fechaPublicacion}"

            val icono = if (noticia.esFavorita) R.drawable.favorito else R.drawable.favorito_borde
            binding.ivNoticia.setImageResource(icono)

            binding.root.setOnClickListener { onItemSelected(noticia) }

            binding.ivNoticia.setOnClickListener {
                // Invertimos el estado visualmente al momento para dar feedback rápido
                noticia.esFavorita = !noticia.esFavorita
                notifyItemChanged(adapterPosition)
                // Avisamos al Activity para que guarde en BD
                onNoticiaClick(noticia)
            }
        }
    }

    class NoticiaDiffCallback : DiffUtil.ItemCallback<Noticia>() {
        override fun areItemsTheSame(oldItem: Noticia, newItem: Noticia): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Noticia, newItem: Noticia): Boolean = oldItem == newItem
    }
}
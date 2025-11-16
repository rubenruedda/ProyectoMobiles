// Archivo: NoticiasAdapter.kt

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aplicacion_infosport.databinding.ItemNoticiaBinding
import model.Noticia

class NoticiaDiffCallback : DiffUtil.ItemCallback<Noticia>() {
    override fun areItemsTheSame(oldItem: Noticia, newItem: Noticia): Boolean = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Noticia, newItem: Noticia): Boolean = oldItem == newItem
}

class NoticiaAdapter(private val onNoticiaClick: (Noticia) -> Unit) :
    ListAdapter<Noticia, NoticiaAdapter.NoticiaViewHolder>(NoticiaDiffCallback()) {

    class NoticiaViewHolder(private val binding: ItemNoticiaBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(noticia: Noticia, onNoticiaClick: (Noticia) -> Unit) {
            binding.tvTituloNoticia.text = noticia.titulo
            binding.tvFuenteNoticia.text = "${noticia.fuente} - ${noticia.fechaPublicacion}"

            // Cargar imagen
            Glide.with(itemView.context)
                .load(noticia.urlImagen)
                .into(binding.ivImagenNoticia)

            // Click listener
            binding.root.setOnClickListener {
                onNoticiaClick(noticia)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticiaViewHolder {
        val binding = ItemNoticiaBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return NoticiaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoticiaViewHolder, position: Int) {
        holder.bind(getItem(position), onNoticiaClick)
    }
}
package view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aplicacion_infosport.R
import com.example.aplicacion_infosport.databinding.ItemNoticiaFavoritaBinding
import model.Noticia

class NoticiaFavoritaAdapter(
    private val onNoticiaClick: (Noticia) -> Unit,
    private val onFavoritoClick: (Noticia) -> Unit
) : RecyclerView.Adapter<NoticiaFavoritaAdapter.NoticiaViewHolder>() {

    private var listaNoticias = listOf<Noticia>()

    fun submitList(nuevaLista: List<Noticia>) {
        listaNoticias = nuevaLista
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticiaViewHolder {
        val binding = ItemNoticiaFavoritaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoticiaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoticiaViewHolder, position: Int) {
        holder.bind(listaNoticias[position])
    }

    override fun getItemCount(): Int = listaNoticias.size

    inner class NoticiaViewHolder(private val binding: ItemNoticiaFavoritaBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(noticia: Noticia) {
            binding.tvTitulo.text = noticia.titulo
            binding.tvFuenteFecha.text = "${noticia.fuente} • ${noticia.fechaPublicacion}"

            Glide.with(binding.root.context)
                .load(noticia.urlImagen)
                .placeholder(R.drawable.placeholder_noticia)
                .error(R.drawable.placeholder_noticia)
                .centerCrop()
                .into(binding.ivNoticia)

            // Mostrar icono de favorito correcto
            val iconRes = if (noticia.esFavorita) R.drawable.favorito else R.drawable.favorito_borde
            binding.btnFavorito.setImageResource(iconRes)

            binding.root.setOnClickListener { onNoticiaClick(noticia) }
            binding.btnFavorito.setOnClickListener { onFavoritoClick(noticia) }
        }
    }
}

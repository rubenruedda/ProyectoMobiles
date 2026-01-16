package view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aplicacion_infosport.R
import com.example.aplicacion_infosport.databinding.ItemNoticiaBinding
import model.Noticia

class NoticiaAdapter(
    private var listaNoticias: List<Noticia>,
    private val onNoticiaClick: (Noticia) -> Unit
) : RecyclerView.Adapter<NoticiaAdapter.NoticiaViewHolder>() {

    fun actualizarDatos(nuevaLista: List<Noticia>) {
        listaNoticias = nuevaLista
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticiaViewHolder {
        val binding = ItemNoticiaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoticiaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoticiaViewHolder, position: Int) {
        holder.bind(listaNoticias[position])
    }

    override fun getItemCount(): Int = listaNoticias.size

    inner class NoticiaViewHolder(private val binding: ItemNoticiaBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(noticia: Noticia) {
            binding.tvTitulo.text = noticia.titulo
            binding.tvFuenteFecha.text = "${noticia.fuente} • ${noticia.fechaPublicacion}"

            Glide.with(binding.root.context)
                .load(noticia.urlImagen)
                .placeholder(R.drawable.placeholder_noticia)
                .error(R.drawable.placeholder_noticia)
                .centerCrop()
                .into(binding.ivNoticia)

            binding.root.setOnClickListener { onNoticiaClick(noticia) }
        }
    }
}
package view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aplicacion_infosport.R
import com.example.aplicacion_infosport.databinding.ItemClasificacionBinding
import model.Clasificacion

class ClasificacionAdapter : RecyclerView.Adapter<ClasificacionAdapter.ViewHolder>() {

    private var clasificacion = listOf<Clasificacion>()

    fun submitList(nuevaLista: List<Clasificacion>) {
        clasificacion = nuevaLista
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemClasificacionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(clasificacion[position])
    }

    override fun getItemCount(): Int = clasificacion.size

    class ViewHolder(private val binding: ItemClasificacionBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Clasificacion) {
            binding.tvPosicion.text = item.posicion.toString()
            binding.tvNombreEquipo.text = item.nombreEquipo
            binding.tvPuntos.text = item.puntos.toString()
            binding.tvPJ.text = item.partidosJugados.toString()

            // Si tienes escudo en el modelo Clasificacion
            Glide.with(binding.root.context)
                .load(item.escudoEquipo)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(binding.ivEscudoEquipo)
        }
    }
}
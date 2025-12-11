package view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aplicacion_infosport.databinding.ItemPartidoBinding
import model.Partido

class PartidoAdapter(
    private var listaPartidos: List<Partido>,
    private val onPartidoClick: (Partido) -> Unit
) : RecyclerView.Adapter<PartidoAdapter.PartidoViewHolder>() {

    // Método para actualizar datos desde la Activity
    fun actualizarDatos(nuevaLista: List<Partido>) {
        listaPartidos = nuevaLista
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PartidoViewHolder {
        val binding = ItemPartidoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PartidoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PartidoViewHolder, position: Int) {
        holder.bind(listaPartidos[position])
    }

    override fun getItemCount(): Int = listaPartidos.size

    inner class PartidoViewHolder(private val binding: ItemPartidoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(partido: Partido) {
            binding.tvLocal.text = partido.nombreLocal
            binding.tvVisitante.text = partido.nombreVisitante
            binding.tvHora.text = partido.hora

            // Resultado o "VS"
            if (partido.golesLocal != null) {
                binding.tvResultado.text = "${partido.golesLocal} - ${partido.golesVisitante}"
            } else {
                binding.tvResultado.text = "VS"
            }

            // Cargar imágenes con Glide
            Glide.with(binding.root.context).load(partido.escudoLocal).into(binding.imgLocal)
            Glide.with(binding.root.context).load(partido.escudoVisitante).into(binding.imgVisitante)

            // Click Listener
            binding.root.setOnClickListener { onPartidoClick(partido) }
        }
    }
}
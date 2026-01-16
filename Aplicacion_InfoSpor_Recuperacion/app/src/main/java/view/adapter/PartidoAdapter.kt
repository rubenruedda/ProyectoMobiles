package view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aplicacion_infosport.R
import com.example.aplicacion_infosport.databinding.ItemPartidoBinding
import model.Partido

class PartidoAdapter(
    private val onPartidoClick: (Partido) -> Unit
) : RecyclerView.Adapter<PartidoAdapter.PartidoViewHolder>() {

    private var partidos = listOf<Partido>()

    fun submitList(nuevaLista: List<Partido>) {
        partidos = nuevaLista
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PartidoViewHolder {
        val binding = ItemPartidoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PartidoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PartidoViewHolder, position: Int) {
        holder.bind(partidos[position])
    }

    override fun getItemCount(): Int = partidos.size

    inner class PartidoViewHolder(private val binding: ItemPartidoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(partido: Partido) {
            binding.tvLocal.text = partido.nombreLocal
            binding.tvVisitante.text = partido.nombreVisitante

            // Lógica visual: Si tiene marcador, muéstralo. Si no, muestra la hora.
            if (partido.marcadorLocal != null) {
                binding.tvResultado.text = "${partido.marcadorLocal} - ${partido.marcadorVisitante}"
                binding.tvHora.text = binding.root.context.getString(R.string.finalizado)
            } else {
                binding.tvResultado.text = binding.root.context.getString(R.string.vs)
                binding.tvHora.text = partido.hora
            }

            Glide.with(binding.root.context)
                .load(partido.escudoLocal)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(binding.imgLocal)
                
            Glide.with(binding.root.context)
                .load(partido.escudoVisitante)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(binding.imgVisitante)

            binding.root.setOnClickListener { onPartidoClick(partido) }
        }
    }
}
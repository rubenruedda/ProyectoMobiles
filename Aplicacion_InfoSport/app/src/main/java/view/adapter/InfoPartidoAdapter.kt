package view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import view.fragment.AlineacionesFragment
import view.fragment.EventosFragment

class InfoPartidoAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    val titulos = listOf("EVENTOS", "ALINEACIONES")

    override fun getItemCount(): Int = titulos.size

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> EventosFragment()
            1 -> AlineacionesFragment() // Usamos tu fragment existente
            else -> throw IllegalStateException("Posición inesperada $position")
        }
    }
}
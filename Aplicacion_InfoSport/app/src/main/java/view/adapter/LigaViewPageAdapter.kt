package view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import view.fragment.ClasificacionFragment
import view.fragment.PartidosLigaFragment

class LigaViewPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    // 1. ESTE CÓDIGO PERTENECE A ESTE ARCHIVO
    val titulos = listOf("CLASIFICACIÓN", "RESULTADOS", "PRÓXIMOS")

    override fun getItemCount(): Int {
        return titulos.size
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ClasificacionFragment()
            1 -> PartidosLigaFragment.newInstance(PartidosLigaFragment.TIPO_RESULTADOS)
            2 -> PartidosLigaFragment.newInstance(PartidosLigaFragment.TIPO_PROXIMOS)
            else -> throw IllegalStateException("Posición inesperada $position")
        }
    }
}
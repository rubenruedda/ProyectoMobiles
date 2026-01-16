package view

import android.content.Intent
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.aplicacion_infosport.R
import com.google.android.material.bottomnavigation.BottomNavigationView

open class BaseActivity : AppCompatActivity() {

    private val TAG = "BaseActivity"

    fun setupBottomNavigation(bottomNavigationView: BottomNavigationView, selectedItemId: Int) {
        Log.d(TAG, "Configurando navegación inferior, item seleccionado: $selectedItemId")
        bottomNavigationView.selectedItemId = selectedItemId

        bottomNavigationView.setOnItemSelectedListener { item ->
            if (item.itemId != selectedItemId) {
                Log.d(TAG, "Navegando a: ${item.title}")
                when (item.itemId) {
                    R.id.nav_inicio -> navegar(MainActivity::class.java)
                    R.id.nav_competiciones -> navegar(CompeticionesActivity::class.java)
                    R.id.nav_noticias -> navegar(NoticiasActivity::class.java)
                    R.id.nav_favoritos -> navegar(FavoritosActivity::class.java)
                    R.id.nav_acerca_de -> navegar(AcercaDeActivity::class.java)
                }
            }
            true
        }
    }

    private fun navegar(destino: Class<*>) {
        Log.d(TAG, "Iniciando actividad: ${destino.simpleName}")
        startActivity(Intent(this, destino))
        overridePendingTransition(0, 0) // Elimina animación para efecto "pestaña"
        finish() // Cierra la activity actual
    }
}
package view

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.aplicacion_infosport.R
import com.google.android.material.bottomnavigation.BottomNavigationView

open class BaseActivity : AppCompatActivity() {

    fun setupBottomNavigation(bottomNavigationView: BottomNavigationView, selectedItemId: Int) {
        bottomNavigationView.selectedItemId = selectedItemId

        bottomNavigationView.setOnItemSelectedListener { item ->
            if (item.itemId != selectedItemId) {
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
        startActivity(Intent(this, destino))
        overridePendingTransition(0, 0)
        finish()
    }
}
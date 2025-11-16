package view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.aplicacion_infosport.R
import com.example.aplicacion_infosport.databinding.ActivityMainBinding
import view.fragment.InicioFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Configurar el NavController
        val navHostFragment = supportFragmentManager
            .findFragmentById(binding.navHostFragment.id) as NavHostFragment
        navController = navHostFragment.navController

        // 2. Configurar el BottomNav
        binding.bottomNavigation.setupWithNavController(navController)

        // 3. Configurar la Toolbar para que muestre el título
        // Define las pestañas principales (para que no muestre la flecha "atrás")
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_inicio, R.id.nav_competiciones, R.id.nav_noticias, R.id.nav_favoritos
            )
        )
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)
    }
}
package view

import android.os.Bundle
import android.util.Log
import com.example.aplicacion_infosport.R
import com.example.aplicacion_infosport.databinding.ActivityAcercaDeBinding

class AcercaDeActivity : BaseActivity() {
    
    private lateinit var binding: ActivityAcercaDeBinding
    private val TAG = "AcercaDeActivity"
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: Iniciando AcercaDeActivity")
        
        binding = ActivityAcercaDeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        // Configurar bottom navigation (sin selección activa ya que es una pantalla especial)
        setupBottomNavigation(binding.bottomNavigation, -1)
        
        Log.d(TAG, "onCreate: AcercaDeActivity inicializada correctamente")
    }
    
    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: AcercaDeActivity visible")
    }
}
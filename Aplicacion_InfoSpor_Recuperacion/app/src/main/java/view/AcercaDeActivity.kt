package view

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.aplicacion_infosport.R
import com.example.aplicacion_infosport.databinding.ActivityAcercaDeBinding

class AcercaDeActivity : AppCompatActivity() {
    
    private val TAG = "AcercaDeActivity"
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: Iniciando AcercaDeActivity")
        
        val binding = ActivityAcercaDeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setNavigationOnClickListener { 
            Log.d(TAG, "Navegación atrás presionada")
            finish() 
        }
        
        // Mostrar mensaje de bienvenida
        Toast.makeText(this, getString(R.string.acerca_de_titulo), Toast.LENGTH_SHORT).show()
        
        Log.d(TAG, "onCreate: AcercaDeActivity inicializada correctamente")
    }
    
    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: AcercaDeActivity visible")
    }
}
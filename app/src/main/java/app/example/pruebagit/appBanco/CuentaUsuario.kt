package app.example.pruebagit.appBanco

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import app.example.pruebagit.R
import app.example.pruebagit.appBanco.data.Usuario
import app.example.pruebagit.databinding.ActivityCuentaUsuarioBinding
import app.example.pruebagit.databinding.ActivityRegistrarUsuarioBinding
import com.bumptech.glide.Glide

class CuentaUsuario : AppCompatActivity() {
    private lateinit var binding: ActivityCuentaUsuarioBinding


    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCuentaUsuarioBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
//123
        val usuarioActual = intent.getParcelableExtra<Usuario>("user")
        usuarioActual?.let { newUser ->
            binding.title.setText(newUser.nombre)
            binding.tvSaldoUsuario.setText(newUser.saldo.toString())
        }


    }


}
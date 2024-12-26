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
import androidx.core.net.toUri
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import app.example.pruebagit.R
import app.example.pruebagit.appBanco.data.Banco
import app.example.pruebagit.appBanco.data.Data
import app.example.pruebagit.appBanco.data.Usuario
import app.example.pruebagit.databinding.ActivityCuentaUsuarioBinding
import app.example.pruebagit.databinding.ActivityRegistrarUsuarioBinding
import com.bumptech.glide.Glide

class CuentaUsuario : AppCompatActivity() {
    private lateinit var binding: ActivityCuentaUsuarioBinding
    private var usuarioActual: Usuario? = null
    private val data by lazy { Banco(this) }
    private var user :Usuario?=null

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
        getInformationUser()
        binding.btnBuscarUsuario.setOnClickListener {
            searchUser()
        }
        binding.btnTranfesrir.setOnClickListener {
            transferirDinero()
        }
        binding.imageSalir.setOnClickListener {
            startActivity(Intent(this,IniciarSesion::class.java))
            finish()
        }
    }

    private fun transferirDinero() {
        val dinero=binding.etCantidadDepositar.text.toString().trim()
        if(!dinero.isNullOrEmpty()){
          usuarioActual=  data.retirar(dinero.toFloat(),usuarioActual!!)
            binding.tvSaldoUsuario.setText(usuarioActual?.saldo.toString())
            data.transferir(dinero.toFloat(),user!!)
        }
    }

    private fun searchUser() {
        val cedula = binding.etUsuarioEncontrado.text.toString().trim()
        user = data.buscarUsuario(cedula)
        println("usuario $user")
        user?.let {
            Glide.with(this).load(it.imagen.toUri()).into(binding.imagenUsuarioBuscado)
            binding.tvNombreUsuarioB.setText(it.nombre)
        }
    }

    private fun getInformationUser() {
        usuarioActual = intent.getParcelableExtra<Usuario>("user")
        usuarioActual?.let { newUser ->
            binding.title.setText(newUser.nombre)
            binding.tvSaldoUsuario.setText(newUser.saldo.toString())
            Glide.with(this).load(newUser.imagen.toUri()).into(binding.imageViewC)
        }
    }


}
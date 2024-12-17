package app.example.pruebagit.appBanco

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import app.example.pruebagit.R
import app.example.pruebagit.appBanco.data.Data
import app.example.pruebagit.appBanco.data.Usuario
import app.example.pruebagit.databinding.ActivityRegistrarUsuarioBinding
import com.bumptech.glide.Glide

class RegistrarUsuario : AppCompatActivity() {
    /*
    me permite enlazar las vistas directamente
     */
    var id =0
   private lateinit var binding: ActivityRegistrarUsuarioBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityRegistrarUsuarioBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
       binding.btnRegistrar.setOnClickListener {
           registarUsuario()
       }
        binding.imageCambiar.setOnClickListener { selectImage() }
    }

    private  fun registarUsuario(){
        val nombre=binding.nombreUsuario.text.toString().trim()
        val pass=binding.passwordUsuario.text.toString().trim()
        val saldo=binding.saldoUsuario.text.toString().trim()
        val cedula=binding.numeroCedula.text.toString().trim()
        val imagen =""

        val newUser= Usuario(id++,cedula,nombre, saldo.toFloat() ,"",pass,imagen)
        irNuevaPantalla(newUser)
        Data.data.add(newUser)
        Toast.makeText(this, "usuario registrado", Toast.LENGTH_SHORT).show()

    }

    private fun  irNuevaPantalla(usuario:Usuario){
        val intent =Intent(this,CuentaUsuario::class.java)
        intent.putExtra("user",usuario)
        startActivity(intent)
    }


    private fun selectImage(){
        val intent=Intent(Intent.ACTION_GET_CONTENT)
        intent.type="image/*"
        register.launch(intent)
    }
    val register =registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if(it.resultCode ==Activity.RESULT_OK){
            val intent=it.data
            val uri =intent?.data
            Glide.with(this).load(uri).into(binding.image)
        }
    }

}
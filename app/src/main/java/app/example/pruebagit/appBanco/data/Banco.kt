package app.example.pruebagit.appBanco.data

import android.content.Context

class Banco(val context: Context):Operaciones { //7.5
    val data= Data(context)

    override fun buscarUsuario(numero: String) :Usuario?{
       return  data.getUser(numero)
    }

    override fun retirar(saldo: Float, usuario: Usuario) {
        TODO("Not yet implemented")
    }

    override fun transferir(saldo: Float, usuario: Usuario) {
        TODO("Not yet implemented")
    }

}
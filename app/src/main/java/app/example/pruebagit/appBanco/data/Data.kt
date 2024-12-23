package app.example.pruebagit.appBanco.data

import android.content.Context
import com.google.gson.Gson

class Data(val context: Context) {

    private val sharedPreferences = context.getSharedPreferences("user", Context.MODE_PRIVATE)
    private val editor = sharedPreferences.edit()

    private val gson = Gson()

    fun saveUser(usuario: Usuario) {
        val user = gson.toJson(usuario)
        editor.putString(usuario.numeroCedula, user)
        editor.apply()
    }

    fun getUser(cedula: String): Usuario? {
        val user = sharedPreferences.getString(cedula, null)
        val newUser = gson.fromJson(user, Usuario::class.java)
        return newUser
    }



}
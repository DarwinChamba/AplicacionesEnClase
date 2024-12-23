package app.example.pruebagit.appBanco.data

interface Operaciones {
    fun buscarUsuario(numero:String):Usuario?
    fun retirar(saldo:Float,usuario: Usuario)
    fun transferir(saldo: Float,usuario: Usuario)
}
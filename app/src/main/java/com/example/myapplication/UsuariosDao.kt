package com.example.myapplication

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UsuariosDao {
    @Insert
    fun cargarUsuario(usuario:Usuarios)

    @Query("SELECT * FROM tabla_usuarios WHERE email = :email")
    fun buscarUsuarioPorEmail(email: String): Usuarios?
}
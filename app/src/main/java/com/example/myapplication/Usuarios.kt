package com.example.myapplication

import android.provider.ContactsContract.CommonDataKinds.Email
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "tabla_usuarios")
class Usuarios (
    @ColumnInfo(name = "nombre")var nombre: String,
    @ColumnInfo(name = "apellido")var apellido:String,
    @ColumnInfo(name = "email")var email:String,
    @ColumnInfo(name = "clave")var clave:String
){
    @PrimaryKey(autoGenerate = true) var id:Int = 0
}
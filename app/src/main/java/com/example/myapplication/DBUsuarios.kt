package com.example.myapplication

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Usuarios::class], version = 1)
 abstract class DBUsuarios: RoomDatabase() {
    abstract fun usuariosDao(): UsuariosDao

    companion object{

        private var INSTANCIA: DBUsuarios?=null

        fun getDatabase(contexto: Context): DBUsuarios{
            if(INSTANCIA ==null){
                synchronized(this){
                    INSTANCIA=Room.databaseBuilder(
                        contexto,DBUsuarios::class.java, "base_de_alumnos")
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build()
                }
            }
            return INSTANCIA!!
        }
    }
}
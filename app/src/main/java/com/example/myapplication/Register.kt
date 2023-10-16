package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val btnYaRegistrado = findViewById<Button>(R.id.btnPoseeUsuario);
        val btnRegistrar = findViewById<Button>(R.id.btnRegistrar);

        btnYaRegistrado.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java);
            startActivity(intent);
        }

        btnRegistrar.setOnClickListener {
            if (findViewById<EditText>(R.id.etNombre).text.toString().isEmpty() ||
                findViewById<EditText>(R.id.etApellido).text.toString().isEmpty() ||
                findViewById<EditText>(R.id.etEmail).text.toString().isEmpty() ||
                findViewById<EditText>(R.id.etClave).text.toString().isEmpty() ||
                findViewById<EditText>(R.id.etRepetirClave).text.toString().isEmpty()
            ){
                Toast.makeText(this, "Debe Completar todos los campos", Toast.LENGTH_SHORT).show()
            }else {
                /*Toast.makeText(this, findViewById<EditText>(R.id.etClave).text.toString()
                       + " - " + findViewById<EditText>(R.id.etRepetirClave).text.toString(), Toast.LENGTH_SHORT).show()*/

                if (findViewById<EditText>(R.id.etClave).text.toString().equals(
                    findViewById<EditText>(R.id.etRepetirClave).text.toString() ) ){
                    var nuevoUsuario = Usuarios(
                        findViewById<EditText>(R.id.etNombre).text.toString(),
                        findViewById<EditText>(R.id.etApellido).text.toString(),
                        findViewById<EditText>(R.id.etEmail).text.toString(),
                        findViewById<EditText>(R.id.etClave).text.toString())

                    DBUsuarios.getDatabase(this).usuariosDao().cargarUsuario(nuevoUsuario)
                    Toast.makeText(this, "Registro Exitoso", Toast.LENGTH_SHORT).show()
                    Thread.sleep(5*1000);
                    val intent = Intent(this, MainActivity::class.java);
                    startActivity(intent);
                }else{
                    Toast.makeText(this, "Las claves ingresadas no coinciden", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

package com.example.myapplication
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.Toolbar



class PantallaPrincipal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_principal)

        lateinit var toolbar: Toolbar
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val btnCharacters = findViewById<Button>(R.id.btnCharacters);
        val btnCerrarSesion = findViewById<Button>(R.id.btnCerrarSesion);

        btnCharacters.setOnClickListener {
            val intent = Intent(this, activityCharacter::class.java)
            startActivity(intent);
        }
        btnCerrarSesion.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java);
            startActivity(intent);
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.option1){
            Toast.makeText(this, "presed1", Toast.LENGTH_SHORT).show()
        }
        if(item.itemId == R.id.option2){
            Toast.makeText(this, "presed2", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }





}
package com.example.myapplication
import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Build
import android.widget.CheckBox
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var emailEditText: EditText
    private lateinit var claveEditText: EditText
    private lateinit var dbUsuarios: DBUsuarios
    private lateinit var binding: ActivityMainBinding
    private val canalNombre = "Notificacion"
    private val canalId = "canalId"
    private val notificacionId = 0


    override fun onCreate(savedInstanceState: Bundle?) {


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreferences = getSharedPreferences("Recordar_Usuario", Context.MODE_PRIVATE)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        emailEditText =findViewById(R.id.txtCorreoElectronico)
        claveEditText =findViewById(R.id.txtClave)
        dbUsuarios=DBUsuarios.getDatabase(this)


        var btnCrearCuenta = findViewById<Button>(R.id.btnCrearCuenta)
        var btnIniciarSesion = findViewById<Button>(R.id.btnInciarSesion)
        val checkBoxRecordarUsuario = findViewById<CheckBox>(R.id.cbRecordarUsuario)

        val recordarUsuario = sharedPreferences.getBoolean("recordar_usuario", false)
        checkBoxRecordarUsuario.isChecked = recordarUsuario
        if (recordarUsuario && sharedPreferences.getString("Usuario","") !== ""){
            val usuario = sharedPreferences.getString("Usuario","")
            val clave = sharedPreferences.getString("Clave","")
            findViewById<EditText>(R.id.txtCorreoElectronico).setText(usuario.toString())
            findViewById<EditText>(R.id.txtClave).setText(clave.toString())
        }

        checkBoxRecordarUsuario.setOnCheckedChangeListener {buttonView, isChecked ->

            val recordarUsuario = if (isChecked) "Se Activó Recordar Usuario" else "Se Desactivó Recordar Usuario"

            crearNotificacionCanal()
            crearNotificacion(recordarUsuario)

        }

        btnCrearCuenta.setOnClickListener{
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }



        btnIniciarSesion.setOnClickListener {
            val email= emailEditText.text.toString()
            val pass= claveEditText.text.toString()

            if (usuarioEsCorrecto(email,pass)) {
                val intent= Intent(this, PantallaPrincipal::class.java)
                val editor = sharedPreferences.edit()
                editor.putBoolean("recordar_usuario", checkBoxRecordarUsuario.isChecked)
                editor.putString("Usuario", emailEditText.text.toString())
                editor.putString("Clave" , claveEditText.text.toString())
                editor.apply()
                startActivity(intent);
                finish()
            } else {
                Toast.makeText(this, "Clave y/o Contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun usuarioEsCorrecto(email:String,clave:String):Boolean{
        val usuarioDao = dbUsuarios.usuariosDao()
        val usuario = usuarioDao.buscarUsuarioPorEmail(email)
        return usuario!= null && usuario.clave == clave
    }

    private fun crearNotificacionCanal(){
        if (Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O){
            val canalImportancia = NotificationManager.IMPORTANCE_HIGH
            val canal = NotificationChannel(canalId,canalNombre,canalImportancia)

            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(canal)
        }
    }

    private fun crearNotificacion(text:String){
        val notificacion = NotificationCompat.Builder(this, canalId).also {
            it.setContentTitle("Notificacion")
            it.setContentText(text)
            it.setSmallIcon(R.drawable.icon_message)
            it.priority = NotificationCompat.PRIORITY_HIGH
        }.build()

        val notificationManager = NotificationManagerCompat.from(this)
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        notificationManager.notify(notificacionId,notificacion)



    }

}



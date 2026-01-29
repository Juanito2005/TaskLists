package com.example.tasklists

import android.os.Bundle
import android.view.textservice.TextInfo
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 1. Referencias a las vistas
        val inputTarea = findViewById<EditText>(R.id.et_tarea)
        val botonAnadir = findViewById<Button>(R.id.btn_anadir)
        val vistaLista = findViewById<ListView>(R.id.lv_lista_tareas)

        val listaTareas = ArrayList<String>()

        val adaptador = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaTareas)

        // 3. ¡VITAL! Conectar la vista con el adaptador
        vistaLista.adapter = adaptador

        // 4. El Click
        botonAnadir.setOnClickListener {
            val texto = inputTarea.text.toString()

            if (texto.isNotEmpty()) {
                listaTareas.add(texto)
                
                adaptador.notifyDataSetChanged()

                inputTarea.text.clear()
            } else {
                android.widget.Toast.makeText(this, "Escribe algo primero", android.widget.Toast.LENGTH_SHORT).show()
            }
        }
    }
}

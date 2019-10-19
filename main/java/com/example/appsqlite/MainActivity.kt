package com.example.appsqlite

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn1.setOnClickListener {
            val admin = AdminSQLiteOpenHelper(this,"administracion", null, 1)
            val bd = admin.writableDatabase
            val registro = ContentValues()
            registro.put("codigo", et1.getText().toString())
            registro.put("descripcion", et2.getText().toString())
            bd.insert("articulos", null, registro)
            bd.close()
            et1.setText("")
            et2.setText("")
            Toast.makeText(this, "Se cargaron los datos del artículo", Toast.LENGTH_SHORT).show()
        }

        btn2.setOnClickListener {
            val admin = AdminSQLiteOpenHelper(this, "administracion", null, 1)
            val bd = admin.writableDatabase
            val fila = bd.rawQuery("select descripcion from articulos where codigo=${et1.text.toString()}", null)
            if (fila.moveToFirst()) {
                txt1.setText(et1.text.toString())
                txt2.setText(fila.getString(0))
            } else
                Toast.makeText(this, "No existe un artículo con dicho código",  Toast.LENGTH_SHORT).show()
            bd.close()
        }
    }
}

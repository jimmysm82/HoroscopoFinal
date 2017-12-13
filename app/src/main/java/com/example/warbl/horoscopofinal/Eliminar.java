package com.example.warbl.horoscopofinal;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Eliminar extends AppCompatActivity {

    TextView eliminar;
    Button btnEliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar);

        eliminar = findViewById(R.id.eliminarUsuario);
        btnEliminar = findViewById(R.id.btnEliminar);

        Bundle b = Eliminar.this.getIntent().getExtras();
        final String correo = b.getString("Correo");

        eliminar.setText(correo);

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    BaseHelper bh = new BaseHelper(Eliminar.this, "usuarios", null, 1);
                    SQLiteDatabase db = bh.getWritableDatabase();
                    db.execSQL("delete from usuarios where correo = +correo");
                    Toast.makeText(Eliminar.this, "Cuenta Eliminada", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Eliminar.this, Login.class);
                    startActivity(i);

                }catch (Exception e){

                    Toast.makeText(Eliminar.this, "Error :"+e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}

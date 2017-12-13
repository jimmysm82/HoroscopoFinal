package com.example.warbl.horoscopofinal;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.UnicodeSetSpanner;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Registro extends AppCompatActivity {

    EditText etcorreo, etnombre, etapellido, etpassword;
    TextView volver;
    Button btnRegistrarse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        etcorreo = findViewById(R.id.etCorreo);
        etnombre = findViewById(R.id.etNombre);
        etapellido = findViewById(R.id.etApellido);
        etpassword = findViewById(R.id.etPass);
        volver = findViewById(R.id.txt_volver);
        btnRegistrarse = findViewById(R.id.btn_Registrar);

        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Registro.this, Login.class);
                startActivity(i);
            }
        });
    }

        public void Agregar(View v){
            if(comprobar()){
                String correo = etcorreo.getText().toString();
                String nombre = etnombre.getText().toString();
                String apellido = etapellido.getText().toString();
                String password = etpassword.getText().toString();

                BaseHelper bh = new BaseHelper(Registro.this, "usuarios", null, 1);
                SQLiteDatabase db = bh.getWritableDatabase();
                ContentValues cv = new ContentValues();
                cv.put("correo", correo);
                cv.put("nombre", nombre);
                cv.put("apellido", apellido);
                cv.put("password", password);
                long insert = db.insert("usuarios", null, cv);

                if(insert > 0){
                    Toast.makeText(Registro.this, "Usuario Agregado con Exito", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Registro.this, Login.class);
                    startActivity(i);
                }else{
                    Toast.makeText(Registro.this, "Error al Agregar Usuario", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(Registro.this, "Hay campos vacios", Toast.LENGTH_SHORT).show();
            }
    }

    public boolean comprobar(){
            if(etcorreo.getText().toString().isEmpty() || etnombre.getText().toString().isEmpty() || etapellido.getText().toString().isEmpty() || etpassword.getText().toString().isEmpty()){
                return false;
            }else{
                return true;
            }
    }


}

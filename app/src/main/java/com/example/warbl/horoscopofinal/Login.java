package com.example.warbl.horoscopofinal;

import android.content.ContentValues;
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

public class Login extends AppCompatActivity {

    Button btnLogin;
    EditText etcorreo, etpassword;
    TextView registrarse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin = findViewById(R.id.btn_login);
        etcorreo = findViewById(R.id.correo_usuario);
        etpassword = findViewById(R.id.password_usuario);
        registrarse = findViewById(R.id.txt_registrarse);

        registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, Registro.class);
                startActivity(i);
            }
        });
    }

    public boolean comprobarCampos() {
        String correo = etcorreo.getText().toString();
        String password = etpassword.getText().toString();
        if (correo.isEmpty() || password.isEmpty()) {
            Toast.makeText(Login.this, "Hay campos vacios", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    public void comprobarUserPass(View v) {
        String correo = etcorreo.getText().toString();
        String password = etpassword.getText().toString();
        String correoad = "admin@horoscopo.cl";
        String nombread = "Jimmy";
        String apellidoad = "Sanchez";
        String passwordad = "123456";

        comprobarCampos();

        if (correo.equals(correoad) && password.equals(passwordad)) {

            Intent i = new Intent(Login.this, Admin.class);
            i.putExtra("Correo", correoad);
            i.putExtra("Nombre", nombread);
            i.putExtra("Apellido", apellidoad);
            i.putExtra("Password", passwordad);
            startActivity(i);

        }

        if (correo.length() > 0 && password.length() > 0) {

            BaseHelper bh = new BaseHelper(Login.this, "usuarios", null, 1);
            SQLiteDatabase db = bh.getReadableDatabase();
            Cursor c = db.rawQuery("select * from usuarios", null);

            if (c.moveToFirst()) {
                do {
                    String dbcorreo = c.getString(0);
                    String dbnombre = c.getString(1);
                    String dbapellido = c.getString(2);
                    String dbpassword = c.getString(3);

                    if (correo.equals(dbcorreo) && password.equals(dbpassword)) {
                        Intent i = new Intent(Login.this, Home.class);
                        i.putExtra("Correo", dbcorreo);
                        i.putExtra("Nombre", dbnombre);
                        i.putExtra("Apellido", dbapellido);
                        i.putExtra("Password", dbpassword);
                        startActivity(i);
                        break;

                    } else if (correo.equals(dbcorreo) && !password.equals(dbpassword)) {

                        Toast.makeText(Login.this, "Contraseña Incorrecta", Toast.LENGTH_SHORT).show();
                    }


                } while (c.moveToNext());

            } else {

                Toast.makeText(Login.this, "No hay Datos", Toast.LENGTH_SHORT).show();
            }

        }
    }
}





















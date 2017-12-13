package com.example.warbl.horoscopofinal;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Capricornio extends AppCompatActivity {

    TextView volver, fechaN, txtamor, txtsalud, txtdinero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capricornio);

        volver = findViewById(R.id.btn_volver);
        fechaN = findViewById(R.id.fechaNacimiento);
        txtamor = findViewById(R.id.texto_amor);
        txtdinero = findViewById(R.id.texto_dinero);
        txtsalud = findViewById(R.id.texto_salud);

        Bundle b = Capricornio.this.getIntent().getExtras();

        String fechaNacimiento = b.getString("fechaN");
        final String nombre = b.getString("Nombre");
        final String apellido = b.getString("Apellido");

        String fn = ""+nombre+" "+apellido+", nacido/a el "+fechaNacimiento+", corresponde al signo de Capricornio";

        fechaN.setText(fn);

        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Capricornio.this, Home.class);
                i.putExtra("Nombre", nombre);
                i.putExtra("Apellido", apellido);
                startActivity(i);
            }
        });

        BaseHelper bh = new BaseHelper(Capricornio.this, "prediccion", null, 1);
        SQLiteDatabase db = bh.getReadableDatabase();
        Cursor c = db.rawQuery("select * from prediccion", null);

        try {
            if (c.moveToFirst()) {
                do {
                    String presigno = c.getString(0);
                    String preamor = c.getString(1);
                    String presalud = c.getString(2);
                    String predinero = c.getString(3);

                    if (presigno.equals("Capricornio")) {

                        txtamor.setText(preamor);
                        txtsalud.setText(presalud);
                        txtdinero.setText(predinero);

                    }

                } while (c.moveToNext());

            } else {

                Toast.makeText(Capricornio.this, "No hay datos", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){

            Toast.makeText(Capricornio.this, "Error :"+e.getMessage(), Toast.LENGTH_LONG).show();

        } finally {

            db.close();

        }
    }
}

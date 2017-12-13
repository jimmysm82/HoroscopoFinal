package com.example.warbl.horoscopofinal;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Admin extends AppCompatActivity {

    RadioGroup btnGroup;
    RadioButton insertar, modificar, eliminar, listar;
    Spinner horoscopo;
    EditText amor, salud, dinero;
    Button ejecutar;
    TextView btnSalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        btnGroup = findViewById(R.id.btnGroup);
        insertar = findViewById(R.id.btnInsertar);
        modificar = findViewById(R.id.btnModificar);
        eliminar = findViewById(R.id.btnEliminar);
        listar = findViewById(R.id.btnListar);
        amor = findViewById(R.id.amor);
        dinero = findViewById(R.id.dinero);
        salud = findViewById(R.id.salud);
        horoscopo = findViewById(R.id.spinner_zodiaco);
        ejecutar = findViewById(R.id.btnEjecutar);
        btnSalir = findViewById(R.id.btnSalir);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(Admin.this, R.array.zodiaco, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        horoscopo.setAdapter(adapter);

        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Admin.this, Login.class);
                startActivity(i);
            }
        });

        ejecutar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String dbamor = amor.getText().toString();
                String dbsalud = salud.getText().toString();
                String dbdinero = dinero.getText().toString();
                String dbhoroscopo = horoscopo.getSelectedItem().toString();

                final BaseHelper bh = new BaseHelper(Admin.this, "prediccion", null, 1);
                final SQLiteDatabase db = bh.getWritableDatabase();

                if (insertar.isChecked()) {
                    //BaseHelper bh = new BaseHelper(Admin.this, "prediccion", null, 1);
                    //SQLiteDatabase db = bh.getWritableDatabase();
                    ContentValues cv = new ContentValues();
                    cv.put("signo", dbhoroscopo);
                    cv.put("amor", dbamor);
                    cv.put("salud", dbsalud);
                    cv.put("dinero", dbdinero);
                    long insert = db.insert("prediccion", null, cv);

                    if (insert > 0) {

                        Toast.makeText(Admin.this, "Datos Agregados Correctamente", Toast.LENGTH_SHORT).show();
                        borrar();
                        db.close();

                    } else {

                        Toast.makeText(Admin.this, "Ya existe " + dbhoroscopo + " en la base de datos", Toast.LENGTH_SHORT).show();
                        borrar();
                        db.close();

                    }

                } else if (modificar.isChecked()) {
                    //BaseHelper bh = new BaseHelper(Admin.this, "prediccion", null, 1);
                    //SQLiteDatabase db = bh.getWritableDatabase();
                    ContentValues cv = new ContentValues();
                    cv.put("amor", dbamor);
                    cv.put("salud", dbsalud);
                    cv.put("dinero", dbdinero);
                    long update = db.update("prediccion", cv, "signo" + "=?", new String[]{dbhoroscopo});

                    if (update > 0) {

                        borrar();
                        Toast.makeText(Admin.this, "Datos Modificados Correctamente", Toast.LENGTH_SHORT).show();
                        db.close();


                    } else {

                        borrar();
                        Toast.makeText(Admin.this, "Error al Modificar los Datos", Toast.LENGTH_SHORT).show();
                        db.close();

                    }

                } else if (eliminar.isChecked()) {

                    //BaseHelper bh = new BaseHelper(Admin.this, "prediccion", null, 1);
                    //SQLiteDatabase db = bh.getWritableDatabase();
                    long delete = db.delete("prediccion", "signo" + "=?", new String[]{dbhoroscopo});

                    if (delete > 0) {

                        borrar();
                        Toast.makeText(Admin.this, "Datos Eliminados Correctamente", Toast.LENGTH_SHORT).show();
                        db.close();


                    } else {

                        borrar();
                        Toast.makeText(Admin.this, "Error al Borrar los Datos", Toast.LENGTH_SHORT).show();
                        db.close();

                    }

                } else if (listar.isChecked()) {

                        //BaseHelper bh = new BaseHelper(Admin.this, "prediccion", null, 1);
                        SQLiteDatabase dbr = bh.getReadableDatabase();
                        Cursor c = db.rawQuery("select * from prediccion", null);
                    try {
                        if (c.moveToFirst()) {
                            do {
                                String presigno = c.getString(0);
                                String preamor = c.getString(1);
                                String presalud = c.getString(2);
                                String predinero = c.getString(3);

                                if (dbhoroscopo.equals(presigno)) {

                                    amor.setText(preamor);
                                    salud.setText(presalud);
                                    dinero.setText(predinero);
                                    dbr.close();

                                } else {

                                    Toast.makeText(Admin.this, "No hay datos", Toast.LENGTH_SHORT).show();
                                    borrar();
                                    dbr.close();

                                }

                            } while (c.moveToNext());

                        } else {
                            Toast.makeText(Admin.this, "No hay datos", Toast.LENGTH_SHORT).show();
                        }


                    }catch (Exception e){

                        Toast.makeText(Admin.this, "Error :"+e.getMessage(), Toast.LENGTH_LONG).show();

                    } finally {

                        dbr.close();

                    }


                }else{
                    Toast.makeText(Admin.this, "Error", Toast.LENGTH_SHORT).show();
                }



            }
        });
    }

    public void borrar(){
        amor.setText("");
        salud.setText("");
        dinero.setText("");
    }
}

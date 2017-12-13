package com.example.warbl.horoscopofinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Home extends AppCompatActivity {

    TextView tvNombreApellido, darseBaja;
    Spinner dias, mes, anio;
    Button btnPrediccion, btnSalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        tvNombreApellido = findViewById(R.id.txtNombreApellido);
        btnPrediccion = findViewById(R.id.btnPrediccion);
        btnSalir = findViewById(R.id.btnSalir);
        darseBaja = findViewById(R.id.DarseBaja);

        Bundle b = Home.this.getIntent().getExtras();
        final String nombre = b.getString("Nombre");
        final String apellido = b.getString("Apellido");
        final String correo = b.getString("Correo");

        tvNombreApellido.setText(nombre+" "+apellido);

        darseBaja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, Eliminar.class);
                i.putExtra("Correo", correo);
                startActivity(i);
            }
        });

        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, Login.class);
                startActivity(i);
            }
        });

        ArrayList<Integer> spdias = new ArrayList<>();
        for (int i = 1; i <=31; i++){
            spdias.add(i);
        }

        ArrayList<Integer> spmes = new ArrayList<>();
        for (int i = 1; i <=12; i++){
            spmes.add(i);
        }

        ArrayList<Integer> spanio = new ArrayList<>();
        for (int i = 2018; i >=1900; i--){
            spanio.add(i);
        }

        dias = findViewById(R.id.spinner_dias);
        mes = findViewById(R.id.spinner_mes);
        anio = findViewById(R.id.spinner_anio);

        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(Home.this, android.R.layout.simple_spinner_item, spdias);
        dias.setAdapter(adapter);

        ArrayAdapter<CharSequence> adapter1 = new ArrayAdapter(Home.this, android.R.layout.simple_spinner_item, spmes);
        mes.setAdapter(adapter1);

        ArrayAdapter<CharSequence> adapter2 = new ArrayAdapter(Home.this, android.R.layout.simple_spinner_item, spanio);
        anio.setAdapter(adapter2);

        btnPrediccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int dia = dias.getSelectedItemPosition()+1;
                int mess = mes.getSelectedItemPosition()+1;
                int anioo = Integer.parseInt(anio.getSelectedItem().toString());

                String FechaNacimiento = dia+"/"+mess+"/"+anioo;

                if(mess == 2 && dia == 30 || dia == 31 ) {
                    Toast.makeText(Home.this, "fecha no valida", Toast.LENGTH_SHORT).show();
                }else if(mess == 4 && dia == 31) {
                    Toast.makeText(Home.this, "fecha no valida", Toast.LENGTH_SHORT).show();
                }else if(mess == 6 && dia == 31) {
                    Toast.makeText(Home.this, "fecha no valida", Toast.LENGTH_SHORT).show();
                }else if(mess == 9 && dia == 31) {
                    Toast.makeText(Home.this, "fecha no valida", Toast.LENGTH_SHORT).show();
                }else if(mess == 11 && dia == 31){
                    Toast.makeText(Home.this, "fecha no valida", Toast.LENGTH_SHORT).show();
                }else {

                    switch (mess) {

                        case 1: //Enero
                            if (dia > 21) {

                                Intent i = new Intent(Home.this, Acuario.class);
                                i.putExtra("fechaN", FechaNacimiento);
                                i.putExtra("Nombre", nombre);
                                i.putExtra("Apellido", apellido);
                                startActivity(i);

                            } else {

                                Intent i = new Intent(Home.this, Capricornio.class);
                                i.putExtra("fechaN", FechaNacimiento);
                                i.putExtra("Nombre", nombre);
                                i.putExtra("Apellido", apellido);
                                startActivity(i);
                            }
                            break;

                        case 2: //Febrero
                            if (dia > 19) {

                                Intent i = new Intent(Home.this, Piscis.class);
                                i.putExtra("fechaN", FechaNacimiento);
                                i.putExtra("Nombre", nombre);
                                i.putExtra("Apellido", apellido);
                                startActivity(i);

                            } else {

                                Intent i = new Intent(Home.this, Acuario.class);
                                i.putExtra("fechaN", FechaNacimiento);
                                i.putExtra("Nombre", nombre);
                                i.putExtra("Apellido", apellido);
                                startActivity(i);
                            }
                            break;

                        case 3: //Marzo
                            if (dia > 20) {

                                Intent i = new Intent(Home.this, Aries.class);
                                i.putExtra("fechaN", FechaNacimiento);
                                i.putExtra("Nombre", nombre);
                                i.putExtra("Apellido", apellido);
                                startActivity(i);

                            } else {

                                Intent i = new Intent(Home.this, Piscis.class);
                                i.putExtra("fechaN", FechaNacimiento);
                                i.putExtra("Nombre", nombre);
                                i.putExtra("Apellido", apellido);
                                startActivity(i);
                            }
                            break;

                        case 4: //Abril
                            if (dia > 20) {

                                Intent i = new Intent(Home.this, Tauro.class);
                                i.putExtra("fechaN", FechaNacimiento);
                                i.putExtra("Nombre", nombre);
                                i.putExtra("Apellido", apellido);
                                startActivity(i);

                            } else {

                                Intent i = new Intent(Home.this, Aries.class);
                                i.putExtra("fechaN", FechaNacimiento);
                                i.putExtra("Nombre", nombre);
                                i.putExtra("Apellido", apellido);
                                startActivity(i);
                            }
                            break;

                        case 5: //Mayo
                            if (dia > 21) {

                                Intent i = new Intent(Home.this, Geminis.class);
                                i.putExtra("fechaN", FechaNacimiento);
                                i.putExtra("Nombre", nombre);
                                i.putExtra("Apellido", apellido);
                                startActivity(i);

                            } else {

                                Intent i = new Intent(Home.this, Tauro.class);
                                i.putExtra("fechaN", FechaNacimiento);
                                i.putExtra("Nombre", nombre);
                                i.putExtra("Apellido", apellido);
                                startActivity(i);
                            }
                            break;

                        case 6: //Junio
                            if (dia > 20) {

                                Intent i = new Intent(Home.this, Cancer.class);
                                i.putExtra("fechaN", FechaNacimiento);
                                i.putExtra("Nombre", nombre);
                                i.putExtra("Apellido", apellido);
                                startActivity(i);

                            } else {

                                Intent i = new Intent(Home.this, Geminis.class);
                                i.putExtra("fechaN", FechaNacimiento);
                                i.putExtra("Nombre", nombre);
                                i.putExtra("Apellido", apellido);
                                startActivity(i);
                            }
                            break;

                        case 7: //Julio
                            if (dia > 22) {

                                Intent i = new Intent(Home.this, Leo.class);
                                i.putExtra("fechaN", FechaNacimiento);
                                i.putExtra("Nombre", nombre);
                                i.putExtra("Apellido", apellido);
                                startActivity(i);

                            } else {

                                Intent i = new Intent(Home.this, Cancer.class);
                                i.putExtra("fechaN", FechaNacimiento);
                                i.putExtra("Nombre", nombre);
                                i.putExtra("Apellido", apellido);
                                startActivity(i);
                            }
                            break;

                        case 8: //Agosto
                            if (dia > 21) {

                                Intent i = new Intent(Home.this, Virgo.class);
                                i.putExtra("fechaN", FechaNacimiento);
                                i.putExtra("Nombre", nombre);
                                i.putExtra("Apellido", apellido);
                                i.putExtra("Nombre", nombre);
                                i.putExtra("Apellido", apellido);
                                startActivity(i);

                            } else {

                                Intent i = new Intent(Home.this, Leo.class);
                                i.putExtra("fechaN", FechaNacimiento);
                                i.putExtra("Nombre", nombre);
                                i.putExtra("Apellido", apellido);
                                startActivity(i);
                            }
                            break;

                        case 9: //Septiembre
                            if (dia > 22) {

                                Intent i = new Intent(Home.this, Libra.class);
                                i.putExtra("fechaN", FechaNacimiento);
                                i.putExtra("Nombre", nombre);
                                i.putExtra("Apellido", apellido);
                                startActivity(i);

                            } else {

                                Intent i = new Intent(Home.this, Virgo.class);
                                i.putExtra("fechaN", FechaNacimiento);
                                i.putExtra("Nombre", nombre);
                                i.putExtra("Apellido", apellido);
                                i.putExtra("Nombre", nombre);
                                i.putExtra("Apellido", apellido);
                                startActivity(i);
                            }
                            break;

                        case 10: //Octubre
                            if (dia > 22) {

                                Intent i = new Intent(Home.this, Escorpion.class);
                                i.putExtra("fechaN", FechaNacimiento);
                                i.putExtra("Nombre", nombre);
                                i.putExtra("Apellido", apellido);
                                startActivity(i);

                            } else {

                                Intent i = new Intent(Home.this, Libra.class);
                                i.putExtra("fechaN", FechaNacimiento);
                                i.putExtra("Nombre", nombre);
                                i.putExtra("Apellido", apellido);
                                startActivity(i);
                            }
                            break;

                        case 11: //Noviembre
                            if (dia > 21) {

                                Intent i = new Intent(Home.this, Sagitario.class);
                                i.putExtra("fechaN", FechaNacimiento);
                                i.putExtra("Nombre", nombre);
                                i.putExtra("Apellido", apellido);
                                startActivity(i);

                            } else {

                                Intent i = new Intent(Home.this, Escorpion.class);
                                i.putExtra("fechaN", FechaNacimiento);
                                i.putExtra("Nombre", nombre);
                                i.putExtra("Apellido", apellido);
                                startActivity(i);
                            }
                            break;

                        case 12: //Diciembre
                            if (dia > 21) {

                                Intent i = new Intent(Home.this, Capricornio.class);
                                i.putExtra("fechaN", FechaNacimiento);
                                i.putExtra("Nombre", nombre);
                                i.putExtra("Apellido", apellido);
                                startActivity(i);

                            } else {

                                Intent i = new Intent(Home.this, Sagitario.class);
                                i.putExtra("fechaN", FechaNacimiento);
                                i.putExtra("Nombre", nombre);
                                i.putExtra("Apellido", apellido);
                                startActivity(i);
                            }
                            break;
                    }
                }
            }
        });

    }
}

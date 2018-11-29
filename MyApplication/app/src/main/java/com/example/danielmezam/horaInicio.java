package com.example.danielmezam;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class horaInicio extends AppCompatActivity {
    ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "dbProyecto", null, 1);
    public ArrayList<String> datosFinales = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hora_inicio);
        componentes();


        Bundle extras = getIntent().getExtras();
        datosFinales = extras.getStringArrayList("datos");
        horaInicio.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                String hour;
                if(minute<10){
                    hour = hourOfDay + ":0" +  minute;
                }else{
                    hour = hourOfDay + ":" +  minute;
                }

                DateFormat sdf = new SimpleDateFormat("hh:mm");

                setHoraInicio.setText(hour);
            }
        });

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Registrar();
            }
        });
    }

    private void componentes(){
        horaInicio = (TimePicker) findViewById(R.id.relogHoraInicio);
        setHoraInicio = (TextView) findViewById(R.id.horaInicioTxt);
        btnRegistrar = (Button) findViewById(R.id.btnRegistrar);
    }

    private void Registrar(){
        SQLiteDatabase db = conn.getWritableDatabase();
        String hora = setHoraInicio.getText().toString();
        ContentValues values = new ContentValues();
        datosFinales.add(13, hora);
        for(int x=0; x<datosFinales.size(); x++){
            System.out.println(datosFinales.get(x));
        }
        values.put(ConexionSQLiteHelper.id, datosFinales.get(0));
        values.put(ConexionSQLiteHelper.nombreMedicamento, datosFinales.get(1));
        values.put(ConexionSQLiteHelper.padecimiento, datosFinales.get(2));
        values.put(ConexionSQLiteHelper.dosis, datosFinales.get(3));
        values.put(ConexionSQLiteHelper.descripcionMedicamento, datosFinales.get(4));
        values.put(ConexionSQLiteHelper.doctor, datosFinales.get(5));
        values.put(ConexionSQLiteHelper.telDoctor, datosFinales.get(6));
        values.put(ConexionSQLiteHelper.horasAldia, datosFinales.get(7));
        values.put(ConexionSQLiteHelper.minutos, datosFinales.get(8));
        values.put(ConexionSQLiteHelper.imagenEnvase, datosFinales.get(9));
        values.put(ConexionSQLiteHelper.imagenMedicamento, datosFinales.get(10));
        values.put(ConexionSQLiteHelper.fechaInicio, datosFinales.get(11));
        values.put(ConexionSQLiteHelper.fechaTermino, datosFinales.get(12));
        values.put(ConexionSQLiteHelper.horaInicial, datosFinales.get(13));

        db.insert("medicamento", null, values);
        Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();
        db.close();
        Intent goToMain = new Intent(horaInicio.this, menuPrincipal.class);
        startActivity(goToMain);
    }

    TimePicker horaInicio;
    TextView setHoraInicio;
    Button btnRegistrar;
}

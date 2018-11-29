package com.example.danielmezam;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class fechaInicio extends AppCompatActivity {
    public ArrayList<String> datosFinales = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fecha_inicio);
        componentes();
        Bundle extras = getIntent().getExtras();
        datosFinales = extras.getStringArrayList("datos");

        calendarioFechaInicio.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange( CalendarView view, int year, int month, int dayOfMonth) {
                String date= "";
                if(dayOfMonth<10 && month<10){
                    date = "0" +dayOfMonth+ "/0" + (month +1) + "/" + year;
                } else if(dayOfMonth<10){
                    date = "0" +dayOfMonth+ "/" + (month +1) + "/" + year;
                } else if(month<10){
                    date = "" +dayOfMonth+ "/0" + (month +1) + "/" + year;
                } else if(dayOfMonth>9 && month> 9){
                    date =  dayOfMonth+ "/" + (month +1) + "/" + year;
                }

                fechaIni.setText(date);
            }
        });

        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                continuar();
            }
        });
    }

    private void continuar() {
        String fechaInicio = fechaIni.getText().toString();
        datosFinales.add(11,fechaInicio);
        Intent fechaTermino = new Intent(fechaInicio.this, fechaTermino.class);
        fechaTermino.putStringArrayListExtra("datos", datosFinales);
        startActivity(fechaTermino);
        //Toast.makeText(this, "" + datosFinales.get(9), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "" + fechaInicio, Toast.LENGTH_SHORT).show();
    }

    private void componentes(){
        btnContinuar = (Button) findViewById(R.id.btnFechaInicio);
        calendarioFechaInicio = (CalendarView) findViewById(R.id.calendarFechaInicio);
        fechaIni = (TextView) findViewById(R.id.fechaInic);
    }

    Button btnContinuar;
    CalendarView calendarioFechaInicio;
    TextView fechaIni;
}

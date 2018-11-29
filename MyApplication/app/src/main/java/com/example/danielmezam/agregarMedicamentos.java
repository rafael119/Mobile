package com.example.danielmezam;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class agregarMedicamentos extends AppCompatActivity {
    ArrayList<String> datosMedicamento=new ArrayList<String>();
    ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "dbProyecto", null, 1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_medicamentos);

        componentes();
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                continuar();
            }
        });
    }

    private void continuar(){
        String nombreMedicamentos = nomMedicamentoJava.getText().toString();
        String idMedicamento = idMedicamentoJava.getText().toString();
        String padecimiento = padecimientoJava.getText().toString();
        String dosis = dosisJava.getText().toString();
        String descipcion = descripcionJava.getText().toString();
        String doctor = doctorJava.getText().toString();
        String telefono =telefonoDr.getText().toString();
        String horas = horasMed.getText().toString();
        String minutos = minMed.getText().toString();

        if(validarVacio(nombreMedicamentos,padecimiento,dosis,descipcion,doctor,telefono, horas, minutos) && validarHorasMin(horas, minutos) && validarID()) {
            datosMedicamento.add(idMedicamento);
            datosMedicamento.add(nombreMedicamentos);
            datosMedicamento.add(padecimiento);
            datosMedicamento.add(dosis);
            datosMedicamento.add(descipcion);
            datosMedicamento.add(doctor);
            datosMedicamento.add(telefono);
            datosMedicamento.add(horas);
            datosMedicamento.add(minutos);
            Intent goToFotos = new Intent(agregarMedicamentos.this, agregarFotos.class);
            goToFotos.putStringArrayListExtra("datos", datosMedicamento);
            startActivity(goToFotos);
        } else{
            Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
        }

    }

    private boolean validarID() {
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "dbProyecto", null, 1);
        SQLiteDatabase db = conn.getReadableDatabase();
        Toast.makeText(this, "" + idMedicamentoJava.getText().toString(), Toast.LENGTH_SHORT).show();
        String consulta = "SELECT * FROM medicamento where idMedicamento = '" + idMedicamentoJava.getText().toString()+"'";
        Cursor c = db.rawQuery(consulta ,null);
        if(c.moveToFirst()){
            c.close();
            Toast.makeText(this, "Ya existe registro de medicamento con este ID", Toast.LENGTH_SHORT).show();
            idMedicamentoJava.setText(null);
            return false;
        }
        c.close();
        return true;
    }

    private boolean validarHorasMin(String horas, String minutos) {
        int hr, min;
        hr = Integer.parseInt(horas);
        min = Integer.parseInt(minutos);
        if(!(hr>24) && !(min>59)){
            return true;
        } else {
            Toast.makeText(this, "Checa los valores para recordar c:", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private boolean validarVacio(String nombreMedicamentos, String padecimiento, String dosis, String descipcion, String doctor, String telefono, String horas, String minutos) {
        if(!nombreMedicamentos.isEmpty() && !padecimiento.isEmpty() && !dosis.isEmpty() && !descipcion.isEmpty() && !doctor.isEmpty() &&  !telefono.isEmpty() && !horas.isEmpty() && !minutos.isEmpty() ){
            return true;
        } else {
            Toast.makeText(this, "Deben llenar todos los campos", Toast.LENGTH_SHORT).show();
            return false;
        }
    }


   /* private void guardarDatos() {
        conn = new conexionSQLHelper(this,"dbProyecto",null, 1);
        db = conn.getWritableDatabase();
        Se insertan los valores en la tabla :D





        if (validarVacio(nombreMedicamentos, padecimiento, dosis, descipcion, doctor, fechaInicio, fechaFinal, hora)) {

            ContentValues values = new ContentValues();
            values.put(Utilidades.nombreMedicamento, nombreMedicamentos);
            values.put(Utilidades.padecimiento, padecimiento);
            values.put(Utilidades.dosis, dosis);
        */
         //---------------------------------------
            //values.put(Utilidades.imagenEnvase, imgToByte(imgEnvase));
            //
            //
            // values.put(Utilidades.idMedicamento,imgToByte(imgMedicamento));


        /*    values.put(Utilidades.descripcionMedicamento, descipcion);
            values.put(Utilidades.doctor, doctor);
            values.put(Utilidades.horasAldia, Integer.parseInt(horas));
            values.put(Utilidades.fechaInicio, fechaInicio);
            values.put(Utilidades.fechaTermino, fechaFinal);
            values.put(Utilidades.horaInicial, hora);

            db.insert(Utilidades.tablaMedicamento, null, values);

            nomMedicamentoJava.setText(null);
            padecimientoJava.setText(null);
            dosisJava.setText(null);
            descripcionJava.setText(null);
            doctorJava.setText(null);
            horasJava.setText(null);
            setFechaIni.setText(null);
            setFechaTer.setText(null);
            setHora.setText(null);
         */   //-------------------------------------------------------------
            //imgEnvase.setImageBitmap(null);
            //imgMedicamento.setImageBitmap(null);

           /* Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();
            db.close();
        } else {
            //Toast.makeText(this, "Error ", Toast.LENGTH_SHORT).show();
        }

    }*/
    private void componentes(){
        nomMedicamentoJava = (EditText) findViewById(R.id.nombreMedicamento);
        idMedicamentoJava = (EditText) findViewById(R.id.idMedicamento); 
        padecimientoJava = (EditText) findViewById(R.id.padecimiento);
        dosisJava = (EditText) findViewById(R.id.dosis);
        descripcionJava = (EditText) findViewById(R.id.descripcionMedicamento);
        doctorJava = (EditText) findViewById(R.id.nombreDoctor);
        telefonoDr = (EditText) findViewById(R.id.telDoctor);
        horasMed = (EditText) findViewById(R.id.horasMedicamento);
        minMed = (EditText) findViewById(R.id.minutosMedicamento);
        btnRegistrar = (Button) findViewById(R.id.btnFotoMedicamento);


    }

    private EditText nomMedicamentoJava;
    private EditText idMedicamentoJava;
    private EditText padecimientoJava;
    private EditText dosisJava;
    private EditText descripcionJava;
    private EditText doctorJava;
    private EditText telefonoDr;
    private EditText horasMed;
    private EditText minMed;
    private Button btnRegistrar;
}
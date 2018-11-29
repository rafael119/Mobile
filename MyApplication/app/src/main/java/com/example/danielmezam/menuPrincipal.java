package com.example.danielmezam;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class menuPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu mimenu) {
        getMenuInflater().inflate(R.menu.menu, mimenu);
        return true;
        //return super.onCreateOptionsMenu(mimenu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem opcMimenu){
        switch (opcMimenu.getItemId()){
            case R.id.medicamentos:
                Intent intenAgregarMedicamentos = new Intent(menuPrincipal.this,agregarMedicamentos.class);
                startActivity(intenAgregarMedicamentos);
                Toast.makeText(this, "medicamentos", Toast.LENGTH_SHORT).show();
                break;
            case R.id.calendario:
                ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "dbProyecto", null, 1);
                SQLiteDatabase db = conn.getReadableDatabase();
                String consulta = "SELECT nombreMedicamento from medicamento";
                Cursor c = db.rawQuery(consulta ,null);
                while (c.moveToNext()){
                    String nombreMedicamento = c.getString(0);
                    Toast.makeText(this, ""+nombreMedicamento, Toast.LENGTH_SHORT).show();
                }
                c.close();

                /*
                Intent intentCalendar = new Intent(menuPrincipal.this, calendario.class);
                startActivity(intentCalendar);
                Toast.makeText(this, "calendario", Toast.LENGTH_SHORT).show();*/
                break;
            case R.id.farmacias:
                Toast.makeText(this, "farmacias", Toast.LENGTH_SHORT).show();
                break;
            case R.id.modificarMedicamentos:
                Toast.makeText(this, "Modificar medicamentos", Toast.LENGTH_SHORT).show();
                Intent intentModificarMedicamentos = new Intent(menuPrincipal.this, medicamentosRegistrados.class);
                startActivity(intentModificarMedicamentos);
                break;
            case R.id.guardarInformacion:
                Toast.makeText(this, "Guardar información", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(opcMimenu);
    }

}

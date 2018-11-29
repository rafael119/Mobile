package com.example.danielmezam;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class mediList extends AppCompatActivity {

    GridView gridView;
    ArrayList<medicamentosShow> list;
    medicamentosListaAdaptable adapter = null;
    @Override
    public void onCreate( Bundle savedInstanceState,  PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        gridView = (GridView) findViewById(R.id.gridView);
        list = new ArrayList<>();
        adapter = new medicamentosListaAdaptable(this, R.layout.lista_medicamento, list);
        gridView.setAdapter(adapter);

        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "dbProyecto", null, 1);
        SQLiteDatabase db = conn.getReadableDatabase();
        String consulta = "SELECT nombreMedicamento from medicamento";
        Cursor c = db.rawQuery(consulta ,null);
        while (c.moveToNext()){
            String nombreMedicamento = c.getString(0);
            list.add(new medicamentosShow(nombreMedicamento));
        }
        c.close();
        adapter.notifyDataSetChanged();
    }
}

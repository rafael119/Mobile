package com.example.danielmezam;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConexionSQLiteHelper extends SQLiteOpenHelper {
    public static final String tablaMedicamento ="MEDICAMENTO";
    public static final String id ="idMedicamento";
    public static final String nombreMedicamento = "nombreMedicamento";
    public static final String padecimiento = "padecimiento";
    public static final String dosis = "dosis";
    public static final String imagenEnvase = "imgEnvase";
    public static final String imagenMedicamento = "imgMedicamento";
    public static final String descripcionMedicamento = "descripcion";
    public static final String horasAldia = "horas";
    public static final String minutos = "minutos";
    public static final String doctor = "doctor";
    public static final String telDoctor = "telDoctor";
    public static final String fechaInicio = "fechaInicio";
    public static final String fechaTermino = "fechaTermino";
    public static final String horaInicial = "horaInicial";

    public static final String crearTablaMedicamento = "CREATE TABLE " + tablaMedicamento + " (" + nombreMedicamento
            + " TEXT," + padecimiento + " TEXT," + dosis + " TEXT," + descripcionMedicamento + " TEXT, "
            + doctor + " TEXT, " + telDoctor + " INT, " + horasAldia +" INT," + minutos +" INTEGER,"  + imagenEnvase
            + "  TEXT, " + imagenMedicamento + " TEXT," + fechaInicio + " DATE," + fechaTermino + " DATE, " + horaInicial +" TEXT)";


    public ConexionSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table medicamento (" +
                "idMedicamento int primary key," +
                "nombreMedicamento text," +
                "padecimiento text," +
                "dosis text," +
                "descripcion text," +
                "doctor text," +
                "telDoctor int," +
                "horas int," +
                "minutos int," +
                "imgEnvase text," +
                "imgMedicamento text," +
                "fechaInicio text," +
                "fechaTermino text," +
                "horaInicial text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS MEDICAMENTO");
        onCreate(db);
    }
}


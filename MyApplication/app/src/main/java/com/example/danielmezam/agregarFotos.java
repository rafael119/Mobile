package com.example.danielmezam;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


import java.io.File;
import java.util.ArrayList;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class agregarFotos extends AppCompatActivity {
    public ArrayList<String> datos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_fotos);
        componentes();

        if(validaPermisos()){
        }else{
            btnImgEnvase.setEnabled(false);
            btnImgMedicamento.setEnabled(false);
            btnContinuar.setEnabled(false);
        }

        btnImgEnvase.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                menuOpciones(codSelecImgEnvase, codSelecFotoEnvase);
            }
        });

        btnImgMedicamento.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                menuOpciones(codSelecImgMedicamento, codSelecFotoMedicamento);
            }
        });

        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                continuar();
            }
        });
    }

    private void menuOpciones(final int codImagen, final int codFoto){
        final CharSequence[] opciones ={"Tomar foto", "Elegir de galeria", "Cancelar"};
        final AlertDialog.Builder builder = new AlertDialog.Builder(agregarFotos.this);
        builder.setTitle("Elige una opción");
        builder.setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(opciones[which].equals("Tomar foto")){
                    //Toast.makeText(agregarMedicamentos.this, "Tomar foto ", Toast.LENGTH_SHORT).show();
                    if(codFoto == 30){
                        abrirCamara(codFoto);
                    } else if( codFoto == 40){
                        abrirCamaraDOS(codFoto);
                    }

                } else if (opciones[which].equals("Elegir de galeria")){
                    try {
                        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        intent.setType("image/");
                        startActivityForResult(intent.createChooser(intent,"Selecciona una aplicacion"), codImagen);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                } else if (opciones[which].equals("Cancelar")){
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    private void abrirCamaraDOS(int codFoto) {
        File miFile2 = new File(Environment.getExternalStorageDirectory(), DIRECTORIO_IMAGEN);
        boolean isCreada = miFile2.exists();
        if(isCreada == false){
            isCreada = miFile2.mkdirs();
        }
        if(isCreada == true){
            Long consecutivo2 = System.currentTimeMillis()/1000;
            nombreFOTODOS = consecutivo2.toString() + ".jpg";
        }

        pathFOTODOS = Environment.getExternalStorageDirectory() + File.separator + DIRECTORIO_IMAGEN+ File.separator + nombreFOTODOS;
        fileFOTODOS  = new File(pathFOTODOS);
        Intent intentCamaraDOS = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intentCamaraDOS.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(fileFOTODOS));
        startActivityForResult(intentCamaraDOS, codFoto);
    }

    private void abrirCamara(int codFoto){
        File miFile = new File(Environment.getExternalStorageDirectory(), DIRECTORIO_IMAGEN);
        boolean isCreada = miFile.exists();
        if(isCreada == false){
            isCreada = miFile.mkdirs();
        }
        if(isCreada == true){
            Long consecutivo = System.currentTimeMillis()/1000;
            nombreFOTO = consecutivo.toString() + ".jpg";
        }

        pathFOTO = Environment.getExternalStorageDirectory() + File.separator + DIRECTORIO_IMAGEN+ File.separator + nombreFOTO;
        fileFOTO  = new File(pathFOTO);
        Intent intentCamara = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intentCamara.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(fileFOTO));
        startActivityForResult(intentCamara, codFoto);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            switch (requestCode){
                case codSelecImgEnvase:
                    pathImagenUno = data.getData();
                    Toast.makeText(this, "" + pathImagenUno, Toast.LENGTH_SHORT).show();
                    imgEnvase.setImageURI(pathImagenUno);
                    isImgEnvase = true;
                    break;

                case codSelecFotoEnvase:
                    MediaScannerConnection.scanFile(agregarFotos.this, new String[]{pathFOTO},null,
                            new MediaScannerConnection.OnScanCompletedListener() {
                                @Override
                                public void onScanCompleted(String path, Uri uri) {
                                    Log.i("Ruta de almacenamiento", "Path: " + pathFOTO);
                                }
                            });
                    Toast.makeText(this, "" + pathFOTO.toString(), Toast.LENGTH_SHORT).show();
                    bitmap = BitmapFactory.decodeFile(pathFOTO);
                    imgEnvase.setImageBitmap(bitmap);
                    isImgEnvase = false;
                    break;

                case codSelecImgMedicamento:
                    pathImagenDos = data.getData();
                    Toast.makeText(this, "" + pathImagenDos, Toast.LENGTH_SHORT).show();
                    imgMedicamento.setImageURI(pathImagenDos);
                    String lol = pathImagenDos.getPath();
                    isImgMedicamento = true;
                    break;

                case codSelecFotoMedicamento:
                    MediaScannerConnection.scanFile(agregarFotos.this, new String[]{pathFOTODOS},null,
                            new MediaScannerConnection.OnScanCompletedListener() {
                                @Override
                                public void onScanCompleted(String path, Uri uri) {
                                    Log.i("Ruta de almacenamiento", "Path: " + pathFOTODOS);
                                }
                            });
                    Toast.makeText(this, "" + pathFOTODOS, Toast.LENGTH_SHORT).show();
                    bitmapDos = BitmapFactory.decodeFile(pathFOTODOS);
                    imgMedicamento.setImageBitmap(bitmapDos);
                    isImgMedicamento = false;
                    break;
            }
        }
    }

    private void continuar() {
        Bundle extras = getIntent().getExtras();
        datos = extras.getStringArrayList("datos");
        if(isImgEnvase == true && isImgMedicamento == true){
            datos.add(9, pathImagenUno.toString());
            datos.add(10,pathImagenDos.toString());
            Toast.makeText(this, "" + pathImagenUno + "" + pathImagenDos +"", Toast.LENGTH_SHORT).show();
            //Toast.makeText(this, "" + pathImagenUno + "\n" + pathImagenDos, Toast.LENGTH_SHORT).show();
            Intent goToHorario = new Intent(agregarFotos.this, fechaInicio.class);
            goToHorario.putStringArrayListExtra("datos", datos);
            startActivity(goToHorario);
        } else if (isImgEnvase == true && isImgMedicamento == false ){
            datos.add(9, pathImagenUno.toString());
            datos.add(10, pathFOTODOS.toString());
            //Toast.makeText(this, "" + pathImagenUno + "\n" + pathFOTODOS, Toast.LENGTH_SHORT).show();
            Intent goToHorario = new Intent(agregarFotos.this, fechaInicio.class);
            goToHorario.putStringArrayListExtra("datos", datos);
            startActivity(goToHorario);
        } else if (isImgEnvase == false && isImgMedicamento == true) {
            datos.add(9,pathFOTO.toString());
            datos.add(10,pathImagenDos.toString());
            //Toast.makeText(this, "" + pathFOTO + "\n" + pathImagenDos, Toast.LENGTH_SHORT).show();
            Intent goToHorario = new Intent(agregarFotos.this, fechaInicio.class);
            goToHorario.putStringArrayListExtra("datos", datos);
            startActivity(goToHorario);
        } else if( isImgEnvase == false && isImgMedicamento == false){
            datos.add(9,pathFOTO.toString());
            datos.add(10, pathFOTODOS.toString());
            //Toast.makeText(this, "" + pathFOTO + "\n" + pathFOTODOS, Toast.LENGTH_SHORT).show();
            Intent goToHorario = new Intent(agregarFotos.this, fechaInicio.class);
            goToHorario.putStringArrayListExtra("datos", datos);
            startActivity(goToHorario);
        }
    }

    private boolean validaPermisos() {

        if(Build.VERSION.SDK_INT<Build.VERSION_CODES.M){
            return true;
        }

        if((checkSelfPermission(CAMERA)==PackageManager.PERMISSION_GRANTED)&&
                (checkSelfPermission(WRITE_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED)){
            return true;
        }

        if((shouldShowRequestPermissionRationale(CAMERA)) ||
                (shouldShowRequestPermissionRationale(WRITE_EXTERNAL_STORAGE))){
            cargarDialogoRecomendacion();
        }else{
            requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE,CAMERA},100);
        }

        return false;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,  String[] permissions,  int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode==100){
            if(grantResults.length==2 && grantResults[0]==PackageManager.PERMISSION_GRANTED
                    && grantResults[1]==PackageManager.PERMISSION_GRANTED){
                btnImgEnvase.setEnabled(true);
            }else{
                solicitarPermisosManual();
            }
        }
    }

    private void solicitarPermisosManual() {
        final CharSequence[] opciones={"si","no"};
        final AlertDialog.Builder alertOpciones=new AlertDialog.Builder(agregarFotos.this);
        alertOpciones.setTitle("¿Desea configurar los permisos de forma manual?");
        alertOpciones.setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (opciones[i].equals("si")){
                    Intent intent=new Intent();
                    intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    Uri uri=Uri.fromParts("package",getPackageName(),null);
                    intent.setData(uri);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(),"Los permisos no fueron aceptados",Toast.LENGTH_SHORT).show();
                    dialogInterface.dismiss();
                }
            }
        });
        alertOpciones.show();
    }

    private void cargarDialogoRecomendacion() {
        AlertDialog.Builder dialogo=new AlertDialog.Builder(agregarFotos.this);
        dialogo.setTitle("Permisos Desactivados");
        dialogo.setMessage("Debe aceptar los permisos para el correcto funcionamiento de la App");

        AlertDialog.Builder aceptar = dialogo.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE, CAMERA}, 100);
            }
        });
        dialogo.show();
    }

    private void componentes(){
        btnImgEnvase = (Button) findViewById(R.id.btnEnvase);
        btnImgMedicamento = (Button) findViewById(R.id.btnMedicamento);
        btnContinuar = (Button) findViewById(R.id.btnContinuar);
        imgEnvase = (ImageView) findViewById(R.id.imgenEnvase);
        imgMedicamento = (ImageView) findViewById(R.id.imagnMedicamento);

    }
    final int codSelecImgEnvase = 10;
    final int codSelecImgMedicamento = 20;
    final int codSelecFotoEnvase = 30;
    final int codSelecFotoMedicamento = 40;
    public String pathFOTO;
    private String pathFOTODOS;
    private String nombreFOTO = "";
    private String nombreFOTODOS = "";
    File fileFOTO, fileFOTODOS;
    private Button btnImgEnvase;
    private Button btnImgMedicamento;
    private Button btnContinuar;
    private ImageView imgEnvase;
    private ImageView imgMedicamento;

    public Uri pathImagenUno;
    public Uri pathImagenDos;

    public String URLimagenEnvaseUNO;
    public String URLimagenEnvaseDOS;
    public String URLimagenMedicamentoUNO;
    public String URLimagenMedicamentoDOS;

    private boolean isImgMedicamento;
    private boolean isImgEnvase;

    private static final String CARPETA_PRINCIPAL = "myPictureApp/";
    private static final String CARPETA_IMAGEN = "imagenes";
    private static final String DIRECTORIO_IMAGEN = CARPETA_PRINCIPAL + CARPETA_IMAGEN;

    Bitmap bitmap, bitmapDos;
}

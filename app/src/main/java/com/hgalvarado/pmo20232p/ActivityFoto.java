package com.hgalvarado.pmo20232p;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class ActivityFoto extends AppCompatActivity {

    static final int peticion_captura_imagen=101;
    static final int peticion_acceso_camara=102;

    ImageView Objetoimagen;
    Button btnTomarFoto;
    String pathFoto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foto);
        Objetoimagen=(ImageView) findViewById(R.id.imageView);
        btnTomarFoto=(Button) findViewById(R.id.btnTomarFoto);
        btnTomarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                permisos();
            }
        });
    }

    private void permisos() {
        if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},peticion_acceso_camara);
        }else{
            TomarFoto();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==peticion_acceso_camara){
            if(grantResults.length>0 & grantResults[0]==PackageManager.PERMISSION_GRANTED){
                TomarFoto();
            }
        }else {
            Toast.makeText(getApplicationContext(),"Se necesita permiso para acceder a la camara",Toast.LENGTH_LONG).show();
        }
    }

    private void TomarFoto() {

    }
}
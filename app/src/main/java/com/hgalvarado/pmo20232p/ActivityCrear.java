package com.hgalvarado.pmo20232p;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hgalvarado.pmo20232p.Configuracion.SQLiteConexion;
import com.hgalvarado.pmo20232p.Configuracion.Transacciones;

public class ActivityCrear extends AppCompatActivity {

    EditText txtNombres, txtApellidos, txtEdad, txtCorreo;
    Button btnInsertar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear);

        txtNombres = (EditText) findViewById(R.id.txtNombres);
        txtApellidos = (EditText) findViewById(R.id.txtApellidos);
        txtEdad =(EditText) findViewById(R.id.txtEdad);
        txtCorreo = (EditText) findViewById(R.id.txtCorreo);

        btnInsertar = (Button) findViewById(R.id.btnInsertar);

        btnInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddPerson();
            }
        });
    }

    private void AddPerson() {

        SQLiteConexion conexion = new SQLiteConexion( this, Transacciones.nameDatabase, null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(Transacciones.nombres, txtNombres.getText().toString());
        valores.put(Transacciones.apellidos, txtApellidos.getText().toString());
        valores.put(Transacciones.edad, txtEdad.getText().toString());
        valores.put(Transacciones.correo, txtCorreo.getText().toString());

        Long result = db.insert(Transacciones.tablePersonas,Transacciones.id,valores);
        Toast.makeText(getApplicationContext(), "Registro ingresado: " + result.toString(),Toast.LENGTH_LONG).show();



        db.close();
        CleanScreen();



    }

    private void CleanScreen() {
        txtNombres.setText("");
        txtApellidos.setText("");
        txtEdad.setText("");
        txtCorreo.setText("");
    }
}
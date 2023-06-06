package com.hgalvarado.pmo20232p;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.hgalvarado.pmo20232p.Configuracion.SQLiteConexion;
import com.hgalvarado.pmo20232p.Configuracion.Transacciones;
import com.hgalvarado.pmo20232p.Models.Personas;

import java.util.ArrayList;

public class ActivityCombo extends AppCompatActivity {

    SQLiteConexion conexion;
    Spinner comboPersonas;
    EditText nombres,apellidos, correo;
    ArrayList<String> listaPersonas;
    ArrayList<Personas> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combo);

        conexion = new SQLiteConexion(this, Transacciones.nameDatabase, null, 1);
        comboPersonas = (Spinner) findViewById(R.id.spinner);
        nombres= (EditText) findViewById(R.id.cbNombre);
        apellidos= (EditText) findViewById(R.id.cbApellido);
        correo= (EditText) findViewById(R.id.cbCorreo);

        obtenerTabla();

        ArrayAdapter<CharSequence> adp = new ArrayAdapter(this, android.R.layout.simple_spinner_item,listaPersonas);
        comboPersonas.setAdapter(adp);

        comboPersonas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {
                    nombres.setText(lista.get(position).getNombres());
                    apellidos.setText(lista.get(position).getApellidos());
                    correo.setText(lista.get(position).getCorreo());
                }catch (Exception e){
                    e.toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void obtenerTabla() {
        SQLiteDatabase db = conexion.getReadableDatabase();
        Personas person = null;

        lista = new ArrayList<Personas>();

        //Cursor de base de datos
        Cursor cursor = db.rawQuery(Transacciones.SelectTablePersonas,null);

        //Recorremos el cursor
        while (cursor.moveToNext()){
            person = new Personas();
            person.setId(cursor.getInt(0));
            person.setNombres(cursor.getString(1));
            person.setApellidos(cursor.getString(2));
            person.setEdad(cursor.getInt(3));
            person.setCorreo(cursor.getString(4));

            lista.add(person);
        }
        cursor.close();

        fillList();
    }
    private void fillList() {
        listaPersonas = new ArrayList<String>();
        for (int i = 0; i < lista.size(); i++) {
            listaPersonas.add(lista.get(i).getId() + "-"
                    + lista.get(i).getNombres() + "-"
                    + lista.get(i).getApellidos() + "-"
                    + lista.get(i).getEdad() + "-"
                    + lista.get(i).getCorreo() + "-"
            );
        }
    }

}
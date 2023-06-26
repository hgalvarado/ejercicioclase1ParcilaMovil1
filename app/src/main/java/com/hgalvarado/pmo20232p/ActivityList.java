package com.hgalvarado.pmo20232p;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.hgalvarado.pmo20232p.Configuracion.SQLiteConexion;
import com.hgalvarado.pmo20232p.Configuracion.Transacciones;
import com.hgalvarado.pmo20232p.Models.Personas;

import java.util.ArrayList;

public class ActivityList extends AppCompatActivity {

    SQLiteConexion conexion;
    ListView listPersonas;
    ArrayList<Personas> lista;
    ArrayList<String> arregloPersonas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        conexion = new SQLiteConexion(this, Transacciones.nameDatabase, null, 1);
        listPersonas = (ListView) findViewById(R.id.listPerson);

        obtenerTabla();
        ArrayAdapter adp = new ArrayAdapter(this, android.R.layout.simple_list_item_1,arregloPersonas);
        listPersonas.setAdapter(adp);

        listPersonas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                // Obtener el elemento seleccionado en la lista
                String selectedItem = (String) adapterView.getItemAtPosition(position);
                // Realizar alguna acción con el elemento seleccionado
                Toast.makeText(getApplicationContext(), "Seleccionaste: " + selectedItem, Toast.LENGTH_SHORT).show();

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
        arregloPersonas = new ArrayList<String>();
        for (int i = 0; i < lista.size(); i++) {
            arregloPersonas.add(lista.get(i).getId() + "-"
                            + lista.get(i).getNombres() + "-"
                            + lista.get(i).getApellidos() + "-"
                            + lista.get(i).getEdad() + "-"
                            + lista.get(i).getCorreo() + "-"
                    );
        }
    }

    //Probando desde la linea de comando
}

package com.hgalvarado.pmo20232p.Configuracion;

public class Transacciones {
    //Nombre de la base de datos
    public static final String nameDatabase = "PM01DB";

    //Tablas de la base de datos
    public static final String tablePersonas = "personas";

    //Campos de la tabla personas
    public static final String id = "id";
    public static final String nombres = "nombres";
    public static final String apellidos = "apellidos";
    public static final String edad = "edad";
    public static final String correo = "correo";

    //DDL Create and Drop
    public static final String CreateTablePersonas = "CREATE TABLE personas " +
            "(id INTEGER PRIMARY KEY AUTOINCREMENT, nombres TEXT, apellidos TEXT, edad INTEGER, correo TEXT )";
    public static final String DropTablePersonas = "DROP TABLE IF EXISTS personas";

    //DML
    public static final String SelectTablePersonas = "SELECT * FROM " + tablePersonas;



}

package models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "TareasDB.db";
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Crear tabla
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE tareas (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "titulo TEXT, " +
                "descripcion TEXT, " +
                "fecha TEXT, " +
                "prioridad TEXT, " +
                "estado TEXT)";
        db.execSQL(sql);
    }

    // Actualizar base si cambia versión
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS tareas");
        onCreate(db);
    }

    // Insertar nueva tarea
    public void insertarTarea(String titulo, String descripcion, String fecha, String prioridad) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("titulo", titulo);
        values.put("descripcion", descripcion);
        values.put("fecha", fecha);
        values.put("prioridad", prioridad);
        values.put("estado", "pendiente"); // Estado inicial
        db.insert("tareas", null, values);
        db.close();
    }

    // Obtener todas las tareas
    public List<Tarea> obtenerTodasLasTareas() {
        List<Tarea> lista = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM tareas ORDER BY estado ASC, fecha ASC", null);

        if (cursor.moveToFirst()) {
            do {
                Tarea tarea = new Tarea();
                tarea.setId(cursor.getInt(0));
                tarea.setTitulo(cursor.getString(1));
                tarea.setDescripcion(cursor.getString(2));
                tarea.setFecha(cursor.getString(3));
                tarea.setPrioridad(cursor.getString(4));
                tarea.setEstado(cursor.getString(5));
                lista.add(tarea);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return lista;
    }

    // Actualizar estado de una tarea
    public void actualizarEstadoTarea(int id, String nuevoEstado) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("estado", nuevoEstado);
        db.update("tareas", values, "id = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    public Cursor obtenerTareas() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM tareas ORDER BY estado ASC, fecha ASC", null);
    }

    public int contarTareas() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM tareas", null);
        int total = 0;
        if (cursor.moveToFirst()) {
            total = cursor.getInt(0);
        }
        cursor.close();
        return total;
    }

    public int contarTareasPorEstado(String estado) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM tareas WHERE estado = ?", new String[]{estado});
        int total = 0;
        if (cursor.moveToFirst()) {
            total = cursor.getInt(0);
        }
        cursor.close();
        return total;
    }


}


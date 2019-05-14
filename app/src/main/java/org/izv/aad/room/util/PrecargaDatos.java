package org.izv.aad.room.util;

import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;

import org.izv.aad.room.entities.Libro;
import org.izv.aad.room.entities.Prestamo;
import org.izv.aad.room.entities.Usuario;
import org.izv.aad.room.room.BasedatosApp;

import java.util.Calendar;
import java.util.Date;

public class PrecargaDatos {

    private static final int DELAY = 500;

    public static void cargaAsincrona(final BasedatosApp db) {
        CargaAsincrona task = new CargaAsincrona(db);
        task.execute();
    }

    public static void cargaSincrona(@NonNull final BasedatosApp db) {
        cargaDatos(db);
    }

    private static void addPrestamo(final BasedatosApp db, final String id,
                                    final Usuario usuario, final Libro libro,
                                    Date desde, Date hasta) {
        Prestamo prestamo = new Prestamo();
        prestamo.id = id;
        prestamo.libroId = libro.id;
        prestamo.usuarioId = usuario.id;
        prestamo.fechaInicial = desde;
        prestamo.fechaFinal = hasta;
        db.modeloPrestamo().insertarPrestamo(prestamo);
    }

    private static Libro addLibro(final BasedatosApp db, final String id, final String titulo) {
        Libro libro = new Libro();
        libro.id = id;
        libro.titulo = titulo;
        db.modeloLibro().insertarLibro(libro);
        return libro;
    }

    private static Usuario addUsuario(final BasedatosApp db, final String id,
                                      final String nombre, final String apellidos,
                                      final int edad) {
        Usuario usuario = new Usuario();
        usuario.id = id;
        usuario.edad = edad;
        usuario.nombre = nombre;
        usuario.apellidos = apellidos;
        db.modeloUsuario().insertarUsuario(usuario);
        return usuario;
    }

    private static void cargaDatos(BasedatosApp db) {
        db.modeloPrestamo().borrarTodos();
        db.modeloUsuario().borrarTodos();
        db.modeloLibro().borrarTodos();

        Usuario usuario1 = addUsuario(db, "1", "Jason", "Seaver", 40);
        Usuario usuario2 = addUsuario(db, "2", "Mike", "Seaver", 12);
        addUsuario(db, "3", "Carol", "Seaver", 15);

        Libro libro1 = addLibro(db, "1", "Dune");
        Libro libro2 = addLibro(db, "2", "1984");
        Libro libro3 = addLibro(db, "3", "The War of the Worlds");
        Libro libro4 = addLibro(db, "4", "Brave New World");
        addLibro(db, "5", "Foundation");
        try {
            Date hoy = getFechaSumaDias(0);
            Date ayer = getFechaSumaDias(-1);
            Date haceDosDias = getFechaSumaDias(-2);
            Date semanaPasada = getFechaSumaDias(-7);
            Date haceDosSemanas = getFechaSumaDias(-14);

            addPrestamo(db, "1", usuario1, libro1, haceDosSemanas, semanaPasada);
            Thread.sleep(DELAY);
            addPrestamo(db, "2", usuario2, libro1, semanaPasada, ayer);
            Thread.sleep(DELAY);
            addPrestamo(db, "3", usuario2, libro2, semanaPasada, hoy);
            Thread.sleep(DELAY);
            addPrestamo(db, "4", usuario2, libro3, semanaPasada, haceDosDias);
            Thread.sleep(DELAY);
            addPrestamo(db, "5", usuario2, libro4, semanaPasada, hoy);
            Log.d("DB", "Added loans");

        } catch (InterruptedException e) {
            Log.d("DB", e.toString());
        }
    }

    private static Date getFechaSumaDias(int dias) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, dias);
        return calendar.getTime();
    }

    private static class CargaAsincrona extends AsyncTask<Void, Void, Void> {

        private final BasedatosApp mDb;

        CargaAsincrona(BasedatosApp db) {
            mDb = db;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            cargaDatos(mDb);
            return null;
        }

    }
}
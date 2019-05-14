package org.izv.aad.room.room;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import org.izv.aad.room.dao.DaoLibro;
import org.izv.aad.room.dao.DaoPrestamo;
import org.izv.aad.room.dao.DaoUsuario;
import org.izv.aad.room.entities.Libro;
import org.izv.aad.room.entities.Prestamo;
import org.izv.aad.room.entities.Usuario;

@Database(entities = {Usuario.class, Libro.class, Prestamo.class}, version = 1)
public abstract class BasedatosApp extends RoomDatabase {

    private static BasedatosApp INSTANCIA;

    public abstract DaoUsuario modeloUsuario();

    public abstract DaoLibro modeloLibro();

    public abstract DaoPrestamo modeloPrestamo();

    public static BasedatosApp getBasedatos(Context contexto) {
        if (INSTANCIA == null) {
            Log.v("TAG", "instancia");
            INSTANCIA =
                    Room.databaseBuilder(contexto.getApplicationContext(), BasedatosApp.class,
                            "biblioteca.sqlite").allowMainThreadQueries().build();
                    /*Room.inMemoryDatabaseBuilder(contexto.getApplicationContext(), BasedatosApp.class)
                            // To simplify the codelab, allow queries on the main thread.
                            // Don't do this on a real app! See PersistenceBasicSample for an example.
                            .allowMainThreadQueries()
                            .build();*/
        }
        return INSTANCIA;
    }

    public static void destruirInstancia() {
        INSTANCIA = null;
    }
}

package org.izv.aad.room;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import org.izv.aad.room.entities.Usuario;
import org.izv.aad.room.room.BasedatosApp;
import org.izv.aad.room.util.PrecargaDatos;

import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();;

    /*
    https://developer.android.com/training/data-storage/room

    Components:

    Database: Contains the database holder and serves as the main access point for the underlying
    connection to your app's persisted, relational data.
    The class that's annotated with @Database should satisfy the following conditions:
    Be an abstract class that extends RoomDatabase.
    Include the list of entities associated with the database within the annotation.
    Contain an abstract method that has 0 arguments and returns the class that is annotated
    with @Dao.
    At runtime, you can acquire an instance of Database by calling Room.databaseBuilder() or
    Room.inMemoryDatabaseBuilder().

    Entity: Represents a table within the database.

    DAO: Contains the methods used for accessing the database.

    https://codelabs.developers.google.com/codelabs/android-persistence


    */

    private BasedatosApp mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDb = BasedatosApp.getBasedatos(getApplicationContext());
        populateDb();
        fetchData();
    }

    private void fetchData() {
        StringBuilder sb = new StringBuilder();
        List<Usuario> usuariosJovenes = mDb.modeloUsuario().getUsuariosMenoresQue(35);
        for (Usuario usuarioJoven : usuariosJovenes) {
            sb.append(String.format(Locale.US,
                    "%s, %s (%d)\n", usuarioJoven.apellidos, usuarioJoven.nombre, usuarioJoven.edad));
        }
        Log.v(TAG, sb.toString());
        //mYoungUsersTextView.setText(sb);
    }

    private void populateDb() {
        PrecargaDatos.cargaSincrona(mDb);
    }
}

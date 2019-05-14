package org.izv.aad.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import org.izv.aad.room.entities.Usuario;

import java.util.List;

import static androidx.room.OnConflictStrategy.IGNORE;

@Dao
public interface DaoUsuario {

    @Query("select * from Usuario")
    List<Usuario> getUsuarios();

    @Query("select * from Usuario where id = :id")
    Usuario getUsuario(int id);

    @Query("select * from Usuario where nombre = :nombre and apellidos = :apellidos")
    List<Usuario> getUsuarioPorNombreApellidos(String nombre, String apellidos);

    @Insert(onConflict = IGNORE)
    void insertarUsuario(Usuario usuario);

    @Delete
    void borrarUsuario(Usuario usuario);

    @Query("delete from Usuario where nombre like :nombreOApellidos OR apellidos like :nombreOApellidos")
    int borrarUsuarioPorNombre(String nombreOApellidos);

    @Insert(onConflict = IGNORE)
    void insertarOReemplazarUsuarios(Usuario... users);

    @Delete
    void borrarUsuarios(Usuario usuario1, Usuario usuario2);

    @Query("select * from Usuario where edad < :edad")
    List<Usuario> getUsuariosMenoresQue(int edad);

    @Query("delete from Usuario")
    void borrarTodos();
}
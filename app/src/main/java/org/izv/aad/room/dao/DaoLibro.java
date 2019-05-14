package org.izv.aad.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.TypeConverters;
import androidx.room.Update;

import org.izv.aad.room.entities.Libro;
import org.izv.aad.room.util.DateConverter;

import java.util.Date;
import java.util.List;

import static androidx.room.OnConflictStrategy.IGNORE;
import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
@TypeConverters(DateConverter.class)
public interface DaoLibro {

    @Query("select * from Libro where id = :id")
    Libro getLibroPorId(int id);

    @Query("select * from Libro " +
            "inner join Prestamo on Prestamo.id_libro = Libro.id " +
            "inner join Usuario on Usuario.id = Prestamo.id_usuario " +
            "where Usuario.nombre like :nombreUsuario"
    )
    LiveData<List<Libro>> getLibrosPrestadosPorNombre(String nombreUsuario);

    @Query("select * from Libro " +
            "inner join Prestamo on Prestamo.id_libro = Libro.id " +
            "inner join Usuario on Usuario.id = Prestamo.id_usuario " +
            "where Usuario.nombre like :nombreUsuario " +
            "and Prestamo.fechaFinal > :fechaFinal "
    )
    LiveData<List<Libro>> getLibrosPrestadosPorNombreFecha(String nombreUsuario, Date fechaFinal);

    @Query("select * from Libro " +
            "inner join Prestamo on Prestamo.id_libro = Libro.id " +
            "inner join Usuario on Usuario.id = Prestamo.id_usuario " +
            "where Usuario.nombre like :nombreUsuario"
    )
    List<Libro> getLibrosPrestadosPorNombreSync(String nombreUsuario);

    @Query("select * from Libro " +
            "inner join Prestamo on Prestamo.id_libro like Libro.id " +
            "where Prestamo.id_usuario like :idUsuario "
    )
    LiveData<List<Libro>> getLibrosPrestadosPorUsuario(String idUsuario);

    @Query("select * from Libro " +
            "inner join Prestamo on Prestamo.id_libro like Libro.id " +
            "where Prestamo.id_usuario like :idUsuario " +
            "and Prestamo.fechaFinal > :fechaFinal "
    )
    LiveData<List<Libro>> getLibrosPrestadosPorUsuarioFecha(String idUsuario, Date fechaFinal);

    @Query("select * from Libro " +
            "inner join Prestamo on Prestamo.id_libro like Libro.id " +
            "where Prestamo.id_usuario like :idUsuario "
    )
    List<Libro> getLibrosPrestadosPorUsuarioSync(String idUsuario);

    @Query("select * from Libro")
    LiveData<List<Libro>> getLibros();


    @Query("select * from Libro")
    List<Libro> getLibrosSync();

    @Insert(onConflict = IGNORE)
    void insertarLibro(Libro libro);

    @Update(onConflict = REPLACE)
    void editarLibro(Libro libro);

    @Query("delete from Libro")
    void borrarTodos();
}
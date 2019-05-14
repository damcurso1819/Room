package org.izv.aad.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.TypeConverters;

import org.izv.aad.room.entities.Prestamo;
import org.izv.aad.room.entities.PrestamoUsuarioLibro;
import org.izv.aad.room.util.DateConverter;

import java.util.Date;
import java.util.List;

@Dao
@TypeConverters(DateConverter.class)
public interface DaoPrestamo {

    @Query("select * from Prestamo")
    LiveData<List<Prestamo>> getPrestamos();

    @Query("select Prestamo.id, Libro.titulo, Usuario.nombre, Prestamo.fechaInicial, Prestamo.fechaFinal from Prestamo " +
            "inner join Libro on Prestamo.id_libro = Libro.id " +
            "inner join Usuario on Prestamo.id_usuario = Usuario.id ")
    LiveData<List<PrestamoUsuarioLibro>> getPrestamosUsuarioLibro();

    @Query("select Prestamo.id, Libro.titulo as titulo, Usuario.nombre as nombre, Prestamo.fechaInicial, Prestamo.fechaFinal " +
            "from Libro " +
            "inner join Prestamo on Prestamo.id_libro = Libro.id " +
            "inner join Usuario on Usuario.id = Prestamo.id_usuario " +
            "where Usuario.nombre like :nombreUsuario " +
            "and Prestamo.fechaFinal > :fechaFinal "
    )
    LiveData<List<PrestamoUsuarioLibro>> getPrestamosPorNombreFecha(String nombreUsuario, Date fechaFinal);

    @Insert()
    void insertarPrestamo(Prestamo prestamo);

    @Query("delete from Prestamo")
    void borrarTodos();
}
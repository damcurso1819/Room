package org.izv.aad.room.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import org.izv.aad.room.util.DateConverter;

import java.util.Date;

@Entity(foreignKeys = {
        @ForeignKey(entity = Libro.class,
                parentColumns = "id",
                childColumns = "id_libro"),

        @ForeignKey(entity = Usuario.class,
                parentColumns = "id",
                childColumns = "id_usuario")})
@TypeConverters(DateConverter.class)
public class Prestamo {

    // Fields can be public or private with getters and setters.
    @PrimaryKey
    @NonNull
    public String id;

    public Date fechaInicial;

    public Date fechaFinal;

    @ColumnInfo(name = "id_libro")
    public String libroId;

    @ColumnInfo(name = "id_usuario")
    public String usuarioId;
}

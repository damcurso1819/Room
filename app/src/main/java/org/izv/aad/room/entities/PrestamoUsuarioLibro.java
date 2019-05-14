package org.izv.aad.room.entities;

import androidx.room.ColumnInfo;
import androidx.room.TypeConverters;

import org.izv.aad.room.util.DateConverter;

import java.util.Date;

public class PrestamoUsuarioLibro {

    public String id;

    @ColumnInfo(name="titulo")
    public String tituloLibro;

    @ColumnInfo(name="nombre")
    public String nombreUsuario;

    @TypeConverters(DateConverter.class)
    public Date fechaInicio;

    @TypeConverters(DateConverter.class)
    public Date fechaFin;

}

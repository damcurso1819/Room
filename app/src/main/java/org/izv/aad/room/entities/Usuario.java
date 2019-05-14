package org.izv.aad.room.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Usuario {
    @PrimaryKey
    @NonNull
    public String id;

    public String nombre;

    public String apellidos;

    public int edad;
}

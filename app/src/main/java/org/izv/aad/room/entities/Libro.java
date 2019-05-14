package org.izv.aad.room.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Libro {
    @PrimaryKey
    @NonNull
    public String id;

    public String titulo;
}
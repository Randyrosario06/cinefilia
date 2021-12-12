package com.randy.pelisgit.Interfaces;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.randy.pelisgit.Models.SeenMovie;

@Database(entities = {SeenMovie.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {
    public abstract MovieDAO movieDAO();
}

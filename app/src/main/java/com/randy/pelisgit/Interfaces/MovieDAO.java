package com.randy.pelisgit.Interfaces;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.randy.pelisgit.Models.SeenMovie;
import java.util.List;
@Dao
public interface MovieDAO {
    @Insert
    void insertMovie(SeenMovie movie);

    @Query("SELECT * FROM SeenMovie")
    List<SeenMovie> getAll();

    @Query("SELECT * FROM SeenMovie WHERE movie_id == :movieId")
    List<SeenMovie> loadMovieSeen(int movieId);
}

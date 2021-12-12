package com.randy.pelisgit;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.randy.pelisgit.Interfaces.AppDataBase;
import com.randy.pelisgit.Models.SeenMovie;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieActivity extends AppCompatActivity {
    TextView title, descp;
    ImageView poster;
    Button btnSeen;
    int movieId;
    String POSTER_BASE_URL, movieTitle, movieDescp,vote_average, posterUrl;
    AppDataBase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        POSTER_BASE_URL = getString(R.string.Poster_URL_Base);
        title = findViewById(R.id.title);
        descp = findViewById(R.id.descpription);
        poster = findViewById(R.id.poster);
        btnSeen = findViewById(R.id.btnSeen);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onResume() {
        super.onResume();
        db = Room.databaseBuilder(getApplicationContext(),
                AppDataBase.class, "database-name").allowMainThreadQueries().build();
        Bundle intent = getIntent().getExtras();
        movieId = Integer.parseInt(intent.get("id").toString());
        movieTitle = intent.get("title").toString();
        vote_average = intent.get("vote_average").toString();
        movieDescp = intent.get("descp").toString();
        posterUrl = intent.get("poster").toString();

        title.setText(movieTitle);
        descp.setText(movieDescp);
        Picasso.get().load(POSTER_BASE_URL+posterUrl).into(poster);

        List<SeenMovie> verificar = db.movieDAO().loadMovieSeen(movieTitle);
        if(!verificar.isEmpty()) {
            btnSeen.setText("Ya la viste!");
            btnSeen.setEnabled(false);
            btnSeen.setBackgroundColor(getColor(R.color.blu_gray));
        }
            btnSeen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    btnSeen.setText("Ya la viste!");
                    btnSeen.setEnabled(false);
                    btnSeen.setBackgroundColor(getColor(R.color.blu_gray));
                    SeenMovie seen = new SeenMovie(movieId,movieTitle,
                            vote_average,posterUrl);
                    db.movieDAO().insertMovie(seen);
                }
            });
    }
}
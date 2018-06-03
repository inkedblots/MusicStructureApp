package com.example.android.music;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main);

        // Find the View that shows the Artist
        TextView queen = findViewById(R.id.queen);

        // Set a click listener on that View
        queen.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent queenIntent = new Intent(MainActivity.this, QueenActivity.class);
                // Start a new activity
                startActivity(queenIntent);
            }
        });

        TextView michaelJackson = findViewById(R.id.michaelJackson);
        michaelJackson.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent michaelJacksonIntent = new Intent(MainActivity.this, MichaeljacksonActivity.class);
                startActivity(michaelJacksonIntent);
            }
        });

        TextView davidBowie = findViewById(R.id.davidBowie);
        davidBowie.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent davidBowieIntent = new Intent(MainActivity.this, DavidbowieActivity.class);
                startActivity(davidBowieIntent);
            }
        });

        TextView gnr = findViewById(R.id.gnr);
        gnr.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent gnrIntent = new Intent(MainActivity.this, GNRActivity.class);
                startActivity(gnrIntent);
            }
        });

        TextView madonna = findViewById(R.id.madonna);
        madonna.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // create new intent to open {@link MadonnaActivity}
                Intent madonnaIntent = new Intent(MainActivity.this, MadonnaActivity.class);
                startActivity(madonnaIntent);
            }
        });
    }

}

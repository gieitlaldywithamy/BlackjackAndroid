package com.codeclan.amymorrison.toptrumps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Button play;
    ImageView dealerFirstCard;
    ImageView dealerHoleCard;
    ImageView playerFirstCard;
    ImageView playerSecondCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        play = findViewById(R.id.play_btn);
        dealerFirstCard = findViewById(R.id.dealer_firstCardView);
        dealerHoleCard = findViewById(R.id.dealer_holeCardView);
        playerFirstCard =
    }
}

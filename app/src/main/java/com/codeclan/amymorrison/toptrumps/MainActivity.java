package com.codeclan.amymorrison.toptrumps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button play;
    Button hitBtn;
    Button standBtn;
    ImageView dealerFirstCardView;
    ImageView dealerHoleCardView;
    ImageView playerFirstCardView;
    ImageView playerSecondCardView;
    Blackjack blackjack;
    Player player;
    Dealer dealer;
    ArrayList<Card> playerHand;
    RecyclerView cardList;
    LinearLayoutManager layoutManager;
    CardListAdapter playerHandAdapter;
    CardListAdapter dealerHandAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        play = findViewById(R.id.play_btn);
        dealerFirstCardView = findViewById(R.id.dealer_firstCardView);
        dealerHoleCardView = findViewById(R.id.dealer_holeCardView);
//        playerFirstCardView = findViewById(R.id.player_firstCardImage);
//        playerSecondCardView = findViewById(R.id.player_secondCardImage);
        hitBtn = findViewById(R.id.hit_btn);
        standBtn = findViewById(R.id.stand_btn);
        player = new Player();
        blackjack = new Blackjack(player);
        dealer = blackjack.getDealer();


        playerHand = new ArrayList<>();
        Log.d(getClass().toString(),"Fragment is being created");
//        playerHand.add(new Card(Suit.CLUBS, Rank.ACE, CardImage.EIGHTSPADES));
//        playerHand.add(new Card(Suit.DIAMONDS, Rank.EIGHT, CardImage.ACEHEARTS));
//        View rootView = inflater.inflate(R.layout.fragment_card_view, container, false);
        cardList = findViewById(R.id.card_list_view_in_fragment);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false );
        playerHandAdapter = new CardListAdapter(this, playerHand);
//        dealerHandAdapter = new CardListAdapter(this, dealerHand);
        cardList.setAdapter(playerHandAdapter);
        cardList.setLayoutManager(layoutManager);
        cardList.setNestedScrollingEnabled(false);
//        layoutManager.canScrollHorizontally(false);

    }

    public void play(View view) {
        play.setVisibility(View.INVISIBLE);
        blackjack.shuffleDeck();
        blackjack.initialDeal();
        ArrayList<Card> dealerHand = blackjack.getDealer().getPlayerHand();
        playerHand = blackjack.getPlayer().getPlayerHand();
        Card dealerFirstCard = dealerHand.get(0);
        dealerHoleCardView.setImageResource(dealerFirstCard.getImageUrl());
        Card playerFirstCard = playerHand.get(0);
//        playerFirstCardView.setImageResource(playerFirstCard.getImageUrl());
        Card playerSecondCard = playerHand.get(1);
//        playerSecondCardView.setImageResource(playerSecondCard.getImageUrl());
        playerHandAdapter.notifyDataSetChanged();
        hitBtn.setVisibility(View.VISIBLE);
        standBtn.setVisibility(View.VISIBLE);
        playerHandAdapter.insertItem(playerHand.get(0));
        playerHandAdapter.insertItem(playerHand.get(1));
        //notifyDataSetChange()
    }

    public void hit(View view) {
        player.drawCard(dealer.dealCard());
        playerHand = blackjack.getPlayer().getPlayerHand();
        //change this so it's not a direct index
        playerHandAdapter.insertItem(playerHand.get(2));

    }
}

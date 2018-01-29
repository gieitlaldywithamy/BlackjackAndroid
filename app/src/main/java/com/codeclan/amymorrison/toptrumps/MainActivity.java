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
    ArrayList<Card> dealerHand;
    RecyclerView playerCardListView;
    RecyclerView dealerCardListView;
    LinearLayoutManager layoutManager;
    LinearLayoutManager layoutManagerDealer;
    CardListAdapter playerHandAdapter;
    CardListAdapter dealerHandAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        play = findViewById(R.id.play_btn);
//        dealerFirstCardView = findViewById(R.id.dealer_firstCardView);
//        dealerHoleCardView = findViewById(R.id.dealer_holeCardView);
//        playerFirstCardView = findViewById(R.id.player_firstCardImage);
//        playerSecondCardView = findViewById(R.id.player_secondCardImage);
        hitBtn = findViewById(R.id.hit_btn);
        standBtn = findViewById(R.id.stand_btn);
        player = new Player();
        blackjack = new Blackjack(player);
        dealer = blackjack.getDealer();


        playerHand = new ArrayList<>();
        dealerHand = new ArrayList<>();
//        Log.d(getClass().toString(),"Fragment is being created");
//        playerHand.add(new Card(Suit.CLUBS, Rank.ACE, CardImage.EIGHTSPADES));
//        playerHand.add(new Card(Suit.DIAMONDS, Rank.EIGHT, CardImage.ACEHEARTS));
//        View rootView = inflater.inflate(R.layout.fragment_card_view, container, false);
        playerCardListView = findViewById(R.id.player_card_list);
        dealerCardListView = findViewById(R.id.dealer_card_list);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false );
        layoutManagerDealer = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false );
        playerHandAdapter = new CardListAdapter(this, playerHand);
        dealerHandAdapter = new CardListAdapter(this, dealerHand);
        playerCardListView.setAdapter(playerHandAdapter);
        playerCardListView.setLayoutManager(layoutManager);
        playerCardListView.setNestedScrollingEnabled(false);

        dealerCardListView.setAdapter(dealerHandAdapter);
        dealerCardListView.setLayoutManager(layoutManagerDealer);

//        layoutManager.canScrollHorizontally(false);

    }

    public void play(View view) {
        play.setVisibility(View.INVISIBLE);
        blackjack.shuffleDeck();
        blackjack.initialDeal();
        dealerHand = blackjack.getDealer().getPlayerHand();
        Card dealerFirstCard = dealerHand.get(0);
//        dealerHoleCardView.setImageResource(dealerFirstCard.getImageUrl());
        playerHand = blackjack.getPlayer().getPlayerHand();
        Card playerFirstCard = playerHand.get(0);
//        playerFirstCardView.setImageResource(playerFirstCard.getImageUrl());
        Card playerSecondCard = playerHand.get(1);
//        playerSecondCardView.setImageResource(playerSecondCard.getImageUrl());
        hitBtn.setVisibility(View.VISIBLE);
        standBtn.setVisibility(View.VISIBLE);

        playerHandAdapter.notifyDataSetChanged();
        dealerHandAdapter.notifyDataSetChanged();
        //notifyDataSetChange()
    }

    public void hit(View view) {
        player.drawCard(dealer.dealCard());

    }
}

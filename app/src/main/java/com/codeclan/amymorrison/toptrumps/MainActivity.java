package com.codeclan.amymorrison.toptrumps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

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
    RecyclerView cardList;
    LinearLayoutManager layoutManager;
    CardListAdapter playerHandAdapter;
    DealerCardAdapter dealerHandAdapter;
    GridView dealerCardDisplay;
    TextView gameResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        play = findViewById(R.id.play_btn);
        dealerFirstCardView = findViewById(R.id.dealer_firstCardView);
        dealerHoleCardView = findViewById(R.id.dealer_holeCardView);
//        dealerHoleCardView.setTag("hole card");
        gameResult = findViewById(R.id.who_won_txtView);
        dealerHand = new ArrayList<>();
        dealerHand.add(new Card(Suit.DIAMONDS, Rank.TEN, CardImage.EIGHTSPADES));
        dealerHandAdapter = new DealerCardAdapter(this, dealerHand);
        dealerCardDisplay = findViewById(R.id.dealer_card_grid);
        dealerCardDisplay.setAdapter(dealerHandAdapter);
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
        dealerHand = blackjack.getDealer().getPlayerHand();
        playerHand = blackjack.getPlayer().getPlayerHand();
//        Card dealerFirstCard = dealerHand.get(0);
//        dealerFirstCardView.setImageResource(dealerFirstCard.getImageUrl());
//        Card playerFirstCard = playerHand.get(0);
//        playerFirstCardView.setImageResource(playerFirstCard.getImageUrl());
//        Card playerSecondCard = playerHand.get(1);
//        playerSecondCardView.setImageResource(playerSecondCard.getImageUrl());
        playerHandAdapter.notifyDataSetChanged();
        dealerHandAdapter.refresh(dealerHand);

        playerHandAdapter.insertItem(playerHand.get(0));
        playerHandAdapter.insertItem(playerHand.get(1));


        if(player.hasBlackJack() || dealer.hasBlackJack()){
            gameFinish();
        } else {
            hitBtn.setVisibility(View.VISIBLE);
            standBtn.setVisibility(View.VISIBLE);
        }
        //notifyDataSetChange()
    }

    private void gameFinish() {
        hitBtn.setVisibility(View.INVISIBLE);
        standBtn.setVisibility(View.INVISIBLE);
        play.setVisibility(View.INVISIBLE);
        gameResult.setText(blackjack.whoWon().toString());
    }

    private void dealerTurnOverHoleCard(){
        Log.d(getClass().toString(), "Checking if dealer has all cards visible or not");
        dealerHand = blackjack.getDealer().getPlayerHand();
//        //this is always failing - WHYYY
//        if (dealerHoleCardView.getDrawable() == getResources().getDrawable(R.drawable.dealer_card_back)) {
//
//            Log.d(getClass().toString(), "Dealer hole card being turned over");
//            dealerHoleCardView.setImageResource(dealerHand.get(1).getImageUrl());
//        }
        dealerHoleCardView.setImageResource(dealerHand.get(1).getImageUrl());
    }

    private void newTurn(){
        if (dealer.calculateHandValue() < 17) {
            dealer.drawCard(dealer.dealCard());
        }
    }

    public void hit(View view) {
        player.drawCard(dealer.dealCard());
        playerHand = blackjack.getPlayer().getPlayerHand();

        //change this so it's not a direct index
        playerHandAdapter.insertItem(playerHand.get(2));
        dealerTurnOverHoleCard();
        if (player.isBust() || player.hasBlackJack() || dealer.hasBlackJack()) {
            gameFinish();
        }
    }

    public void stand(View view) {

    }
}

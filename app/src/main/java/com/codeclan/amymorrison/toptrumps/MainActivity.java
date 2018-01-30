package com.codeclan.amymorrison.toptrumps;

import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button play;
    Button hitBtn;
    Button standBtn;
    ImageButton playerBetBtn;
    TextView playerBetView;
    TextView placeBetLabel;
    Toolbar actionBar;

    Blackjack blackjack;
    Player player;
    Dealer dealer;

    ArrayList<Card> playerHand;
    ArrayList<Card> dealerHand;

    RecyclerView playerCardListDisplay;
    LinearLayoutManager layoutManager;
    CardListAdapter playerHandAdapter;
    DealerCardAdapter dealerHandAdapter;

    GridView dealerCardDisplay;
    TextView gameResult;
    TextView bank;
    int dealerHoleCardTrueValue;
    boolean hasDealerRevealedHoleCard;
    int currentBet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       actionBar = findViewById(R.id.bet_toolbar);
//
        setSupportActionBar(actionBar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        play = findViewById(R.id.play_btn);
        hitBtn = findViewById(R.id.hit_btn);
        standBtn = findViewById(R.id.stand_btn);
        gameResult = findViewById(R.id.who_won_txtView);

        placeBetLabel = findViewById(R.id.place_bet_txtView);
        playerHand = new ArrayList<>();
        dealerHand = new ArrayList<>();
//        dealerHand.add(new Card(Suit.DIAMONDS, Rank.TEN, CardImage.EIGHTSPADES));
        dealerHandAdapter = new DealerCardAdapter(this, dealerHand);
        dealerCardDisplay = findViewById(R.id.dealer_card_grid);
        dealerCardDisplay.setAdapter(dealerHandAdapter);
//        playerFirstCardView = findViewById(R.id.player_firstCardImage);
//        playerSecondCardView = findViewById(R.id.player_secondCardImage);
        playerBetBtn = findViewById(R.id.bet_button_view);

        playerBetView = findViewById(R.id.playerBetView);
        playerBetView.setText(String.format("£%d",0));
        bank = findViewById(R.id.playerBankView);
        bank.setText(String.format("£%d",0));
        player = new Player();
        blackjack = new Blackjack(player);
        dealer = blackjack.getDealer();




        Log.d(getClass().toString(),"Fragment is being created");
//        playerHand.add(new Card(Suit.CLUBS, Rank.ACE, CardImage.EIGHTSPADES));
//        playerHand.add(new Card(Suit.DIAMONDS, Rank.EIGHT, CardImage.ACEHEARTS));
//        View rootView = inflater.inflate(R.layout.fragment_card_view, container, false);
        playerCardListDisplay = findViewById(R.id.card_list_view_in_fragment);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false );

        playerHandAdapter = new CardListAdapter(this, playerHand);
//        dealerHandAdapter = new CardListAdapter(this, dealerHand);
        playerCardListDisplay.setAdapter(playerHandAdapter);
        playerCardListDisplay.setLayoutManager(layoutManager);
        playerCardListDisplay.setNestedScrollingEnabled(false);
//        playerCardListDisplay.addItemDecoration(new HorizontalSpaceItemDecoration);

//        layoutManager.canScrollHorizontally(false);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    public void play(View view) {
        if (Integer.valueOf(playerBetView.getText().toString().substring(1)) > 5) {

            play.setVisibility(View.INVISIBLE);

            blackjack.shuffleDeck();
            blackjack.initialDeal();
            dealerHand = blackjack.getDealer().getPlayerHand();
            playerHand = blackjack.getPlayer().getPlayerHand();
            dealerHoleCardTrueValue = dealerHand.get(1).setImageUrl(R.drawable.dealer_card_back);
            //only one of these images should be visible right now, but stay the same card, change the resource! confused

            dealerHandAdapter.refresh(dealerHand);
            //why am i doing this?
            playerHandAdapter.refresh(playerHand);


            if (player.hasBlackJack() || dealer.hasBlackJack()) {
                gameFinish();
            } else {
                hitBtn.setVisibility(View.VISIBLE);
                standBtn.setVisibility(View.VISIBLE);
            }
            hasDealerRevealedHoleCard = false;
        } else {
            Toast.makeText(MainActivity.this, "Place your bet!",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void gameFinish() {
        hitBtn.setVisibility(View.INVISIBLE);
        standBtn.setVisibility(View.INVISIBLE);
        play.setVisibility(View.INVISIBLE);
        gameResult.setVisibility(View.VISIBLE);
        gameResult.setText(blackjack.whoWon().toString());
        Player winner = blackjack.getWinner();
        if (winner.equals(blackjack.getPlayer())){
            int bet = Integer.valueOf(playerBetView.getText().toString().substring(1));
            int banked = Integer.valueOf(bank.getText().toString().substring(1));
            bank.setText(String.format("£%d",banked+bet));

        }
    }

//    @Override
//    public void setActionBar(String heading) {
//        // TODO Auto-generated method stub
//
//        com.codeclan.amymorrison.toptrumps.ActionBar actionBar = getSupportActionBar();
//
//        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.title_bar_gray)));
//        actionBar.setTitle(heading);
//        actionBar.show();
//
//    }

    private boolean dealerTurnOverHoleCard(){
        Log.d(getClass().toString(), "Checking if dealer has all cards visible or not");

        //WHY NOT PERMANENTLY?!?
        dealerHand.get(1).setImageUrl(dealerHoleCardTrueValue);
        dealerHandAdapter.refresh(dealerHand);
//        //this is always failing - WHYYY
//        if (dealerHoleCardView.getDrawable() == getResources().getDrawable(R.drawable.dealer_card_back)) {
//
//            Log.d(getClass().toString(), "Dealer hole card being turned over");
//            dealerHoleCardView.setImageResource(dealerHand.get(1).getImageUrl());
//        }
        //dealerHoleCardView.setImageResource(dealerHand.get(1).getImageUrl());
        return true;
    }

    private void newTurn(){

        if (player.isBust()) {
            gameFinish();
        }

        if (dealer.calculateHandValue() < 17) {
            dealer.drawCard(dealer.dealCard());
            dealerHandAdapter.refresh(dealer.getPlayerHand());
        }
        if (dealer.isBust()){
            gameFinish();
        }

    }

    public void hit(View view) {
        player.drawCard(dealer.dealCard());
        playerHand = blackjack.getPlayer().getPlayerHand();
        playerHandAdapter.refresh(playerHand);
        if (!dealerTurnOverHoleCard()) {
            dealerTurnOverHoleCard();
            hasDealerRevealedHoleCard = true;
        }
        if (player.isBust() || player.hasBlackJack()) {
            //doesnt work
            gameFinish();
        }
    }

    public void stand(View view) {

        if (!dealerTurnOverHoleCard()) {
            dealerTurnOverHoleCard();
            hasDealerRevealedHoleCard = true;
        }

        if (player.isBust()) {
            gameFinish();
        }

        while (dealer.calculateHandValue() < 17) {
            dealer.drawCard(dealer.dealCard());
            dealerHandAdapter.refresh(dealer.getPlayerHand());
        }
        gameFinish();
    }

    public void raiseBet(View view) {
        currentBet = Integer.valueOf(playerBetView.getText().toString().substring(1));
        playerBetView.setText(String.format("£%d",currentBet+5));
    }

    public void new_game(MenuItem item) {
        //blackjack.newDeck();
        player.emptyHand();
        dealer.emptyHand();
        playerHand = blackjack.getPlayer().getPlayerHand();
        dealerHand = blackjack.getDealer().getPlayerHand();
        playerHandAdapter.refresh(playerHand);
        dealerHandAdapter.refresh(dealerHand);
        gameResult.setVisibility(View.INVISIBLE);
        placeBetLabel.setVisibility(View.VISIBLE);
        playerBetView.setText(String.format("£%d",0));
        play.setVisibility(View.VISIBLE);


    }
}

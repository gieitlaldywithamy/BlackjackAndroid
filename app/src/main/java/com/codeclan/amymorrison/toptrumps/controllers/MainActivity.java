package com.codeclan.amymorrison.toptrumps.controllers;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import com.codeclan.amymorrison.toptrumps.R;
import com.codeclan.amymorrison.toptrumps.deck.Card;
import com.codeclan.amymorrison.toptrumps.gamelogic.Blackjack;
import com.codeclan.amymorrison.toptrumps.gamelogic.Dealer;
import com.codeclan.amymorrison.toptrumps.gamelogic.Player;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button play;
    Button hitBtn;
    Button standBtn;
    Animation animation;

    ImageButton playerBetBtn;
    Button doubleBtn;
    TextView playerBetView;
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
    TextView bank;
    TextView cash;
    int dealerHoleCardTrueValue;
    boolean hasDealerRevealedHoleCard;

    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialiseViews();
        toggleInGameButtons();

        blackjack = new Blackjack();
        player = blackjack.getPlayer();
        dealer = blackjack.getDealer();

        initialiseToolbar();

        dealerHandAdapter = new DealerCardAdapter(this, dealer.getPlayerHand());
        dealerCardDisplay.setAdapter(dealerHandAdapter);

        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false );
        playerHandAdapter = new CardListAdapter(this, player.getPlayerHand());
        playerCardListDisplay.setAdapter(playerHandAdapter);
        playerCardListDisplay.setLayoutManager(layoutManager);
        playerCardListDisplay.setNestedScrollingEnabled(false);
        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
        animation.setFillAfter(true);

    }

    private void initialiseViews(){

        //player input
        play = findViewById(R.id.play_btn);
        hitBtn = findViewById(R.id.hit_btn);
        standBtn = findViewById(R.id.stand_btn);
        playerBetBtn = findViewById(R.id.bet_button_view);
        doubleBtn = findViewById(R.id.double_down_btn);


        //toolbar
        bank = findViewById(R.id.playerBankView);
        cash = findViewById(R.id.playerCashView);
        playerBetView = findViewById(R.id.playerBetView);

        //card lists
        dealerCardDisplay = findViewById(R.id.dealer_card_grid);
        playerCardListDisplay = findViewById(R.id.card_list_view_in_fragment);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    public void initialiseToolbar(){
        actionBar = findViewById(R.id.bet_toolbar);
        setSupportActionBar(actionBar);
        //hides default title
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        sharedPref = getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        int wonSoFar = sharedPref.getInt("winnings", 0);
        bank.setText(String.format("£%d",wonSoFar));
        cash.setText(String.format("£%d", player.getWallet()));
        playerBetView.setText(String.format("£%d",0));
    }

    public void updateToolbar(){
        int playerBanked = sharedPref.getInt("winnings", 0);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putInt("winnings", playerBanked+player.getWinnings());
        editor.apply();

        int playerCurrentBet = player.getBet();
        int playerWallet = player.getWallet();
        playerBanked = sharedPref.getInt("winnings", 0);



        playerBetView.setText(String.format("£%d",playerCurrentBet));
        bank.setText(String.format("£%d", playerBanked));
        cash.setText(String.format("£%d", playerWallet));
    }

    public void play(View view) {
        if (player.getBet() >= 5) {

            play.setVisibility(View.INVISIBLE);
            toggleMoneyButtonsVisiblity();

            blackjack.shuffleDeck();
            blackjack.initialDeal();
            dealerHand = dealer.getPlayerHand();
            playerHand = player.getPlayerHand();
//            dealerHoleCardTrueValue = dealerHand.get(1).setImageUrl(R.drawable.dealer_card_back);
            //only one of these images should be visible right now, but stay the same card, change the resource! confused
            hasDealerRevealedHoleCard = false;
            dealerHandAdapter.refresh(dealerHand);
            playerHandAdapter.refresh(playerHand);

            if (player.hasBlackJack() || dealer.hasBlackJack()) {
                dealerTurnOverHoleCard();
                gameFinish();
            } else {
                hitBtn.setVisibility(View.VISIBLE);
                standBtn.setVisibility(View.VISIBLE);
                doubleBtn.setVisibility(View.VISIBLE);
//                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        switch (which){
//                            case DialogInterface.BUTTON_POSITIVE:
//                                double_down();
//                                hitBtn.performClick();
//                                gameFinish();
//                                break;
//
//                            case DialogInterface.BUTTON_NEGATIVE:
//                                //doubleBtn.setVisibility(View.INVISIBLE);
//                                break;
//                        }
//                    }
//                };
//
//                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
//                builder.setMessage("Double down?").setPositiveButton("Yes", dialogClickListener)
//                        .setNegativeButton("No", dialogClickListener).show();
            }

        } else {
            Toast.makeText(MainActivity.this, "Place your bet!",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void gameFinish() {
        hitBtn.setVisibility(View.INVISIBLE);
        standBtn.setVisibility(View.INVISIBLE);
        play.setVisibility(View.INVISIBLE);

        Player winner = blackjack.getWinner();
        String result = winner.toString() + " won!";
        Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG).show();
        if (winner.equals(player)){

            player.increaseWinnings((player.getBet()*3/2)+player.getBet());
            updateToolbar();
        }

    }

    private void dealerTurnOverHoleCard(){

        dealer.turnOverHiddenCard();
//        //this is always failing - WHYYY
//        if (dealerHoleCardView.getDrawable() == getResources().getDrawable(R.drawable.dealer_card_back)) {

        this.hasDealerRevealedHoleCard = true;
        dealerHand = dealer.getPlayerHand();
        dealerHandAdapter.refresh(dealerHand);
    }


    public void hit(View view) {
        doubleBtn.setVisibility(View.INVISIBLE);
        blackjack.playerHit();
        playerHand = player.getPlayerHand();
        playerHandAdapter.refresh(playerHand);


        if (!hasDealerRevealedHoleCard) {
            dealerTurnOverHoleCard();
        }
        if (player.isBust() || player.hasBlackJack()) {
            gameFinish();
        }
    }

    public void stand(View view) {
        doubleBtn.setVisibility(View.INVISIBLE);
        if (!hasDealerRevealedHoleCard) {
            dealerTurnOverHoleCard();
        }
        blackjack.playerStand();

        dealerHandAdapter.refresh(dealer.getPlayerHand());

        gameFinish();
    }

    public void raiseBet(View view) {
        player.raiseBet(5);
        playerBetBtn.startAnimation(animation);

        updateToolbar();
    }

    public void toggleMoneyButtonsVisiblity(){
        playerBetBtn.setVisibility(View.INVISIBLE);
    }

    public void toggleInGameButtons(){
        hitBtn.setVisibility(View.INVISIBLE);
        standBtn.setVisibility(View.INVISIBLE);
        doubleBtn.setVisibility(View.INVISIBLE);
    }

    public void new_game(MenuItem item) {
        toggleInGameButtons();
        blackjack.newGame();
        playerHand = player.getPlayerHand();
        dealerHand = dealer.getPlayerHand();
        playerHandAdapter.refresh(playerHand);
        dealerHandAdapter.refresh(dealerHand);
        updateToolbar();
        play.setVisibility(View.VISIBLE);
        playerBetBtn.setVisibility(View.VISIBLE);
    }

    public void player_cash_out(MenuItem item) {
        int playerBanked = sharedPref.getInt("winnings", 0);
        SharedPreferences.Editor editor = sharedPref.edit();
        player.profit(playerBanked);
        editor.putInt("winnings", 0);
        editor.apply();
        updateToolbar();


    }

    public void double_down(View view) {
        player.doubleDown();

        updateToolbar();
        hitBtn.performClick();
        standBtn.performClick();
    }
}

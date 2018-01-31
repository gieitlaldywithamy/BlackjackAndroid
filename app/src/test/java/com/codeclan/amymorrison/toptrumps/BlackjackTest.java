package com.codeclan.amymorrison.toptrumps;

import com.codeclan.amymorrison.toptrumps.deck.Card;
import com.codeclan.amymorrison.toptrumps.deck.CardImage;
import com.codeclan.amymorrison.toptrumps.deck.Rank;
import com.codeclan.amymorrison.toptrumps.deck.Suit;
import com.codeclan.amymorrison.toptrumps.gamelogic.Blackjack;
import com.codeclan.amymorrison.toptrumps.gamelogic.Dealer;
import com.codeclan.amymorrison.toptrumps.gamelogic.Player;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by amymorrison on 27/01/2018.
 */

public class BlackjackTest {

    Blackjack game;
    Player player;
    Dealer dealer;
    Card three;
    Card eight;
    Card ten;

    @Before
    public void before(){



        game = new Blackjack();
        dealer = game.getDealer();
        player = game.getPlayer();
        three = new Card(Suit.CLUBS, Rank.THREE, CardImage.THREECLUBS);
        eight = new Card(Suit.HEARTS, Rank.EIGHT, CardImage.EIGHTSPADES);
        ten = new Card(Suit.DIAMONDS, Rank.TEN, CardImage.TENCLUBS);

    }

    @Test
    public void canGetPlayerValue(){
        game.initialDeal();
        assertEquals(12, player.handValue());

    }

    @Test
    public void canGetHandScore(){
        game.initialDeal();
        assertEquals(12, player.handValue());
    }

    @Test
    public void canGetBlackjack(){
        player.drawCard(three);
        player.drawCard(eight);
        player.drawCard(ten);
        assertEquals(player, game.getWinner());
    }

    @Test
    public void startNewGame(){
        player.drawCard(three);
        player.drawCard(ten);
        game.newGame();
        assertEquals(0, player.getPlayerHand().size());
    }

}

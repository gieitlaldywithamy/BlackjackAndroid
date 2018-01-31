package com.codeclan.amymorrison.toptrumps;

import com.codeclan.amymorrison.toptrumps.deck.Card;
import com.codeclan.amymorrison.toptrumps.deck.CardImage;
import com.codeclan.amymorrison.toptrumps.deck.Deck;
import com.codeclan.amymorrison.toptrumps.deck.Rank;
import com.codeclan.amymorrison.toptrumps.deck.Suit;
import com.codeclan.amymorrison.toptrumps.gamelogic.Dealer;
import com.codeclan.amymorrison.toptrumps.gamelogic.Player;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by amymorrison on 30/01/2018.
 */

public class PlayerTest {

    Dealer dealer;
    Card card1;
    Card card2;
    Card card3;
    Card ace1;
    Card ace2;
    Player player;

    @Before
    public void before() {
        dealer = new Dealer(new Deck());
        card1 = new Card(Suit.HEARTS, Rank.NINE, CardImage.NINEHEARTS);
        card2 = new Card(Suit.SPADES, Rank.JACK, CardImage.JACKSPADES);
        card3 = new Card(Suit.HEARTS, Rank.EIGHT, CardImage.EIGHTHEARTS);
        ace1 = new Card(Suit.DIAMONDS, Rank.ACE, CardImage.ACEDIAMONDS);
        ace2 = new Card(Suit.SPADES, Rank.ACE, CardImage.ACESPADES);
        player = new Player();
    }

    @Test
    public void handDoesntContainsAce(){
        dealer.drawCard(card2);
        assertEquals(false, dealer.handContainsAce());
    }

    @Test
    public void handContainsAce(){
        dealer.drawCard(card1);
        dealer.drawCard(ace1);
        dealer.drawCard(card2);
        dealer.drawCard(ace2);
        dealer.drawCard(card1);
        assertTrue(dealer.handContainsAce());
    }

    @Test
    public void canCountAces() {
        dealer.drawCard(card1);
        dealer.drawCard(ace1);
        dealer.drawCard(card2);
        dealer.drawCard(ace2);
        dealer.drawCard(card1);
        assertEquals(3, player.countAces());
    }

    @Test
    public void playerCanDrawCard(){
        player.drawCard(card2);
        assertEquals(1, player.getPlayerHand().size());
    }

    @Test
    public void playerHasValue17(){
        player.drawCard(card1);
        player.drawCard(card3);
        assertEquals(17, player.calculateHandValue());
    }

    @Test
    public void playerIsBust(){
        player.drawCard(card1);
        player.drawCard(card2);
        player.drawCard(card3);
        assertTrue(player.isBust());
    }

    @Test
    public void playerIsntBustBeecauseAce(){
        player.drawCard(card1);
        player.drawCard(card2);
        player.drawCard(ace2);
        assertFalse(player.isBust());
    }

    @Test
    public void playerMultipleAceValue(){
        player.drawCard(card1);
        player.drawCard(card2);
        player.drawCard(ace2);
        player.drawCard(ace1);
        assertFalse(player.isBust());
    }

    @Test
    public void canDoubleDown(){
        player.drawCard(ace1);
        player.drawCard(ace2);
    }


}

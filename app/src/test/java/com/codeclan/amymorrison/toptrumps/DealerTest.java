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

/**
 * Created by amymorrison on 27/01/2018.
 */

public class DealerTest {

    Dealer dealer;
    Card card1;
    Card card2;
    Card card3;
    Card ace1;
    Card ace2;
    Player player;

    @Before
    public void before(){
        dealer = new Dealer(new Deck());
        card1 = new Card(Suit.HEARTS, Rank.NINE, CardImage.NINEHEARTS);
        card2 = new Card(Suit.SPADES, Rank.JACK, CardImage.JACKSPADES);
        card3 = new Card(Suit.HEARTS, Rank.EIGHT, CardImage.EIGHTHEARTS);
        ace1 = new Card(Suit.DIAMONDS, Rank.ACE, CardImage.ACEDIAMONDS);
        ace2 = new Card(Suit.SPADES, Rank.ACE, CardImage.ACESPADES);
        player = new Player();
    }

    @Test
    public void canGetDealerPlayerScore(){
        assertEquals(0, dealer.handValue());
    }

    @Test
    public void canCountCards(){
        assertEquals(52, dealer.getDeck().getCards().size());
    }

    @Test
    public void canDealCard(){
        Deck sampleDeck = new Deck();
        Card firstCard = sampleDeck.getCards().getFirst();
        assertEquals(firstCard.prettyName(), dealer.dealCard().prettyName());
    }

    @Test
    public void canDeal2CardsToPlayer(){
        player.drawCard(dealer.dealCard());
        player.drawCard(dealer.dealCard());
        assertEquals(2, player.getPlayerHand().size());
    }

    @Test
    public void dealerCanHit(){
        dealer.drawCard(ace1);
        assertEquals(11, dealer.handValue());
    }

    @Test
    public void dealerStopsDrawingAfter16(){
        dealer.drawCard(card1);
        dealer.drawCard(card2);
        assertEquals(false, dealer.shouldHit());
    }}

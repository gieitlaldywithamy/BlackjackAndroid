package com.codeclan.amymorrison.toptrumps;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;

/**
 * Created by amymorrison on 26/01/2018.
 */

public class DeckTest {

    Deck cardDeck;

    @Before
    public void before() {
        cardDeck = new Deck();
    }

    @Test
    public void canGetCards(){
        cardDeck.getCards();
    }

    @Test
    public void canGetDeckSize(){
        assertEquals(52, cardDeck.getSize());
    }

    @Test
    public void deckHasImages(){

        assertEquals(R.drawable.clubstwo, cardDeck.getCards().get(6).getImageUrl());
    }

    @Test
    public void deckCanDealCard(){
        Card topCard = new Card(Suit.values()[0], Rank.values()[0], CardImage.values()[1]);
        assertEquals(topCard.getValue(), cardDeck.removeFirst().getValue());
        assertEquals(51, cardDeck.getSize());
    }

    @Test
    public void canShuffleDeck(){
        cardDeck.shuffle();
        assertFalse(cardDeck.equals(new Deck()));
    }

    @Test
    public void deckHasFourAces(){
        int count = 0;
        for (Card card: cardDeck.getCards()){
            if (card.getRank().equals(Rank.ACE)){
                count++;
            }
        }
        assertEquals(4, count);
    }
}

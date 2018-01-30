package com.codeclan.amymorrison.toptrumps.deck;

import com.codeclan.amymorrison.toptrumps.R;

/**
 * Created by amymorrison on 26/01/2018.
 */


public enum CardImage {

    ACEHEARTS(R.drawable.ace_of_hearts),
    ACESPADES(R.drawable.ace_of_spades),
    ACECLUBS(R.drawable.ace_of_clubs),
    ACEDIAMONDS(R.drawable.ace_of_diamonds),
    TWOHEARTS(R.drawable.twohearts),
    TWOSPADES(R.drawable.twospades),
    TWOCLUBS(R.drawable.clubstwo),
    TWODIAMONDS(R.drawable.twohearts),
    THREEHEARTS(R.drawable.threehearts),
    THREESPADES(R.drawable.threespades),
    THREECLUBS(R.drawable.threeclubs),
    THREEDIAMONDS(R.drawable.threediamonds),
    FOURHEARTS(R.drawable.fourhearts),
    FOURSPADES(R.drawable.fourspades),
    FOURCLUBS(R.drawable.fourclubs),
    FOURDIAMONDS(R.drawable.fourdiamonds),
    FIVEHEARTS(R.drawable.fivehearts),
    FIVESPADES(R.drawable.fivespades),
    FIVECLUBS(R.drawable.fiveclubs),
    FIVEDIAMONDS(R.drawable.fivediamonds),
    SIXHEARTS(R.drawable.sixhearts),
    SIXSPADES(R.drawable.sixspades),
    SIXCLUBS(R.drawable.sixclubs),
    SIXDIAMONDS(R.drawable.sixdiamonds),
    SEVENHEARTS(R.drawable.sevenhearts),
    SEVENSPADES(R.drawable.sevenspades),
    SEVENCLUBS(R.drawable.sevenclubs),
    SEVENDIAMONDS(R.drawable.sevendiamonds),
    EIGHTHEARTS(R.drawable.eighthearts),
    EIGHTSPADES(R.drawable.eightspades),
    EIGHTCLUBS(R.drawable.eightclubs),
    EIGHTDIAMONDS(R.drawable.eightdiamonds),
    NINEHEARTS(R.drawable.ninehearts),
    NINESPADES(R.drawable.ninespades),
    NINECLUBS(R.drawable.nineclubs),
    NINEDIAMONDS(R.drawable.ninediamonds),
    TENHEARTS(R.drawable.tenhearts),
    TENSPADES(R.drawable.tenspades),
    TENCLUBS(R.drawable.tenclubs),
    TENDIAMONDS(R.drawable.tenhearts),
    JACKHEARTS(R.drawable.jack_of_hearts),
    JACKSPADES(R.drawable.jack_of_spades),
    JACKCLUBS(R.drawable.jack_of_clubs),
    JACKDIAMONDS(R.drawable.ace_of_diamonds),
    QUEENHEARTS(R.drawable.queen_of_hearts),
    QUEENSPADES(R.drawable.queen_of_spades),
    QUEENCLUBS(R.drawable.queen_of_clubs),
    QUEENDIAMONDS(R.drawable.queen_of_diamonds),
    KINGHEARTS(R.drawable.king_of_hearts),
    KINGSPADES(R.drawable.king_of_hearts),
    KINGCLUBS(R.drawable.king_of_hearts),
    KINGDIAMONDS(R.drawable.king_of_hearts);

    private int drawableImageSrc;

    CardImage(int drawableID) {
        this.drawableImageSrc = drawableID;
    }

    public int getImageId() {
        return this.drawableImageSrc;
    }

}

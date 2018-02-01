package com.codeclan.amymorrison.toptrumps.controllers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.codeclan.amymorrison.toptrumps.R;
import com.codeclan.amymorrison.toptrumps.deck.Card;

import java.util.ArrayList;

/**
 * Created by amymorrison on 29/01/2018.
 */

public class DealerCardAdapter extends BaseAdapter {

    private final Context mContext;
    private ArrayList<Card> cards;

    public DealerCardAdapter(Context context, ArrayList<Card> cards) {
        mContext = context;
        this.cards = cards;
    }

    @Override
    public int getCount() {
        return this.cards.size();
    }

    @Override
    public Object getItem(int position) {
        return this.cards.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //a bit hacky...
    public void refresh(ArrayList<Card> cards)
    {
        this.cards = cards;
        notifyDataSetChanged();
    }


    @Override
    public View getView(int position, View gridItemView, ViewGroup parent) {
        ImageView dealerCardView;
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (gridItemView == null){
            gridItemView = inflater.inflate(R.layout.dealer_card_item, parent, false);
        }

        Card currentCard = cards.get(position);
        dealerCardView = gridItemView.findViewById(R.id.dealer_card_image_view);

        int drawable = mContext.getResources().getIdentifier(currentCard.toFileString(), "drawable", mContext.getPackageName());
        dealerCardView.setImageResource(drawable);


//        dealerCardView.setImageResource(currentCard.getImageUrl());
        return dealerCardView;
    }
}

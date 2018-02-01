package com.codeclan.amymorrison.toptrumps.controllers;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.codeclan.amymorrison.toptrumps.R;
import com.codeclan.amymorrison.toptrumps.deck.Card;

import java.util.ArrayList;

/**
 * Created by amymorrison on 29/01/2018.
 */

public class CardListAdapter extends RecyclerView.Adapter<CardListAdapter.ViewHolder> {
    private ArrayList<Card> cards;
    private LayoutInflater mInflator;
    public CardListAdapter(Context context, ArrayList<Card> cardList) {
        this.cards = cardList;
        Log.d(getClass().toString(), Integer.toString(this.cards.size()));
        this.mInflator = LayoutInflater.from(context);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
//        Card currentCard = getItem(position);
//
//        ImageView card = listItemView.findViewById(R.id.card_list_item);
//        card.setImageResource(currentCard.getImageUrl());

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Card currentCard = cards.get(position);
        holder.cardImageView.setImageResource(currentCard.getImageUrl());
    }

    public void refresh(ArrayList<Card> cards)
    {
        this.cards = cards;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return this.cards.size();
    }

    public void insertItem(Card newCard){
        cards.add(newCard);
        notifyItemInserted(getItemCount());
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView cardImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            cardImageView = itemView.findViewById(R.id.card_list_item);
        }
    }
}

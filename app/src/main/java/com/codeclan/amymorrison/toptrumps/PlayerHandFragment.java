package com.codeclan.amymorrison.toptrumps;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by amymorrison on 29/01/2018.
 */

public class PlayerHandFragment extends Fragment {

    ArrayList<Card> playerHand;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle = this.getArguments();
        playerHand = new ArrayList<>();
        Log.d(getClass().toString(),"Fragment is being created");
        playerHand.add(new Card(Suit.CLUBS, Rank.ACE, CardImage.EIGHTSPADES));
        playerHand.add(new Card(Suit.DIAMONDS, Rank.EIGHT, CardImage.ACEHEARTS));
        View rootView = inflater.inflate(R.layout.fragment_card_view, container, false);
        RecyclerView cardList = rootView.findViewById(R.id.card_list_view_in_fragment);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false );
        CardListAdapter cardListAdapter = new CardListAdapter(getActivity(), playerHand);
        cardList.setAdapter(cardListAdapter);
        cardList.setLayoutManager(layoutManager);
        return rootView;
    }
}

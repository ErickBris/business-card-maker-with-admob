package com.restuibu.mycardbox;

import java.util.ArrayList;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.restuibu.mycardbox.adapter.CardboxAdapter;
import com.restuibu.mycardbox.model.Card;
import com.restuibu.mycardbox.util.LayoutHelper;

public class CardboxFragment extends Fragment {

	public static ListView list;
	public static CardboxAdapter adapter;

	@Override
	public View onCreateView(LayoutInflater inflater,

	ViewGroup container, Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.alertdialog_list, container,
				false);

		//add interstitial
		if (MainActivity.mInterstitialAd.isLoaded()) {
			MainActivity.mInterstitialAd.show();
		}

		list = (ListView) rootView.findViewById(R.id.listView1);
		CardboxFragment.displayCardBox(getActivity());
		return rootView;

	}

	public static void displayCardBox(Context c) {
		ArrayList<Card> cards = new ArrayList<Card>();
		cards = LayoutHelper.getCards(c);
		CardboxFragment.adapter = new CardboxAdapter(c, cards);
		// Inflate the layout for this fragment
		CardboxFragment.list.setAdapter(adapter);
	}

}

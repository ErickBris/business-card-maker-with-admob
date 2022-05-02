package com.restuibu.mycardbox;

import java.util.ArrayList;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import at.technikum.mti.fancycoverflow.FancyCoverFlow;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.restuibu.mycardbox.adapter.FancyCoverFlowSampleAdapter;
import com.restuibu.mycardbox.util.LayoutHelper;

public class CoverflowFragment extends Fragment {

	@SuppressWarnings("deprecation")
	@Override
	public View onCreateView(LayoutInflater inflater,

	ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_coverflow,
				container, false);

		// add interstitial
		if (MainActivity.mInterstitialAd.isLoaded()) {
			MainActivity.mInterstitialAd.show();
		}
		MainActivity.mInterstitialAd.setAdListener(new AdListener() {
			@Override
			public void onAdClosed(){
				LayoutHelper.loadInterstitial(getActivity());				
			}
		});

		// ad banner
		AdView mAdView = (AdView) rootView.findViewById(R.id.adView);
		AdRequest adRequest = new AdRequest.Builder().build();
		mAdView.loadAd(adRequest);

		
		// Inflate the layout for this fragment

		FancyCoverFlow fancyCoverFlow = (FancyCoverFlow) rootView
				.findViewById(R.id.fancyCoverFlow);

		ArrayList<Bitmap> images = LayoutHelper.getCardBitmaps(getActivity());

		DisplayMetrics metrics = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay()
				.getMetrics(metrics);

		int height = metrics.heightPixels;
		int width = metrics.widthPixels;

		fancyCoverFlow.setAdapter(new FancyCoverFlowSampleAdapter(
				getActivity(), images, height, width));
		fancyCoverFlow.setReflectionEnabled(true);
		fancyCoverFlow.setReflectionRatio(0.4f);
		fancyCoverFlow.setUnselectedAlpha(1.0f);
		fancyCoverFlow.setUnselectedSaturation(0.0f);
		fancyCoverFlow.setUnselectedScale(0.5f);
		fancyCoverFlow.setSpacing(50);
		fancyCoverFlow.setMaxRotation(0);
		fancyCoverFlow.setScaleDownGravity(0.2f);
		fancyCoverFlow.setActionDistance(FancyCoverFlow.ACTION_DISTANCE_AUTO);
		return rootView;
	}

}

package com.restuibu.mycardbox;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.restuibu.mycardbox.util.LayoutHelper;

public class AddCardFragment extends Fragment {

	public static int currentLayoutType;
	public static Button bAddCard;
	public static EditText etFullname, etJobTitle, etPhone, etEmail, etAddress,
			etCompany;
	public static LinearLayout llBrowseLayout;
	public static TextView fullName, jobTitle, phone, email, address, company;

	@Override
	public View onCreateView(LayoutInflater inflater,

	ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_add_card, container,
				false);

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
		AddCardFragment.currentLayoutType = 0;
		llBrowseLayout = (LinearLayout) rootView
				.findViewById(R.id.linearLayout1);

		bAddCard = (Button) rootView.findViewById(R.id.button1);
		etFullname = (EditText) rootView.findViewById(R.id.editText1);
		etJobTitle = (EditText) rootView.findViewById(R.id.editText2);
		etPhone = (EditText) rootView.findViewById(R.id.editText3);
		etEmail = (EditText) rootView.findViewById(R.id.editText4);
		etAddress = (EditText) rootView.findViewById(R.id.editText5);
		etCompany = (EditText) rootView.findViewById(R.id.editText6);

		llBrowseLayout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				LayoutHelper.alertPickTemplate(getActivity());
			}
		});

		bAddCard.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (!etFullname.getText().toString().isEmpty()
						&& !etFullname.getText().toString().isEmpty()
						&& !etPhone.getText().toString().isEmpty()
						&& !etEmail.getText().toString().isEmpty()
						&& !etAddress.getText().toString().isEmpty()
						&& !etCompany.getText().toString().isEmpty())

					LayoutHelper.alertInputCardname(getActivity());
				else {
					Toast.makeText(getActivity(),
							"Please fill in all the field", Toast.LENGTH_SHORT)
							.show();
				}

			}
		});

		// ViewGroup viewGroup =
		// (ViewGroup)rootView.findViewById(R.id.linearLayout1);
		// viewGroup.addView(LayoutInflater.from(getActivity()).inflate(
		// R.layout.template_one, null, true));

		etFullname.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub
				fullName.setText(arg0);
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub

			}
		});

		etJobTitle.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub
				jobTitle.setText(arg0);
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub

			}
		});

		etPhone.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub
				phone.setText(arg0);
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub

			}
		});

		etEmail.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub
				email.setText(arg0);
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub

			}
		});

		etAddress.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub
				address.setText(arg0);
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub

			}
		});

		etCompany.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub
				company.setText(arg0);
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub

			}
		});

		return rootView;
	}
}

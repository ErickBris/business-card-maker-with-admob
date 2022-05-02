package com.restuibu.mycardbox;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.restuibu.mycardbox.model.Card;
import com.restuibu.mycardbox.model.MySQLiteHelper;
import com.restuibu.mycardbox.util.LayoutHelper;

public class EditActivity extends Activity {
	public static int currentLayoutType;
	public static Button bAddCard;
	public static EditText etFullname, etJobTitle, etPhone, etEmail, etAddress,
			etCompany;
	public static LinearLayout llBrowseLayout;
	public static TextView fullName, jobTitle, phone, email, address, company;
	private MySQLiteHelper db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_add_card);
		
		ActionBar actionBar = getActionBar();
		getActionBar().setIcon(android.R.color.transparent);
		actionBar.setBackgroundDrawable(new ColorDrawable(getResources()
				.getColor(R.color.blue_)));
		// get string extra from clicked card in cardbox
		final Intent intent = getIntent();
		db = new MySQLiteHelper(EditActivity.this);

		llBrowseLayout = (LinearLayout) findViewById(R.id.linearLayout1);
		bAddCard = (Button) findViewById(R.id.button1);
		etFullname = (EditText) findViewById(R.id.editText1);
		etJobTitle = (EditText) findViewById(R.id.editText2);
		etPhone = (EditText) findViewById(R.id.editText3);
		etEmail = (EditText) findViewById(R.id.editText4);
		etAddress = (EditText) findViewById(R.id.editText5);
		etCompany = (EditText) findViewById(R.id.editText6);

		// initialize layout
		LayoutHelper.initializeCardLayout(EditActivity.this,
				Integer.parseInt(intent.getStringExtra("layoutType")));
		LayoutHelper.enableInputCard(EditActivity.this);

		// textwatcher
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

		etFullname.setText(intent.getStringExtra("fullName"));
		etJobTitle.setText(intent.getStringExtra("jobTitle"));
		etPhone.setText(intent.getStringExtra("phone"));
		etEmail.setText(intent.getStringExtra("email"));
		etAddress.setText(intent.getStringExtra("address"));
		etCompany.setText(intent.getStringExtra("company"));

		bAddCard.setText("Update");
		bAddCard.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (!etFullname.getText().toString().isEmpty()
						&& !etFullname.getText().toString().isEmpty()
						&& !etPhone.getText().toString().isEmpty()
						&& !etEmail.getText().toString().isEmpty()
						&& !etAddress.getText().toString().isEmpty()
						&& !etCompany.getText().toString().isEmpty()) {

					Card card = new Card();
					card.setEtFullname(etFullname.getText().toString());
					card.setEtJobTitle(etJobTitle.getText().toString());
					card.setEtPhone(etPhone.getText().toString());
					card.setEtEmail(etEmail.getText().toString());
					card.setEtAddress(etAddress.getText().toString());
					card.setEtCompany(etCompany.getText().toString());
					card.setCardName(intent.getStringExtra("cardName"));
					db.updateCard(card);
					LayoutHelper.saveImage(EditActivity.this, LayoutHelper
							.getLayoutScreenShot(EditActivity.llBrowseLayout),
							intent.getStringExtra("cardName"));

					// refresh list in cardbox immediately
					CardboxFragment.displayCardBox(EditActivity.this);

					finish();

				} else {
					Toast.makeText(EditActivity.this,
							"Please fill in all the field", Toast.LENGTH_SHORT)
							.show();
				}

			}
		});

	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		finish();
	}

}

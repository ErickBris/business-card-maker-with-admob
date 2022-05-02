package com.restuibu.mycardbox.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.restuibu.mycardbox.AddCardFragment;
import com.restuibu.mycardbox.CardboxFragment;
import com.restuibu.mycardbox.EditActivity;
import com.restuibu.mycardbox.MainActivity;
import com.restuibu.mycardbox.R;
import com.restuibu.mycardbox.adapter.CardTemplateAdapter;
import com.restuibu.mycardbox.asynctask.SendEmailAsyncTask;
import com.restuibu.mycardbox.model.Card;
import com.restuibu.mycardbox.model.CardTemplate;
import com.restuibu.mycardbox.model.MySQLiteHelper;

public class LayoutHelper {
	public static void zoomCard(Context c, Bitmap imgSrc){
		LayoutInflater inflater = LayoutInflater.from(c);
		View dialogview = inflater.inflate(R.layout.alertdialog_zoom,
				null);
		final ScaleImageView imageView1 = (ScaleImageView) dialogview
				.findViewById(R.id.imageView1);
		imageView1.setImageBitmap(imgSrc);

		final AlertDialog alert = new AlertDialog.Builder(c)
				.create();
		alert.setView(dialogview);
		alert.show();
	}

	public static Bitmap getLayoutScreenShot(LinearLayout layout) {
		Bitmap bitmap = Bitmap.createBitmap(layout.getWidth(),
				layout.getHeight(), Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);
		layout.draw(canvas);
		return bitmap;
	}

	public static void shareToSocMed(Context c, String cardName) {
		Intent sharingIntent = new Intent(Intent.ACTION_SEND);

		String APPLICATION_PACKAGE_NAME = c.getPackageName();
		String path = Environment.getExternalStorageDirectory() + "/"
				+ APPLICATION_PACKAGE_NAME + "/" + cardName + ".png";

		Uri screenshotUri = Uri.parse(path);

		sharingIntent.setType("image/png");
		sharingIntent.putExtra(Intent.EXTRA_STREAM, screenshotUri);
		c.startActivity(Intent.createChooser(sharingIntent, "Share card using"));
	}

	public static void sendEmail(final Context c, final String cardName,
			final String fullName) {
		LayoutInflater inflater = LayoutInflater.from(c);
		View dialogview = inflater.inflate(R.layout.alertdialog_input_email,
				null);
		final AlertDialog alert = new AlertDialog.Builder(c).create();

		final EditText eEmail = (EditText) dialogview
				.findViewById(R.id.editText1);
		final Button bSend = (Button) dialogview.findViewById(R.id.button1);
		final Button bCancel = (Button) dialogview.findViewById(R.id.button2);

		bSend.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (eEmail.getText().toString().isEmpty()) {
					Toast.makeText(c, "Please fill in your target email(s)",
							Toast.LENGTH_LONG).show();
				} else {
					new SendEmailAsyncTask(c).execute(eEmail.getText()
							.toString(), cardName, fullName);
					alert.dismiss();
				}

			}
		});

		bCancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				alert.dismiss();
			}
		});

		alert.setView(dialogview);
		alert.show();
	}

	public static void initializeCardLayout(Context c, int type) {
		// TODO Auto-generated method stub
		int template = 0;

		switch (type) {
		case 1:
			template = R.layout.template_one;
			break;
		case 2:
			template = R.layout.template_two;
			break;
		case 3:
			template = R.layout.template_three;
			break;
		}

		LayoutInflater inflaterCard = LayoutInflater.from(c);

		View cardLayout = inflaterCard.inflate(template, null, false);

		if (c instanceof EditActivity) {
			EditActivity.llBrowseLayout.removeAllViews();

			EditActivity.llBrowseLayout.addView(cardLayout,
					EditActivity.llBrowseLayout.getLayoutParams().width,
					EditActivity.llBrowseLayout.getLayoutParams().height);

			EditActivity.currentLayoutType = type;
			EditActivity.fullName = (TextView) cardLayout
					.findViewById(R.id.textView1);
			EditActivity.jobTitle = (TextView) cardLayout
					.findViewById(R.id.textView2);
			EditActivity.phone = (TextView) cardLayout
					.findViewById(R.id.textView3);
			EditActivity.email = (TextView) cardLayout
					.findViewById(R.id.textView4);
			EditActivity.address = (TextView) cardLayout
					.findViewById(R.id.textView5);
			EditActivity.company = (TextView) cardLayout
					.findViewById(R.id.textView6);
		} else if (c instanceof MainActivity) {
			AddCardFragment.llBrowseLayout.removeAllViews();

			AddCardFragment.llBrowseLayout.addView(cardLayout,
					AddCardFragment.llBrowseLayout.getLayoutParams().width,
					AddCardFragment.llBrowseLayout.getLayoutParams().height);

			AddCardFragment.currentLayoutType = type;
			AddCardFragment.fullName = (TextView) cardLayout
					.findViewById(R.id.textView1);
			AddCardFragment.jobTitle = (TextView) cardLayout
					.findViewById(R.id.textView2);
			AddCardFragment.phone = (TextView) cardLayout
					.findViewById(R.id.textView3);
			AddCardFragment.email = (TextView) cardLayout
					.findViewById(R.id.textView4);
			AddCardFragment.address = (TextView) cardLayout
					.findViewById(R.id.textView5);
			AddCardFragment.company = (TextView) cardLayout
					.findViewById(R.id.textView6);
		}

	}

	public static void enableInputCard(Context c) {
		if (c instanceof MainActivity) {
			AddCardFragment.etFullname.setEnabled(true);
			AddCardFragment.etJobTitle.setEnabled(true);
			AddCardFragment.etPhone.setEnabled(true);
			AddCardFragment.etEmail.setEnabled(true);
			AddCardFragment.etAddress.setEnabled(true);
			AddCardFragment.etCompany.setEnabled(true);
			AddCardFragment.bAddCard.setEnabled(true);
		} else if (c instanceof EditActivity) {
			EditActivity.etFullname.setEnabled(true);
			EditActivity.etJobTitle.setEnabled(true);
			EditActivity.etPhone.setEnabled(true);
			EditActivity.etEmail.setEnabled(true);
			EditActivity.etAddress.setEnabled(true);
			EditActivity.etCompany.setEnabled(true);
			EditActivity.bAddCard.setEnabled(true);
		}
	}

	public static void alertPickTemplate(Context c) {
		LayoutInflater inflater = LayoutInflater.from(c);
		View dialogview = inflater.inflate(R.layout.alertdialog_list, null);
		final AlertDialog alert = new AlertDialog.Builder(c).create();

		
		TypedArray templates = c.getResources().obtainTypedArray(
				R.array.card_layout_templates);
		String[] templateNames = c.getResources().getStringArray(
				R.array.card_layout_names);

		ArrayList<CardTemplate> cardTemplates = new ArrayList<CardTemplate>();

		// add templates to list
		for (int i = 0; i < templateNames.length; i++) {
			cardTemplates.add(new CardTemplate(templateNames[i], i + 1,
					templates.getResourceId(i, -1)));
		}

		CardTemplateAdapter adapter = new CardTemplateAdapter(c, cardTemplates,
				alert);

		ListView lvLayoutTemplate = (ListView) dialogview
				.findViewById(R.id.listView1);
		lvLayoutTemplate.setAdapter(adapter);

		alert.setView(dialogview);
		alert.show();
	}

	public static ArrayList<Bitmap> getCardBitmaps(Context c) {
		ArrayList<Bitmap> images = new ArrayList<Bitmap>();

		String APPLICATION_PACKAGE_NAME = c.getPackageName();
		File path = new File(Environment.getExternalStorageDirectory(),
				APPLICATION_PACKAGE_NAME);

		for (File f : path.listFiles()) {
			if (f == null) {
				break;
			} else if (f.isFile()) {
				Bitmap myBitmap = BitmapFactory.decodeFile(f.getAbsolutePath());

				images.add(myBitmap);
			}

		}
		return images;
	}

	public static ArrayList<Card> getCards(Context c) {
		ArrayList<Card> cards = new ArrayList<Card>();
		MySQLiteHelper helper = new MySQLiteHelper(c);

		String APPLICATION_PACKAGE_NAME = c.getPackageName();
		File path = new File(Environment.getExternalStorageDirectory(),
				APPLICATION_PACKAGE_NAME);

		for (File f : path.listFiles()) {
			if (f == null) {
				break;
			} else if (f.isFile()) {
				Bitmap myBitmap = BitmapFactory.decodeFile(f.getAbsolutePath());
				String name = f.getName().split(Pattern.quote("."))[0];
				Card card = helper.getDetailCard(name);
				card.setCardBitmap(myBitmap);

				cards.add(card);
			}

		}
		return cards;
	}

	public static boolean deleteCardBitmap(Context c, String cardName) {
		String APPLICATION_PACKAGE_NAME = c.getPackageName();
		File path = new File(Environment.getExternalStorageDirectory(),
				APPLICATION_PACKAGE_NAME);

		for (File f : path.listFiles()) {
			if (f == null) {
				break;
			} else if (f.isFile()) {
				String name = f.getName().split(Pattern.quote("."))[0];

				if (name.equals(cardName)) {
					if (f.delete()) {
						return true;
					}
				}
			}

		}
		return false;
	}

	public static boolean isExistCardname(Context c, String name) {
		String APPLICATION_PACKAGE_NAME = c.getPackageName();
		File path = new File(Environment.getExternalStorageDirectory(),
				APPLICATION_PACKAGE_NAME);

		if (!path.exists()) {
			path.mkdir();
		}
		
		for (File f : path.listFiles()) {
			if (f == null) {
				break;
			} else if (f.isFile()) {

				if (f.getName().equals(name + ".png"))
					return true;
			}

		}
		return false;
	}

	public static void alertInputCardname(final Context c) {
		LayoutInflater inflater = LayoutInflater.from(c);
		View dialogview = inflater.inflate(R.layout.alertdialog_cardname, null);
		final AlertDialog alert = new AlertDialog.Builder(c).create();

		final EditText eCardname = (EditText) dialogview
				.findViewById(R.id.editText1);
		final Button bSave = (Button) dialogview.findViewById(R.id.button1);
		final Button bCancel = (Button) dialogview.findViewById(R.id.button2);

		bSave.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (eCardname.getText().toString().equals("")) {
					Toast.makeText(c, eCardname.getText().toString(),
							Toast.LENGTH_SHORT).show();
				} else {
					if (LayoutHelper.isExistCardname(c, eCardname.getText()
							.toString())) {
						Toast.makeText(c, "Name Card already exist",
								Toast.LENGTH_SHORT).show();
					} else {
						MySQLiteHelper db = new MySQLiteHelper(c);

						Card card = new Card();
						// card.setLayoutType()
						card.setCardName(eCardname.getText().toString());
						card.setLayoutType(AddCardFragment.currentLayoutType);
						card.setEtFullname(AddCardFragment.fullName.getText()
								.toString());
						card.setEtJobTitle(AddCardFragment.jobTitle.getText()
								.toString());
						card.setEtPhone(AddCardFragment.phone.getText()
								.toString());
						card.setEtEmail(AddCardFragment.email.getText()
								.toString());
						card.setEtAddress(AddCardFragment.address.getText()
								.toString());
						card.setEtCompany(AddCardFragment.company.getText()
								.toString());
						card.setCreatedDate(new SimpleDateFormat(
								"yyyy-MM-dd HH:mm:ss").format(new Date()));

						db.addCard(card);

						LayoutHelper.saveImage(
								c,
								LayoutHelper
										.getLayoutScreenShot(AddCardFragment.llBrowseLayout),
								eCardname.getText().toString());
					}

					alert.dismiss();
				}

			}
		});

		bCancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				alert.dismiss();
			}
		});

		alert.setView(dialogview);
		alert.show();
	}

	public static void alertDeleteConfirmation(final Context c,
			final List<Card> cards, final String cardName, final int position) {

		LayoutInflater inflater = LayoutInflater.from(c);
		View dialogview = inflater.inflate(
				R.layout.alertdialog_delete_confirmation, null);
		final AlertDialog alert = new AlertDialog.Builder(c).create();

		final Button bDelete = (Button) dialogview.findViewById(R.id.button1);
		final Button bCancel = (Button) dialogview.findViewById(R.id.button2);

		bDelete.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				MySQLiteHelper helper = new MySQLiteHelper(c);
				helper.deleteCard(cardName);

				// delete card in db
				cards.remove(position);
				CardboxFragment.adapter.notifyDataSetChanged();

				// delete card bitmap in local storage
				LayoutHelper.deleteCardBitmap(c, cardName);
				alert.dismiss();

			}
		});

		bCancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				alert.dismiss();
			}
		});

		alert.setView(dialogview);
		alert.show();

	}

	public static void saveImage(Context c, Bitmap b, String name) {
		try {
			String APPLICATION_PACKAGE_NAME = c.getPackageName();
			File path = new File(Environment.getExternalStorageDirectory(),
					APPLICATION_PACKAGE_NAME);

			if (!path.exists()) {
				path.mkdir();
			}
			File file = new File(path, name + ".png");
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			BufferedOutputStream bos = new BufferedOutputStream(
					fileOutputStream);

			// choose another format if PNG doesn't suit you
			b.compress(CompressFormat.PNG, 100, bos);

			bos.flush();
			bos.close();

			Toast.makeText(c, "Card Added", Toast.LENGTH_SHORT).show();

		} catch (FileNotFoundException e) {
			Log.w("TAG", "Error saving image file: " + e.getMessage());

		} catch (IOException e) {
			Log.w("TAG", "Error saving image file: " + e.getMessage());

		}
	}
	
	public static void loadInterstitial(Context c){
		MainActivity.mInterstitialAd = new InterstitialAd(c);
		MainActivity.mInterstitialAd.setAdUnitId(c.getResources().getString(
				R.string.interstitial_ad_unit_id));

		AdRequest adRequest = new AdRequest.Builder().build();
		MainActivity.mInterstitialAd.loadAd(adRequest);
	}
}

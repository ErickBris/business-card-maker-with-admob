package com.restuibu.mycardbox.model;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MySQLiteHelper extends SQLiteOpenHelper {
	// Database Version
	private static final int DATABASE_VERSION = 1;
	// Database Name
	private static final String DATABASE_NAME = "BusinessCardMakerDb";
	public static final String TBL_CARD = "tbl_card";

	private Context context;

	public MySQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.context = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// SQL statement to create book table

		String TBL_CARD = "CREATE TABLE IF NOT EXISTS TBL_CARD ("
				+ " ID INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ " layoutType INTEGER NOT NULL,"
				+ " cardName VARCHAR(50) NOT NULL,"
				+ " fullname VARCHAR(50) NOT NULL,"
				+ " jobTitle VARCHAR(30) NOT NULL,"
				+ " phone VARCHAR(15) NOT NULL,"
				+ " email VARCHAR(50) NOT NULL," + " address TEXT NOT NULL,"
				+ " company TEXT NOT NULL," + " createdDate DATETIME NOT NULL)";

		// create table
		db.execSQL(TBL_CARD);

	}

	public void updateCard(Card card) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL("UPDATE TBL_CARD SET fullname='" + card.getEtFullname()
				+ "',jobTitle='" + card.getEtJobTitle() + "',phone='"
				+ card.getEtPhone() + "',email='" + card.getEtEmail()
				+ "',address='" + card.getEtAddress() + "',company='"
				+ card.getEtCompany() + "' WHERE cardName='"
				+ card.getCardName() + "'");
		Toast.makeText(context, "Card updated", Toast.LENGTH_SHORT).show();
	}

	public Card getDetailCard(String cardName) {
		String query = "SELECT * FROM TBL_CARD WHERE cardName='" + cardName
				+ "'";

		// 2. get reference to writable DB
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(query, null);

		// 3. go over each row, build book and add it to list
		Card Card = null;
		if (cursor.moveToFirst()) {
			do {
				Card = new Card(Integer.parseInt(cursor.getString(1)),
						cursor.getString(2), cursor.getString(3),
						cursor.getString(4), cursor.getString(5),
						cursor.getString(6), cursor.getString(7),
						cursor.getString(8), cursor.getString(9));

			} while (cursor.moveToNext());
		}

		return Card;

	}

	//
	public void addCard(Card Card) {
		SQLiteDatabase db = this.getWritableDatabase();

		if (!isExist(Card)) {
			ContentValues values = new ContentValues();

			values.put("layoutType", Card.getLayoutType());
			values.put("cardName", Card.getCardName());
			values.put("fullname", Card.getEtFullname());
			values.put("jobTitle", Card.getEtJobTitle());
			values.put("phone", Card.getEtPhone());
			values.put("email", Card.getEtEmail());
			values.put("address", Card.getEtAddress());
			values.put("company", Card.getEtCompany());
			values.put("createdDate", Card.getCreatedDate());

			db.insert(TBL_CARD, null, values);

		} else {
			Toast.makeText(context, "Card already exist", Toast.LENGTH_SHORT)
					.show();
		}
		db.close();
	}

	public boolean isExist(Card card) {
		ArrayList<Card> Cards = getAllCard();

		for (Card Card : Cards) {
			if (Card.getCardName().equals(card.getCardName())) {
				return true;
			}
		}
		return false;

	}

	public ArrayList<Card> getAllCard() {
		ArrayList<Card> Cards = new ArrayList<Card>();

		String query = "SELECT * FROM TBL_CARD ORDER BY ID DESC";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(query, null);

		Card Card = null;
		if (cursor.moveToFirst()) {
			do {
				Card = new Card(Integer.parseInt(cursor.getString(1)),
						cursor.getString(2), cursor.getString(3),
						cursor.getString(4), cursor.getString(5),
						cursor.getString(6), cursor.getString(7),
						cursor.getString(8), cursor.getString(9));
				Cards.add(Card);

			} while (cursor.moveToNext());
		}

		return Cards;
	}

	public void deleteCard(String cardName) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL("DELETE FROM TBL_CARD WHERE cardName = '" + cardName + "'");
		Toast.makeText(context, "Card deleted", Toast.LENGTH_SHORT).show();

	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

}

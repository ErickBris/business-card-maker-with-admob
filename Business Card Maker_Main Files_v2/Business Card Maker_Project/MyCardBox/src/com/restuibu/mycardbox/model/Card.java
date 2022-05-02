package com.restuibu.mycardbox.model;

import android.graphics.Bitmap;

public class Card {
	private int layoutType;
	private String cardName;

	private String etFullname;
	private String etJobTitle;
	private String etPhone;
	private String etEmail;
	private String etAddress;
	private String etCompany;
	
	private Bitmap cardBitmap;

	public Bitmap getCardBitmap() {
		return cardBitmap;
	}

	public void setCardBitmap(Bitmap cardBitmap) {
		this.cardBitmap = cardBitmap;
	}

	private String createdDate;

	public Card(int layoutType, String cardName, String etFullname,
			String etJobTitle, String etPhone, String etEmail,
			String etAddress, String etCompany, String createdDate) {
		super();
		this.layoutType = layoutType;
		this.cardName = cardName;
		this.etFullname = etFullname;
		this.etJobTitle = etJobTitle;
		this.etPhone = etPhone;
		this.etEmail = etEmail;
		this.etAddress = etAddress;
		this.etCompany = etCompany;
		this.createdDate = createdDate;
	}

	public Card() {
		// TODO Auto-generated constructor stub
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public int getLayoutType() {
		return layoutType;
	}

	public void setLayoutType(int layoutType) {
		this.layoutType = layoutType;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public String getEtFullname() {
		return etFullname;
	}

	public void setEtFullname(String etFullname) {
		this.etFullname = etFullname;
	}

	public String getEtJobTitle() {
		return etJobTitle;
	}

	public void setEtJobTitle(String etJobTitle) {
		this.etJobTitle = etJobTitle;
	}

	public String getEtPhone() {
		return etPhone;
	}

	public void setEtPhone(String etPhone) {
		this.etPhone = etPhone;
	}

	public String getEtEmail() {
		return etEmail;
	}

	public void setEtEmail(String etEmail) {
		this.etEmail = etEmail;
	}

	public String getEtAddress() {
		return etAddress;
	}

	public void setEtAddress(String etAddress) {
		this.etAddress = etAddress;
	}

	public String getEtCompany() {
		return etCompany;
	}

	public void setEtCompany(String etCompany) {
		this.etCompany = etCompany;
	}

}

package com.restuibu.mycardbox.model;

public class CardTemplate {
	private String name;
	private int layoutType;
	private int img;

	public CardTemplate(String name, int layoutType, int img) {
		super();
		this.name = name;
		this.layoutType = layoutType;
		this.img = img;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLayoutType() {
		return layoutType;
	}

	public void setLayoutType(int layoutType) {
		this.layoutType = layoutType;
	}

	public int getImg() {
		return img;
	}

	public void setImg(int img) {
		this.img = img;
	}

}

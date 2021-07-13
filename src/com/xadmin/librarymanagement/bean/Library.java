package com.xadmin.librarymanagement.bean;

public class Library {
	
	protected int id;
	protected String title;
	protected String artist;
	protected String category;
	
	public Library() {
	}
	
	public Library(String title, String artist, String category) {
		super();
		this.title = title;
		this.artist = artist;
		this.category = category;
	}

	public Library(int id, String title, String artist, String category) {
		super();
		this.id = id;
		this.title = title;
		this.artist = artist;
		this.category = category;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	
}

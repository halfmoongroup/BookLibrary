package com.hmg.demo.app.booklibrary.api;

public class Book {

	private String title;
	private String authors;
	private Integer published;
	private String bookId;
	private String originalPublicationYear;
	private String imageUrl;
	private String smallImageUrl;
	
	
	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getAuthors() {
		return authors;
	}


	public void setAuthors(String authors) {
		this.authors = authors;
	}


	public Integer getPublished() {
		return published;
	}


	public void setPublished(Integer published) {
		this.published = published;
	}


	public String getBookId() {
		return bookId;
	}


	public void setBookId(String bookId) {
		this.bookId = bookId;
	}


	public String getOriginalPublicationYear() {
		return originalPublicationYear;
	}


	public void setOriginalPublicationYear(String originalPublicationYear) {
		this.originalPublicationYear = originalPublicationYear;
	}


	public String getImageUrl() {
		return imageUrl;
	}


	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}


	public String getSmallImageUrl() {
		return smallImageUrl;
	}


	public void setSmallImageUrl(String smallImageUrl) {
		this.smallImageUrl = smallImageUrl;
	}


	public boolean validateForCreate() {
		
		return true;
	}

}

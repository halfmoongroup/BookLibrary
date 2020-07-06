package com.hmg.demo.app.booklibrary.api;

public class Book {

	private String title;
	private String publisher;
	private String author;
	private Integer published;
	private String bookId;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
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
	
	public boolean validateForCreate() {
		
		return true;
	}

}

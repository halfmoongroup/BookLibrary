package com.hmg.demo.app.booklibrary.api;

import java.util.ArrayList;
import java.util.List;

public class BookList {
	private  int count;
	private List<Book> books;
	
	public BookList() {
		books = new ArrayList<Book>();
	}
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	public void addBook(Book aBook) {
		this.books.add(aBook);
		this.count = this.books.size();
	}
	public List<Book> getBooks() {
		return books;
	}
	public void setBooks(List<Book> books) {
		this.books = books;
	}
	
}

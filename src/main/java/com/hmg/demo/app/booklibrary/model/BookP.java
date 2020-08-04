package com.hmg.demo.app.booklibrary.model;

import java.lang.reflect.InvocationTargetException;

import org.apache.cayenne.validation.ValidationResult;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.RandomStringUtils;

import com.hmg.demo.app.booklibrary.api.Book;
import com.hmg.demo.app.booklibrary.model.auto._BookP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BookP extends _BookP {
	private static Logger log = LoggerFactory.getLogger(BookP.class);

    private static final long serialVersionUID = 1L;

	public ValidationResult populate(Book book) {
		ValidationResult vResult = new ValidationResult();
		String bookId = RandomStringUtils.randomAlphanumeric(8);
		
		try {
			BeanUtils.copyProperties(this, book);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		this.setBookId(bookId);
		this.validateForInsert(vResult);
		return vResult;
	}


	public Book getApiBook() {
		Book aBook = new Book();
		try {
			BeanUtils.copyProperties(aBook, this);
			if (aBook.getLanguageCode() == null) {
				aBook.setLanguageCode("en");
			}
		} catch (Exception e) {
			log.info("problem copying properties");
		} 
		return aBook;
	}


	@Override
	protected void onPostAdd() {
		this.setActive(true);
	} 

}

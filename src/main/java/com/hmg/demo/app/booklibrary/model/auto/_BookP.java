package com.hmg.demo.app.booklibrary.model.auto;

import org.apache.cayenne.CayenneDataObject;
import org.apache.cayenne.exp.Property;

/**
 * Class _BookP was generated by Cayenne.
 * It is probably a good idea to avoid changing this class manually,
 * since it may be overwritten next time code is regenerated.
 * If you need to make any customizations, please use subclass.
 */
public abstract class _BookP extends CayenneDataObject {

    private static final long serialVersionUID = 1L; 

    public static final String BOOK_ID_PK_COLUMN = "book_id";

    public static final Property<Boolean> ACTIVE = Property.create("active", Boolean.class);
    public static final Property<String> AUTHOR = Property.create("author", String.class);
    public static final Property<String> BOOK_ID = Property.create("bookId", String.class);
    public static final Property<String> IMAGE_URL = Property.create("imageUrl", String.class);
    public static final Property<String> LANGUAGE_CODE = Property.create("languageCode", String.class);
    public static final Property<Integer> ORIGINAL_PUBLICATION_YEAR = Property.create("originalPublicationYear", Integer.class);
    public static final Property<String> SMALL_IMAGE_URL = Property.create("smallImageUrl", String.class);
    public static final Property<String> TITLE = Property.create("title", String.class);

    public void setActive(Boolean active) {
        writeProperty("active", active);
    }
    public Boolean getActive() {
        return (Boolean)readProperty("active");
    }

    public void setAuthor(String author) {
        writeProperty("author", author);
    }
    public String getAuthor() {
        return (String)readProperty("author");
    }

    public void setBookId(String bookId) {
        writeProperty("bookId", bookId);
    }
    public String getBookId() {
        return (String)readProperty("bookId");
    }

    public void setImageUrl(String imageUrl) {
        writeProperty("imageUrl", imageUrl);
    }
    public String getImageUrl() {
        return (String)readProperty("imageUrl");
    }

    public void setLanguageCode(String languageCode) {
        writeProperty("languageCode", languageCode);
    }
    public String getLanguageCode() {
        return (String)readProperty("languageCode");
    }

    public void setOriginalPublicationYear(int originalPublicationYear) {
        writeProperty("originalPublicationYear", originalPublicationYear);
    }
    public int getOriginalPublicationYear() {
        Object value = readProperty("originalPublicationYear");
        return (value != null) ? (Integer) value : 0;
    }

    public void setSmallImageUrl(String smallImageUrl) {
        writeProperty("smallImageUrl", smallImageUrl);
    }
    public String getSmallImageUrl() {
        return (String)readProperty("smallImageUrl");
    }

    public void setTitle(String title) {
        writeProperty("title", title);
    }
    public String getTitle() {
        return (String)readProperty("title");
    }

    protected abstract void onPostAdd();

}

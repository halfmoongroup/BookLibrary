package com.hmg.demo.app.booklibrary.resources;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.configuration.server.ServerRuntime;
import org.apache.cayenne.query.ObjectSelect;
import org.apache.cayenne.validation.ValidationFailure;
import org.apache.cayenne.validation.ValidationResult;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.hmg.demo.app.booklibrary.api.Book;
import com.hmg.demo.app.booklibrary.api.BookError;
import com.hmg.demo.app.booklibrary.api.BookList;
import com.hmg.demo.app.booklibrary.model.BookP;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Info;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

@Path("/library")
@Api(tags= {"Book Management"})
@SwaggerDefinition(
		info = @Info(
				description = "This is a sample booklibrary server.", 
				version = "1.0.0", 
				title = "Book Library"),
		tags= {@Tag(name="Book Library Resources", description="manage books")},
		schemes = SwaggerDefinition.Scheme.HTTP
		)
public class LibraryResource {

	private Provider<ServerRuntime> runtimeProvider;

	@Inject
	public LibraryResource(Provider<ServerRuntime> runtimeProvider) {
		this.runtimeProvider = runtimeProvider;
	}
	
	@GET
	@Path("/books")
	@Produces("application/json")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Get a book instance", response = BookList.class ),
		@ApiResponse(code = 400, message = "Invalid book id supplied", response = String.class)
	})
	public Response getBooks() {
		Response r = null;
		ServerRuntime runtime = runtimeProvider.get();
		ObjectContext context = runtime.newContext();
		List<BookP> bookPList = ObjectSelect.query(BookP.class)
				.where(BookP.ACTIVE.eq(true)).select(context);
		BookList bookList = new BookList();
		if ((bookPList != null) && (bookPList.size() > 0)) {
 			for(BookP aBook : bookPList) {
 				bookList.addBook(aBook.getApiBook());
			}
		}
		r = Response.status(Response.Status.OK).entity(bookList).build();
		return r;
	}
	
	@GET
	@Path("/book/{id}")
	@Produces("application/json")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Get a book instance", response = Book.class ),
		@ApiResponse(code = 400, message = "Invalid book id supplied", response = BookError.class),
		@ApiResponse(code = 404, message = "Book not found", response = BookError.class) 
		})
	public Response getBook(@PathParam("id") String id) {
		Response r = null;
		ServerRuntime runtime = runtimeProvider.get();
		ObjectContext context = runtime.newContext();
		BookP aBook = ObjectSelect.query(BookP.class).where(BookP.BOOK_ID.eq(id))
				.and(BookP.ACTIVE.eq(true)).selectOne(context);
		if (aBook == null) {
			BookError error = new BookError(Response.Status.NOT_FOUND.getStatusCode(),"Book not found" );
			r = Response.status(Response.Status.NOT_FOUND).entity(error).build();
		} else {
			Book apiBook = aBook.getApiBook();
			r = Response.status(Response.Status.OK).entity(apiBook).build();
		}
		return r;
	}
	
	@DELETE
	@Path("/book/{id}")
	@Produces("application/json")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Get a book instance", response = Book.class ),
		@ApiResponse(code = 400, message = "Invalid book id supplied", response = BookError.class),
		@ApiResponse(code = 404, message = "Book not found", response = BookError.class) 
		})
	public Response deleteBook(@PathParam("id") String id) {
		Response r = null;
		ServerRuntime runtime = runtimeProvider.get();
		ObjectContext context = runtime.newContext();
		BookP aBook = ObjectSelect.query(BookP.class).where(BookP.BOOK_ID.eq(id))
				.and(BookP.ACTIVE.eq(true)).selectOne(context);
		if (aBook == null) {
			BookError error = new BookError(Response.Status.NOT_FOUND.getStatusCode(),"Book not found" );
			r = Response.status(Response.Status.NOT_FOUND).entity(error).build();
		} else {
			Book apiBook = aBook.getApiBook();
			context.deleteObject(aBook);
			context.commitChanges();
			apiBook.setBookId(null);
			r = Response.status(Response.Status.OK).entity(apiBook).build();
		}
		return r;
	}

	@POST
	@Path("/book")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiResponses({
		@ApiResponse( code = 200, message = "Post a book instance", response = Book.class),
		@ApiResponse(code = 400, message = "Invalid book supplied", response = BookError.class),
		@ApiResponse(code = 406, message = "Do not set bookid on creation", response = BookError.class)
		})
	public Response putBook(Book book) {
		Response r = null;
		if (book.validateForCreate()) {
			ServerRuntime runtime = runtimeProvider.get();
			ObjectContext context = runtime.newContext();
			ValidationResult vResult;
			BookP newBook = context.newObject(BookP.class);
			vResult = newBook.populate(book);
			if (!vResult.hasFailures()) {
				context.commitChanges();
				Book aBook = newBook.getApiBook();
				r = Response.status(Response.Status.OK).entity(aBook).build();
			} else {
				context.rollbackChanges();
				StringBuilder builder = new StringBuilder();
				for (ValidationFailure verror: vResult.getFailures()) {
					builder.append(verror.getDescription());
				}
				BookError error = new BookError(Response.Status.BAD_REQUEST.getStatusCode(),builder.toString());
				r = Response.status(Response.Status.BAD_REQUEST).entity(error).build();

			}
		}else {
			BookError error = new BookError(Response.Status.NOT_ACCEPTABLE.getStatusCode(),"Do not set bookid on creation");
			r = Response.status(Response.Status.NOT_ACCEPTABLE).entity(error).build();
		}

		return r;
	}
		
}

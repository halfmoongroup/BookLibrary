package com.hmg.demo.app.booklibrary.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.configuration.server.ServerRuntime;
import org.apache.cayenne.exp.Expression;
import org.apache.cayenne.exp.Property;
import org.apache.cayenne.query.SelectQuery;
import org.apache.commons.lang3.StringUtils;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.hmg.demo.app.booklibrary.api.Book;
import com.hmg.demo.app.booklibrary.api.BookList;
import com.hmg.demo.app.booklibrary.api.BookSearch;
import com.hmg.demo.app.booklibrary.model.BookP;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Info;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

@Path("/search")
@Api(tags = { "Book Search" })
@SwaggerDefinition(info = @Info(description = "This is a sample booklibrary search service.", version = "1.0.0", title = "Book Library"), tags = {
		@Tag(name = "Book Library Resources", description = "search books") }, schemes = SwaggerDefinition.Scheme.HTTP)
public class SearchResource {



	private Provider<ServerRuntime> runtimeProvider;

	@Inject
	public SearchResource(Provider<ServerRuntime> runtimeProvider) {
		this.runtimeProvider = runtimeProvider;
	}

	@POST
	@Path("/books")
	@Produces("application/json")
	@ApiResponses({ @ApiResponse(code = 200, message = "Get a book instance", response = BookList.class),
			@ApiResponse(code = 400, message = "Invalid book id supplied", response = String.class) })
	public Response searchBooks(BookSearch queryBook) {
		Expression expression = null;
		ServerRuntime runtime = runtimeProvider.get();
		ObjectContext context = runtime.newContext();
		if (StringUtils.isNotBlank(queryBook.getTitle())) {
			expression = andFieldExpression(expression, BookP.TITLE,  queryBook.getTitle());
		}
		if (StringUtils.isNotBlank(queryBook.getAuthor())) {
			expression = andFieldExpression(expression, BookP.AUTHOR,  queryBook.getAuthor());
		}
		SelectQuery<BookP> bookQuery = new SelectQuery<BookP>(BookP.class,expression);
		
		@SuppressWarnings("unchecked")
		List<BookP> books =  context.performQuery(bookQuery);
		List<Book> apiBooks = new ArrayList<>();
		for(BookP item : books) {
			apiBooks.add(item.getApiBook());
		}
		return Response.status(Response.Status.OK).entity(apiBooks).build();
	}

	Expression andFieldExpression(Expression e, Property<String> key, String value) {
		boolean isLike = value.contains("%");
		if (e != null) {
			if (isLike) {
				return e.andExp(key.likeIgnoreCase(value));
			} else {
				return e.andExp(key.lower().eq(value.toLowerCase()));
			}
		} else if (isLike) {
			return key.like(value);
		} else {
			return key.lower().eq(value.toLowerCase());
		}

	}
}

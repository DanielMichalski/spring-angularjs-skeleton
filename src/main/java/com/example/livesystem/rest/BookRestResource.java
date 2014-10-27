package com.example.livesystem.rest;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.livesystem.JsonViews;
import com.example.livesystem.entity.Book;
import com.example.livesystem.repo.BookRepository;
import com.example.livesystem.security.UserSecurityService;

@Component
@Path("/books")
public class BookRestResource {
	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private ObjectMapper mapper;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String findAll() throws JsonGenerationException,
			JsonMappingException, IOException {
		ObjectWriter viewWriter;
		if (UserSecurityService.isAdmin()) {
			viewWriter = mapper.writerWithView(JsonViews.Admin.class);
		} else {
			viewWriter = mapper.writerWithView(JsonViews.User.class);
		}
		List<Book> allEntries = bookRepository.findAll();

		return viewWriter.writeValueAsString(allEntries);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Book findOne(@PathParam("id") Long id) {
		Book book = bookRepository.findOne(id);
		if (book == null) {
			throw new WebApplicationException(404);
		}
		return book;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Book create(Book book) {
		return bookRepository.save(book);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Book update(@PathParam("id") Integer id, Book book) {
		return bookRepository.save(book);
	}

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public void delete(@PathParam("id") Long id) {
		bookRepository.delete(id);
	}

}

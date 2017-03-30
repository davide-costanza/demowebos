package it.extrasys.studio.rest;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import it.extrasys.studio.model.entity.BookEntity;
import it.extrasys.studio.model.manager.BookManager;

@Controller
@RequestMapping("/basic-rest")
public class BasicSpringRestController {
	private static final String TEMPLATE = "Wonder %s!";
	
	private final AtomicLong counter = new AtomicLong();

	@Autowired
	private BookManager bookManager;
	
	// http://localhost:8080/demowebos/basic-rest
	// http://localhost:8080/demowebos/basic-rest?name=article
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody BookEntity sayHello(
			@RequestParam(value = "name", required = false, defaultValue = "book") String name) {
		BookEntity book = new BookEntity(String.format(TEMPLATE, name), "Unknown" + counter.incrementAndGet());

		BookEntity resBook = bookManager.save(book);
		
		return resBook;
	}
}

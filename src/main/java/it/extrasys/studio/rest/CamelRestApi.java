package it.extrasys.studio.rest;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.extrasys.studio.model.entity.BookEntity;
import it.extrasys.studio.model.manager.BookManager;

@Component
class CamelRestApi extends RouteBuilder {

	@Autowired
	private BookManager bookManager;

    @Override
    public void configure() {
    	// Essendoci nel classpath camel-servlet, prende quello come component di default.
    	// Abilita esplicitamente il binding mode JSON appoggiandosi a camel-jackson.
    	// http://localhost:8080/demowebos/camel-rest-jpa/api-doc
        restConfiguration()
            .contextPath("/camel-rest-jpa").apiContextPath("/api-doc")
                .apiProperty("api.title", "Camel REST API")
                .apiProperty("api.version", "1.0")
                .apiProperty("cors", "true")
                .apiContextRouteId("doc-api")
            .bindingMode(RestBindingMode.json);

        // http://localhost:8080/demowebos/camel-rest-jpa/books/
        // http://localhost:8080/demowebos/camel-rest-jpa/books/order/1        
        rest("/books").description("Books REST service")
            .get("/").description("The list of all the books")
                .route().routeId("books-api")
                .bean(RestService.class, "findBooks")
                .endRest()
            .get("order/{id}").description("Details of an order by id")
                .route().routeId("order-api")
                .bean(RestService.class, "findBook(${header.id})")
                .endRest()
            .post("/").description("Test create a new book")
            	.type(BookEntity.class)
            	.route().routeId("books-test-create-api")
            	.bean(RestService.class, "logBook(${body})")        
            	.endRest()
            .post("/create/").description("Create a new book")
            	.type(BookEntity.class)
            	.route().routeId("books-create-api")
            	.bean(bookManager, "save(${body})");
        
        // TODO: Provare anche outType() per restituire oggetti aggiornati

        //*/
                 
        /* POST
        // http://localhost:8080/demowebos/camel-rest-jpa/books/
        // http://localhost:8080/demowebos/camel-rest-jpa/books/create/
        {
        	"name": "Camel in action",
        	"author": "Ibsen"
        }
        //*/
        
        // NOTA BENE: La differenza tra .route().routeId() e .id() e' che nel primo caso
        // viene embeddata una rotta Camel direttamente nel DSL REST, nel secondo viene
        // attivata una rotta esterna con .to():
        //
        // get("search/id/{id}").description("Search for a blog article / id").id("rest-searchbyid").to("direct:searchById")
                
    }
}

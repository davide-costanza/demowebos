package it.extrasys.studio;

import org.apache.camel.component.servlet.CamelHttpTransportServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

/**
 * Applicazione Spring Boot.
 *
 * @author davide
 */
@SpringBootApplication
// Non usare perchè pianta tutto: installare le rotte nel classpath
// @ImportResource({"classpath:spring/camel-context.xml"})
public class DemowebosApplication {

    // Logger
    private static final Logger LOG = LoggerFactory.getLogger(DemowebosApplication.class);

    /**
     * Main.
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(DemowebosApplication.class, args);
    }

    /**
     * Servlet per Camel.
     *
     * @return
     */
    @Bean
    ServletRegistrationBean servletRegistrationBean() {
        ServletRegistrationBean servlet = new ServletRegistrationBean(new CamelHttpTransportServlet(),
                "/camel-rest-jpa/*");

        servlet.setName("CamelServlet");

        return servlet;
    }

    /*
     * Questo da' noia ai test in quanto essi usano un application context
     * parziale (BookManager non c'e'), pertanto il bean e' stato esternalizzato
     * in una classe a parte
     */
    /*
     * @Bean CommandLineRunner start(BookManager service) { return args -> {
     * LOG.info("\n\n Initializing database...");
     *
     * service.save(new BookEntity("Fatal eggs", "Bulgakov")); service.save(new
     * BookEntity("The mound", "Lovecraft"));
     *
     * Iterable<BookEntity> books = service.findAll();
     *
     * books.iterator().forEachRemaining(System.out::println);
     *
     * LOG.info("\n ...Done!\n"); }; } //
     */
}

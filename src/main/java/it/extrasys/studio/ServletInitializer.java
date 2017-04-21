package it.extrasys.studio;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Servlet intitializer per Spring Boot Web.
 *
 * @author davide
 */
public class ServletInitializer extends SpringBootServletInitializer {

    /**
     * Metodo standard di configurazione dell'applicazione nel caso delle
     * webapp.
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(DemowebosApplication.class);
    }

}

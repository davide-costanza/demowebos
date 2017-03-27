package it.extrasys.studio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
//Non usare perchè pianta tutto: installare le rotte nel classpath
//@ImportResource({"classpath:spring/camel-context.xml"})
public class DemowebosApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemowebosApplication.class, args);
	}
}

package it.extrasys.studio.camel;

import org.springframework.stereotype.Component;

@Component(value = "myTransformer")
public class MyTransformer {

	public String transform() {
		// lets return a random string
		StringBuffer buffer = new StringBuffer();
		
		for (int i = 0; i < 3; i++) {
			int number = (int) (Math.round(Math.random() * 1000) % 10);
			char letter = (char) ('0' + number);
			buffer.append(letter);
		}
		return buffer.toString();
	}
}
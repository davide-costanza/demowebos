package it.extrasys.studio.configuration;

import java.net.URL;

import org.junit.Test;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

/**
 * Test della configurazione di Spring Boot e delle proprieta' di sistema.
 *
 * @author davide
 */
public class ConfigurationTest {
    /**
     * Test.
     *
     * @throws Exception
     */
    @Test
    public void testConfiguration() throws Exception {
        URL url = Resources.getResource("application.yml");

        String text = Resources.toString(url, Charsets.UTF_8);

        System.out.println("Properties read: \n" + text);
        System.out.println("Profile: \n" + System.getProperty("spring.profiles.active"));
    }
}

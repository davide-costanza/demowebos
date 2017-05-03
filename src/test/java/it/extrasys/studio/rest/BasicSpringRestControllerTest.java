package it.extrasys.studio.rest;

import static java.lang.Math.toIntExact;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import it.extrasys.studio.DemowebosApplication;
import it.extrasys.studio.model.dao.BookDAO;
import it.extrasys.studio.model.entity.BookEntity;

/**
 * Test per i servizi REST basati sui controller nativi di Spring.
 *
 * Non e' annotato come integration test in quanto non lancia un application
 * server vero e proprio ma si limita a usare il mock MVC.
 *
 * @author davide
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemowebosApplication.class)
@WebAppConfiguration
public class BasicSpringRestControllerTest {
    private static final Logger LOG = LoggerFactory.getLogger(BasicSpringRestControllerTest.class);

    private final MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private MockMvc mockMvc;

    @SuppressWarnings("rawtypes")
    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private BookDAO bookDao;

    private final List<BookEntity> bookList = new ArrayList<>();

    /**
     * Intercetta e mette da parte il converter usato da Spring per serializzare
     * gli oggetti in JSON.
     *
     * @param converters
     */
    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {
        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
                .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
                .findAny()
                .orElse(null);

        assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }

    /**
     * Metodo di preparazione invocato prima di ogni test.
     *
     * @throws Exception
     */
    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(this.webApplicationContext).build();

        // Ogni test si ritrova con un singolo book precaricato su db
        this.bookDao.deleteAll();

        this.bookList.add(this.bookDao.save(new BookEntity("Test Fatal eggs", "Test Bulgakov")));
    }

    /**
     * Testa la chiamata create book.
     *
     * @throws Exception
     */
    @Test
    public void testCreateBook() throws Exception {
        LOG.info("\n\n testCreateBook()\n");

        String bookJson = json(new BookEntity("New Fatal eggs", "New Bulgakov"));

        this.mockMvc.perform(post("/basic-rest/books")
                .contentType(this.contentType)
                .content(bookJson))
                .andExpect(status().isCreated());
    }

    /**
     * Testa la chiamata read all books.
     *
     * @throws Exception
     */
    @Test
    public void testReadBooks() throws Exception {
        LOG.info("\n\n testReadBooks()\n");

        this.mockMvc.perform(get("/basic-rest/books"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(this.contentType))
                .andExpect(jsonPath("$", hasSize(1)));
    }

    /**
     * Testa la chiamata read book.
     *
     * @throws Exception
     */
    @Test
    public void testReadSingleBook() throws Exception {
        LOG.info("\n\n testReadSingleBook()\n");

        this.mockMvc.perform(get("/basic-rest/books/"
                + this.bookList.get(0).getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(this.contentType))
                .andExpect(jsonPath("$.id", is(toIntExact(this.bookList.get(0).getId()))));
    }

    /**
     * Converte il dato oggetto Java in JSON usando lo stesso converter di
     * Spring.
     *
     * @param o
     * @return
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();

        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);

        return mockHttpOutputMessage.getBodyAsString();
    }
}

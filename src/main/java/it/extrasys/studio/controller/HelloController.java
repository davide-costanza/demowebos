package it.extrasys.studio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller per view JSP.
 *
 * @author davide
 */
@Controller
public class HelloController {

    /**
     * Pagina index.
     *
     * @return
     */
    @RequestMapping("/")
    public String index() {
        return "myindex";
    }

    /**
     * Pagina hello.
     *
     * @param model
     * @param name
     * @return
     */
    @RequestMapping("/hello")
    public String hello(Model model,
            @RequestParam(value = "name", required = false, defaultValue = "World") String name) {
        model.addAttribute("name", name);
        return "hello";
    }
}

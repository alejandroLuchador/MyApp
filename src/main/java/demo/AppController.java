package demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    Logger LOGGER = LoggerFactory.getLogger(AppController.class);

    @RequestMapping("/")
    public String greet() {
        LOGGER.info("Loading application index...");
        return "Hello World!";
    }
}

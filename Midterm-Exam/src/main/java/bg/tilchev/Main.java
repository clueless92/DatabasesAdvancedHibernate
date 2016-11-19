package bg.tilchev;

import bg.tilchev.parsers.JSONParser;
import bg.tilchev.parsers.XMLParser;
import bg.tilchev.parsers.impls.JSONParserImpl;
import bg.tilchev.parsers.impls.XMLParserImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Created by Todor Ilchev on 2016-11-19.
 */

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public JSONParser autowireJSONParser() {
        return new JSONParserImpl();
    }

    @Bean
    public XMLParser autowireXMLParser() {
        return new XMLParserImpl();
    }
}

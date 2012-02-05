/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wad.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author arto
 */
@Configuration
public class Config {

    @Bean
    public String overrideLog4JErrors() {
        System.setProperty("org.apache.catalina.loader.WebappClassLoader.ENABLE_CLEAR_REFERENCES", "false");
        return "done";
    }
}

package com.nayuki.components;

import com.nayuki.Ward;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

/**
 * ServletComponent used for application port changing
 *
 * @author Rudolf Barbu, Nayuki
 * @version 1.0.3
 */
@Component
public class ServletComponent implements WebServerFactoryCustomizer<TomcatServletWebServerFactory> {
    /**
     * Autowired UtilitiesComponent object
     * Used for various utility functions
     */
    private final UtilitiesComponent utilitiesComponent;

    public ServletComponent(UtilitiesComponent utilitiesComponent) {
        this.utilitiesComponent = utilitiesComponent;
    }

    /**
     * Customizes port of application
     *
     * @param tomcatServletWebServerFactory servlet factory
     */
    @Override
    public void customize(TomcatServletWebServerFactory tomcatServletWebServerFactory) {
        try {
            File setupFile = new File(Ward.SETUP_FILE_PATH);
            String port = utilitiesComponent.getFromIniFile(setupFile, "setup", "port");

            if (port != null) {
                int portNumber = Integer.parseInt(port);
                tomcatServletWebServerFactory.setPort(portNumber);
                System.out.println("[INFO] Port found in setup.ini file. Using port " + portNumber);
            } else {
                int defaultPort = 8080;
                tomcatServletWebServerFactory.setPort(defaultPort);
                System.out.println("[WARN] Port not found in setup.ini file. Using default port " + defaultPort);
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error while reading setup.ini file or parsing port number");
        }
    }
}
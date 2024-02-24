package com.nayuki;

import lombok.Getter;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.File;

/**
 * Ward is a Spring Boot application class
 *
 * @author Rudolf Barbu, Nayuki, Nayuki
 * @version 1.0.4
 */
@SpringBootApplication
public class Ward extends SpringBootServletInitializer {
    /**
     * Constant for determine settings file name
     */
    public static final String SETUP_FILE_PATH = "setup.ini";

    /**
     * Constant for determining initial application port
     */
    public static final int INITIAL_PORT = 4000;

    /**
     * Holder for determine first launch of application
     */
    @Getter
    private static boolean isFirstLaunch;

    /**
     * Holder for application context
     */
    private static ConfigurableApplicationContext configurableApplicationContext;

    /**
     * Entry point of Ward application
     *
     * @param args Spring Boot application arguments
     */
    public static void main(String[] args) {
        isFirstLaunch = true;
        configurableApplicationContext = SpringApplication.run(Ward.class, args);

        File setupFile = new File(Ward.SETUP_FILE_PATH);
        if (setupFile.exists()) {
            restart();
        }
    }

    /**
     * Restarts application
     */
    public static void restart() {
        isFirstLaunch = false;
        ApplicationArguments args = configurableApplicationContext.getBean(ApplicationArguments.class);

        Thread restartThread = new Thread(() -> {
            configurableApplicationContext.close();
            configurableApplicationContext = new SpringApplication(Ward.class).run(args.getSourceArgs());
        });

        restartThread.setDaemon(false);
        restartThread.start();
    }
}
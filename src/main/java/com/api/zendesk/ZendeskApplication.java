package com.api.zendesk;


import com.api.zendesk.controller.MainController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@EnableAutoConfiguration
@SpringBootApplication
public class ZendeskApplication implements CommandLineRunner {

    private static Logger LOG = LoggerFactory
            .getLogger(ZendeskApplication.class);
    @Autowired
    public MainController mc;

    public static void main(String[] args) {
        LOG.info("STARTING THE APPLICATION");
        SpringApplication.run(ZendeskApplication.class, args).close();

        LOG.info("APPLICATION FINISHED");
    }

    @Override
    public void run(String... args) {
        System.out.println("\n\n\n\n");
        System.out.println("::::::::::|  Welcome to Zendesk Ticket Viewer |::::::::::");
        mc.mainMenu();
    }

}
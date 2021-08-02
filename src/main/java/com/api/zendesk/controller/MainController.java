package com.api.zendesk.controller;


import com.api.zendesk.model.Ticket;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

@Component
public class MainController {


    @Autowired
    public ConnectionController connectionController;

    @Autowired
    public RenderController renderController;

    //In memory storage of parsed Json object
    List<Ticket> mainData;

    Gson gson = new Gson();
    int lengthOfTickets = 0;

    Scanner scan = new Scanner(System.in);

    @Autowired
    private ConfigurableApplicationContext configurableApplicationContext;


    public void mainMenu() {
        String uriAll = "https://zccakshay.zendesk.com/api/v2/tickets.json?page[size]=25";
        String uriSingle = "https://zccakshay.zendesk.com/api/v2/tickets.json";


        while (true) {
            System.out.println("\n\n");
            System.out.println("Your options are as follows:::");
            System.out.println("** Press 1 to view all the tickets \n** Press 2 to view a single ticket \n** Press 3 to exit");
            int i = scan.nextInt();
            switch (i) {
                case 1:
                    getAllTickets(uriAll);
                    break;
                case 2:
                    singleTicketMenu(uriSingle);
                    break;
                case 3:
                    System.out.println("\t:::Thanks for using::");
                    System.exit(SpringApplication.exit(configurableApplicationContext));
                    break;
                default:
                    System.out.println("Invalid Choice");
                    break;
            }
        }
    }

    //Menu for Mutiple Ticket Pages
    public void nextMenu(String prev, String next) {
        System.out.println("** Press 1 for previous Page \n** Press 2 for next Page \n** Press 3 for main menu");
        int i = scan.nextInt();
        if (i == 1) {

            getAllTickets(prev);
        } else if (i == 2) {
            getAllTickets(next);
        } else if (i == 3) {

        } else {
            System.out.println("Invalid Choice");
            nextMenu(prev, next);
        }

    }

    // Menu for Single Ticket
    public void singleTicketMenu(String uri) {
        int lengthOfTicket = countTickets();

        while (true) {
            System.out.println("Enter Ticket Number Available from 1 to " + lengthOfTicket);
            int number = scan.nextInt();
            if (getSingleTicket(uri, number)) {
                break;
            }
        }
        //mainMenu();

    }

    //Parse & Process the Ticket
    public boolean getSingleTicket(String uri, int number) {

        JSONObject obj = new JSONObject(connectionController.connect(uri));

        //System.out.println(obj);
        try {
            JSONArray arr = obj.getJSONArray("tickets");
            mainData = gson.fromJson(String.valueOf(arr), new TypeToken<List<Ticket>>() {
            }.getType());
            lengthOfTickets = ((Integer) obj.get("count"));


            if (number > arr.length() && number <= lengthOfTickets) {
                number = number - arr.length();
                getSingleTicket(obj.get("next_page").toString(), number);
            } else if (number > 0 && number <= arr.length()) {
                renderController.renderSingle(mainData, number - 1);
                return true;

            } else {
                System.out.println("Please enter valid number");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error is:::" + e.getLocalizedMessage());
            throw e;
        }

        return true;

    }

    //Parse & Process all the Tickets
    public void getAllTickets(String uri) {
        try {
            JSONObject obj = new JSONObject(connectionController.connect(uri));
            JSONArray arr = obj.getJSONArray("tickets");
            // System.out.println(obj);

            mainData = gson.fromJson(String.valueOf(arr), new TypeToken<List<Ticket>>() {
            }.getType());
            lengthOfTickets = arr.length();
            if (lengthOfTickets != 0) {

                String prev = obj.getJSONObject("links").get("prev").toString();
                prev = URLDecoder.decode(prev, StandardCharsets.UTF_8);
                String next = obj.getJSONObject("links").get("next").toString();
                next = URLDecoder.decode(next, StandardCharsets.UTF_8);
                renderController.renderAll(mainData);
                nextMenu(prev, next);
            } else {
                System.out.println("No tickets to show");
            }
        } catch (Exception e) {
            System.out.println("Error is:::" + e.getLocalizedMessage());
        }
    }

    //Get total count of tickets
    public int countTickets() {

        String uri = "https://zccakshay.zendesk.com/api/v2/tickets/count.json";
        if (lengthOfTickets <= 0) {
            JSONObject obj = new JSONObject(connectionController.connect(uri));
            lengthOfTickets = ((Integer) obj.getJSONObject("count").get("value"));
        }
        return lengthOfTickets;

    }

}
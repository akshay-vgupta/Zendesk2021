package com.api.zendesk.controller;

import com.api.zendesk.model.Ticket;
import de.vandermeer.asciitable.AsciiTable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RenderController {

    // Using ASCIItable library to print data in table format

    public void renderAll(List<Ticket> mainData) {

        AsciiTable at = new AsciiTable();
        at.addRule();
        at.addRow("Ticket id", "Subject", "Opened by", "Opened Date");
        for (int i = 0; i < mainData.size(); i++) {
            at.addRule();
            at.addRow(mainData.get(i).getId(), // ID
                    mainData.get(i).getSubject(), // SUBJECT
                    mainData.get(i).getAssignee_id(), // Creator ID
                    mainData.get(i).getCreated_at()); // DATE
        }
        at.addRule();
        String rend = at.render();
        System.out.println(rend);

    }

    public void renderSingle(List<Ticket> mainData, int i) {
        AsciiTable at = new AsciiTable();
        at.addRule();
        at.addRow("Ticket id", "Subject", "Opened by", "Opened Date");
        at.addRule();
        at.addRow(mainData.get(i).getId(),   // ID
                mainData.get(i).getSubject(), // Subject
                mainData.get(i).getAssignee_id(), // Creator ID
                mainData.get(i).getCreated_at()); //Date
        at.addRule();
        String rend = at.render();
        System.out.println(rend);
    }


}
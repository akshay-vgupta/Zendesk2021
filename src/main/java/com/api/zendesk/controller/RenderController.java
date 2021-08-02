package com.api.zendesk.controller;

import com.api.zendesk.model.Ticket;
import de.vandermeer.asciitable.AT_Row;
import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RenderController {

    // Using ASCIItable library to print data in table format

    public void renderAll(List<Ticket> mainData) {

            AsciiTable at = new AsciiTable();
            AT_Row row;
            at.addRule();
            at.addRow("Ticket id", "Subject", "Opened by", "Opened Date");
            for (int i = 0; i < mainData.size(); i++) {
                at.addRule();
              row=  at.addRow(mainData.get(i).getId(), // ID
                        mainData.get(i).getSubject(), // SUBJECT
                        mainData.get(i).getAssignee_id(), // Creator ID
                        mainData.get(i).getCreated_at()); // DATE
            row.getCells().get(1).getContext().setTextAlignment(TextAlignment.LEFT);
                row.getCells().get(3).getContext().setTextAlignment(TextAlignment.LEFT);
            }
            at.addRule();
        at.getContext().setWidth(120);
            String rend = at.render();
            System.out.println(rend);

    }

    public void renderSingle(List<Ticket> mainData, int i) {
        AsciiTable at = new AsciiTable();
        AT_Row row;
        at.addRule();
        at.addRow("Ticket id", "Subject", "Opened by", "Opened Date");
        at.addRule();
        row =at.addRow(mainData.get(i).getId(),   // ID
                mainData.get(i).getSubject(), // Subject
                mainData.get(i).getAssignee_id(), // Creator ID
                mainData.get(i).getCreated_at()); //Date
        row.getCells().get(1).getContext().setTextAlignment(TextAlignment.LEFT);
        row.getCells().get(3).getContext().setTextAlignment(TextAlignment.LEFT);
        at.addRule();
        at.getContext().setWidth(120);
        String rend = at.render();
        System.out.println(rend);
    }


}
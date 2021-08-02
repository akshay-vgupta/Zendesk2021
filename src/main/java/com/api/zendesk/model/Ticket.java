package com.api.zendesk.model;

import java.util.Date;

public class Ticket {
    private int id;
    private String subject;
    private long assignee_id;
    private Date created_at;

    public Ticket(int id, String subject, long assignee_id, Date created_at) {
        this.id = id;
        this.subject = subject;
        this.assignee_id = assignee_id;
        this.created_at = created_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public long getAssignee_id() {
        return assignee_id;
    }

    public void setAssignee_id(long assignee_id) {
        this.assignee_id = assignee_id;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                ", assignee_id=" + assignee_id +
                ", created_at=" + created_at +
                '}';
    }
}
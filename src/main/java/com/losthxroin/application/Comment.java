package com.losthxroin.application;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Comment {
    @Size(min = 1, max = 20)
    @NotEmpty()
     String name;
     int applicationFormsID;
    @Size(min = 1, max = 100)
    @NotEmpty()
    String comment;

    public Comment(String name, int applicationFormsID, String comment) {
        this.name = name;
        this.applicationFormsID = applicationFormsID;
        this.comment = comment;
    }
    public Comment() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getApplicationFormsID() {
        return applicationFormsID;
    }

    public void setApplicationFormsID(int applicationFormsID) {
        this.applicationFormsID = applicationFormsID;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

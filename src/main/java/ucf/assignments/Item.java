/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 NURIDEAN MUBARAK
 */
package ucf.assignments;

import javafx.beans.property.BooleanProperty;
import javafx.scene.control.CheckBox;

import java.io.Serializable;
import java.time.LocalDate;

public class Item implements Serializable {
    //task object

    private String description;
    private String date;
    private BooleanProperty complete;

    public Item() {
        this.description = "description";
        this.date = "date";
    }



    //generated constructor
    public Item(String description, String date, boolean complete) {
        this.description = description;
        this.date = date;
    }

    //generated setters and getters
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


}

/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 NURIDEAN MUBARAK
 */
package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MenuController  implements Initializable {
    @FXML
    private TableView tableDisplay;

    @FXML
    private TextField itemReader;

    @FXML
    private DatePicker dueDateTime;

    @FXML
    public void itemDescription(ActionEvent actionEvent) {
        //get item name from user
    }

    public void itemDelete(ActionEvent actionEvent) {
        //deletes the selected item from the list
        deleteItemClicked();
    }

    @FXML
    public void itemAdd(ActionEvent actionEvent) {
        addItemClicked();
    }

    public void displayCompleted(ActionEvent actionEvent) {
        //call method to show completed items
    }

    public void displayIncompleted(ActionEvent actionEvent) {
        //call method to show incompleted items
    }

    public void saveFile(ActionEvent actionEvent) {
        //writeFile(ObservableList<Item> itemObservableList);
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //manually creating the columns for my tallest
        TableColumn ItemDescription = new TableColumn("Item Description");
        TableColumn ItemDate = new TableColumn("Item Date");
        TableColumn ItemComplete = new TableColumn("Check if done");

        //assigning the object variables
        tableDisplay.getColumns().addAll(ItemDescription, ItemDate, ItemComplete);

        //assigning parts of the item object to each collumn
        ItemDescription.setCellValueFactory( new PropertyValueFactory<Item,String>("description"));
        ItemDate.setCellValueFactory( new PropertyValueFactory<Item,String>("date"));
        ItemComplete.setCellValueFactory( new PropertyValueFactory<Item,String>("selectComplete"));
    }



    public void addItemClicked(){
        //check that the user enters a name
        if(itemReader.getText().equals(""))
            return;
        //check that the user enters a new date
        if(dueDateTime.getValue().equals(null))
            return;
        Item item = new Item();

        tableDisplay.setEditable(true);
        //get item description from textfield
        item.setDescription(itemReader.getText());
        //get localdate from datepicker
        item.setDate(String.valueOf(dueDateTime.getValue()));
        //defualt value for boolean
        item.isComplete(false);
        tableDisplay.getItems().add(item);
        itemReader.clear();
        dueDateTime.getEditor().clear();
        dueDateTime.setValue(null);
    }

    public void deleteItemClicked(){
        ObservableList<Item> itemSelected, allItems;
        allItems = tableDisplay.getItems();
        itemSelected = tableDisplay.getSelectionModel().getSelectedItems();

        itemSelected.forEach(allItems::remove);
    }


/* public void writeFile(ObservableList<Item> itemObservableList) {

        try {
            //writing objects to the file
            FileOutputStream file = new FileOutputStream("savefile.ser");
            ObjectOutputStream ofile = new ObjectOutputStream(file);
            ofile.writeObject(new ArrayList<Item>(itemObservableList));
            ofile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
} */



}

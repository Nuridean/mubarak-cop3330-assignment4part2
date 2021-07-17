/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 NURIDEAN MUBARAK
 */
package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MenuController  implements Initializable {
    @FXML
    private TableView tableDisplay;

    @FXML
    private TextField itemReader;

    @FXML
    private DatePicker dueDateTime;

    @FXML
    private Button saveFile;

    private Desktop desktop = Desktop.getDesktop();
    final FileChooser fileChooser = new FileChooser();
    private final ObservableList<Item> list = FXCollections.observableArrayList();

    @FXML
    public void itemDescription(ActionEvent actionEvent) {
        //get item name from user
    }

    @FXML
    public void itemDelete(ActionEvent actionEvent) {
        //deletes the selected item from the list
        deleteItemClicked();
    }

    @FXML
    public void clearItemList(ActionEvent actionEvent) {
        deleteListClicked();
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
        saveFileClicked();
    }

    public void openFile(ActionEvent actionEvent) {
        openFileClicked();
    }
    



    @Override
    public void initialize(URL url, ResourceBundle rb) {

        tableDisplay.setEditable(true);
        //manually creating the columns
        TableColumn ItemDescription = new TableColumn("Item Description");
        TableColumn ItemDate = new TableColumn("Item Date");
        TableColumn ItemComplete = new TableColumn("Check if done");

        //assigning the object variables
        tableDisplay.getColumns().addAll(ItemDescription, ItemDate, ItemComplete);

        //assigning parts of the item object to each column
        ItemDescription.setCellValueFactory( new PropertyValueFactory<Item,String>("description"));
        ItemDescription.setCellFactory(TextFieldTableCell.forTableColumn());
        ItemDescription.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Item, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Item, String> t) {
                        ((Item) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setDescription(t.getNewValue());
                    }
                }
        );
        ItemDate.setCellValueFactory( new PropertyValueFactory<Item,String>("date"));
        ItemDate.setCellFactory(TextFieldTableCell.forTableColumn());
        ItemDate.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Item, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Item, String> t) {
                        ((Item) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setDate(t.getNewValue());
                    }
                }
        );

        ItemComplete.setCellFactory( new PropertyValueFactory<Item,String>("selectComplete"));
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
        tableDisplay.getItems().add(item);
        itemReader.clear();
        dueDateTime.getEditor().clear();
        dueDateTime.setValue(null);

        list.add(item);
    }

    public void deleteItemClicked(){
        //Make a temp list
        //define selected item
        //deleted selected item
        ObservableList<Item> itemSelected, allItems;
        allItems = tableDisplay.getItems();
        itemSelected = tableDisplay.getSelectionModel().getSelectedItems();

        itemSelected.forEach(allItems::remove);
    }

    private void deleteListClicked() {
        //Clear the tableview of objects
        tableDisplay.getItems().clear();
    }



    private void saveFileClicked() {
        File sfile = fileChooser.showSaveDialog(null);
        if(sfile != null) {
            saveFile(tableDisplay.getItems(), sfile);
        }
    }

    private void saveFile(ObservableList<Item> Item, File sfile) {
        //write list to file
        try {
            BufferedWriter outWriter = new BufferedWriter(new FileWriter(sfile));

                for(Item task : Item) {
                outWriter.write(task.toString());
                outWriter.newLine();
            }
            outWriter.close();
        } catch (IOException e) {
            }
        }

    private void openFileClicked() {
        File file = fileChooser.showOpenDialog(null);
        if(file != null){
            openFile(file);
        }
    }

    private void openFile(File file) {
        try{
            desktop.open(file);
        } catch (IOException ex) {
            Logger.getLogger(
                    MenuController.class.getName()).log(
                    Level.SEVERE, null, ex
            );
        }
    }

}
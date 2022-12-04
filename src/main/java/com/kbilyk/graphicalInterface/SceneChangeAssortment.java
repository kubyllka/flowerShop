package com.kbilyk.graphicalInterface;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class SceneChangeAssortment {

    private Stage stage;
    private Scene scene;

    /**
     * Switch stage to Add new flower scene
     * if user selected Add new flower button in Change Assortment Menu
     * @param event Action from user
     */
    public void switchAddNewFlower(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxmlFiles/addNewFlower.fxml")));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Switch stage to Add new accessory scene
     * if user selected Add new accessory button in Change Assortment Menu
     * @param event Action from user
     */
    public void switchAddNewAccessory(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxmlFiles/addNewAccessory.fxml")));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Switch stage to Delete flower scene
     * if user selected Delete flower button in Change Assortment Menu
     * @param event Action from user
     */
    public void switchDeleteFlower(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxmlFiles/deleteFlower.fxml")));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Switch stage to Delete accessory scene
     * if user selected Delete accessory button in Change Assortment Menu
     * @param event Action from user
     */
    public void switchDeleteAccessory(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxmlFiles/deleteAccessory.fxml")));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Switch stage to Main menu
     * if user selected Back button in Change Assortment Menu
     * @param event Action from user
     */
    public void switchMainMenu(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxmlFiles/mainMenu.fxml")));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}

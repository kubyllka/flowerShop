package com.kbilyk.graphicalInterface;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

import static com.kbilyk.databaseConnection.Database.closeConnection;

public class SceneMainMenu {

    // Buttons of main menu

    private Button createBouquetButton;

    private Button changeAssortmentButton;

    private Button exit;

    private Stage stage;
    private Scene scene;

    /**
     * Method call Main menu stage
     * @param stage
     */

    public void startMainMenu(Stage stage){
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxmlFiles/mainMenu.fxml")));
            Scene scene = new Scene(root);
            Image icon = new Image("D:\\навчання\\1 сем 2 курс\\ПП\\lab6\\src\\main\\resources\\icon.png");
            stage.getIcons().add(icon);
            stage.setTitle("Happiness workshop");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Switch stage from Main Menu to Create Bouquet Menu
     * if user selected Create bouquet button in Main Menu
     * @param event Action from user
     */
    public void switchCreateBouquetMenu(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxmlFiles/createBouquetMenu.fxml")));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Switch stage from Main Menu to Change Assortment Menu
     * if user selected Change assortment button in Main Menu
     * @param event Action from user
     */
    public void switchChangeAssortmentMenu(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxmlFiles/changeAssortmentMenu.fxml")));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     *Exit from the program and close connect to database
     * if user selected Exit button in Main Menu
     * if it`s necessary
     */
    public void exitFromProgram(ActionEvent event){
        closeConnection();
        System.exit(0);
    }
}

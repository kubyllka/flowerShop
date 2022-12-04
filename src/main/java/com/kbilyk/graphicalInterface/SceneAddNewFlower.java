package com.kbilyk.graphicalInterface;

import com.kbilyk.constant.FlowerType;
import com.kbilyk.item.Flower;
import com.kbilyk.itemDAO.FlowerDAO;
import com.kbilyk.validation.Validator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static com.kbilyk.constant.maxSizes.*;
import static com.kbilyk.dialog.InfoDialog.display;

public class SceneAddNewFlower implements Initializable {
    private Stage stage;
    private Scene scene;  // scene add new flower

    @FXML
    private Button addNewFlower; // button that add new flower to database

    //----------------------------------Info about flower---------------------------------------
    @FXML
    private ChoiceBox<FlowerType> flowerType;

    @FXML
    private TextField colourField;

    @FXML
    private TextField lengthField;

    @FXML
    private TextField numberOfFlowersField;

    @FXML
    private TextField priceField;
    //-----------------------------------------------------------------------------------------

    @FXML
    private Label errorColour;  // error label for user if he typed incorrect colour info

    @FXML
    private Label errorPrice;  // error label for user if he typed incorrect price info

    @FXML
    private Label errorLength; // error label for user if he typed incorrect length info

    @FXML
    private Label errorNumberOfFlowers; // error label for user if he typed incorrect number of items info


    private boolean allInfoCorrect = false;  // if all flower info in fields is correct

    private boolean typeChosen = false;  // if flower type was chosen by user

    /**
     * Create flower and add it to database
     * if user press Add new flower button
     * @param event Action of user
     */
    public void addNewFlower(ActionEvent event) {
        if (!addNewFlower.isDisable()) {
            Flower flower = Flower.builder()
                    .setFlowerType(flowerType.getValue())
                    .setColour(colourField.getText())
                    .setLength(Double.parseDouble(lengthField.getText()))
                    .setPrice(Double.parseDouble(priceField.getText()))
                    .built();

            int count = 0;
            FlowerDAO flowerDAO = new FlowerDAO();
            for (int i = 0; i < Integer.parseInt(numberOfFlowersField.getText()); i++) {
                count += flowerDAO.insert(flower);// counting how many flowers were inserted to database
            }

            // message box for user
            if (count == Integer.parseInt(numberOfFlowersField.getText())) {
                display("Info", "All flowers were added!");
            } else {
                display("Error", "Flowers were not added! Try again");
            }
        }
    }

    /**
     * Check if the flower info in fields are correct
     * @param event Action of user
     */
    public void correctFields(KeyEvent event) {
        boolean correctColour = Validator.isTextFieldIsWords(colourField, errorColour);
        boolean correctPrice = Validator.isTextFieldIsRealNumber(priceField, errorPrice);
        boolean correctLength = Validator.isTextFieldIsRealNumber(lengthField, errorLength);
        boolean correctNumberOfFlowers = Validator.isTextFieldIsInteger(numberOfFlowersField, errorNumberOfFlowers);
        allInfoCorrect = correctColour && correctPrice && correctLength && correctNumberOfFlowers;
        addNewFlower.setDisable(!allInfoCorrect || !typeChosen);
    }

    /**
     * Check if flower type for creating was selected
     * @param event Action of user
     */
    public void isTypeSelected(ActionEvent event){
        typeChosen = !flowerType.getSelectionModel().isEmpty();
        addNewFlower.setDisable(!allInfoCorrect || !typeChosen);
    }

    /**
     * Initialize objects of graphical interface
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        flowerType.getItems().setAll(FlowerType.values());
        flowerType.setOnAction(this::isTypeSelected);

        errorColour.setText("type the colour like: light pink, red. (max symb: " + maxSizeOfColourField + " )");
        errorLength.setText("type double/integer value like: 77, 56.6 (max symb: " + maxDigitsInNumber + ")");
        errorPrice.setText("type double/integer value like: 77, 56.6 (max symb: " + maxDigitsInNumber + ")");
        errorNumberOfFlowers.setText("type integer value like: 1, 68 (max digit: " +  maxDigitInAddField + ")");

    }

    /**
     * Switch stage to Change Assortment Menu
     * if user selected Back button
     * @param event Action from user
     */
    public void backOption(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxmlFiles/changeAssortmentMenu.fxml")));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}

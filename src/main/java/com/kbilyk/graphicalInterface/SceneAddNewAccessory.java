package com.kbilyk.graphicalInterface;
import com.kbilyk.constant.AccessoryType;
import com.kbilyk.item.Accessory;
import com.kbilyk.itemDAO.AccessoryDAO;
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

public class SceneAddNewAccessory implements Initializable {

        private Stage stage;
        private Scene scene;  // scene add new accessory

        @FXML
        private Button addNewAccessory;  // button that add new accessory to database

        //----------------------------------Info about accessory---------------------------------------
        @FXML
        private ChoiceBox<AccessoryType> accessoryType; // choosing accessory type for creation

        @FXML
        private TextField colourField;

        @FXML
        private TextField priceField;

        @FXML
        private TextField numberOfAccessoriesField;

        @FXML
        private TextArea infoField;
        //--------------------------------------------------------------------------------------------

        @FXML
        private Label errorColour;  // error label for user if he typed incorrect colour info

        @FXML
        private Label errorPrice;  // error label for user if he typed incorrect price info

        @FXML
        private Label errorNumberOfAccessories; // error label for user if he typed incorrect number of items info

        @FXML
        private Label errorAdditionalInfo;

        private boolean allInfoCorrect = false; // if all accessory info in fields is correct

        private boolean typeChosen = false;  // if accessory type was chosen by user

        /**
         * Create accessory and add it to database
         * if user press Add new accessory button
         * @param event Action of user
         */
        public void addNewAccessory(ActionEvent event){
                if(!addNewAccessory.isDisable()){
                        Accessory accessory = Accessory.builder()
                                .setAccessoryType(accessoryType.getValue())
                                .setColour(colourField.getText())
                                .setPrice(Double.parseDouble(priceField.getText()))
                                .setInfo(infoField.getText())
                                .built();
                        AccessoryDAO accessoryDAO = new AccessoryDAO();
                        int count = 0;
                        for(int i = 0; i < Integer.parseInt(numberOfAccessoriesField.getText()); i++){
                                count += accessoryDAO.insert(accessory); // counting how many accessories were
                                                                        // inserted to database
                        }

                        // message box for user
                        if(count == Integer.parseInt(numberOfAccessoriesField.getText())){
                                display("Info", "All accessories were added!");
                        }else{
                                display("Error", "Accessories were not added! Try again");
                        }
                }
        }

        /**
         * Check if the accessory info in fields are correct
         * @param event Action of user
         */
        public void correctFields(KeyEvent event){
                boolean correctColour = Validator.isTextFieldIsWords(colourField, errorColour);
                boolean correctPrice = Validator.isTextFieldIsRealNumber(priceField, errorPrice);
                boolean correctNumberOfAccessories = Validator.isTextFieldIsInteger
                        (numberOfAccessoriesField, errorNumberOfAccessories);
                boolean correctInfo = Validator.isTextAreaLessThanMax(infoField, errorAdditionalInfo);
                allInfoCorrect = correctColour && correctPrice && correctNumberOfAccessories && correctInfo;
                addNewAccessory.setDisable(!allInfoCorrect || !typeChosen);
        }

        /**
         * Check if accessory type for creating was selected
         * @param event Action of user
         */
        public void isTypeSelected(ActionEvent event){
                typeChosen = !accessoryType.getSelectionModel().isEmpty();
                addNewAccessory.setDisable(!allInfoCorrect || !typeChosen);
        }

        /**
         * Initialize objects of graphical interface
         */

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
                accessoryType.getItems().setAll(AccessoryType.values());
                accessoryType.setOnAction(this::isTypeSelected);

                infoField.setOnKeyPressed(this::correctFields);

                errorColour.setText("type the colour like: light pink, red. (max symb: " + maxSizeOfColourField + " )");
                errorPrice.setText("type double/integer value like: 77, 56.6 (max symb: " + maxDigitsInNumber + ")");
                errorNumberOfAccessories.setText("type integer value like: 1, 68 (max digit: " +  maxDigitInAddField + ")");
                errorAdditionalInfo.setText("max symbol: " + maxSizeOfInfoField);
        }

        /**
         * Switch stage to Change Assortment Menu
         * if user selected Back button
         * @param event Action from user
         */
        @FXML
        public void backOption(ActionEvent event) {
                try {
                        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxmlFiles/changeAssortmentMenu.fxml")));
                        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                        scene = new Scene(root);
                        stage.setScene(scene);
                } catch (IOException ex) {
                        throw new RuntimeException(ex);
                }
        }
}

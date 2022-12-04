package com.kbilyk.graphicalInterface;

import com.kbilyk.constant.FlowerType;
import com.kbilyk.constant.OrderSort;
import com.kbilyk.item.Flower;
import com.kbilyk.itemDAO.FlowerDAO;
import com.kbilyk.sortfilter.SortFilterFlowersDAO;
import com.kbilyk.validation.Validator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.Objects;
import java.util.ResourceBundle;

import static com.kbilyk.dialog.InfoDialog.display;
import static com.kbilyk.sortfilter.FilterFlowersThatNotUsed.*;

public class SceneDeleteFlower implements Initializable {
    private Stage stage;
    private Scene scene; // scene delete flower

    //-----------------------------Table of flowers---------------------------
    @FXML
    private TableView<Flower> tableOfFlowers;

    @FXML
    private TableColumn<Flower, FlowerType> flowerTypeColumn;

    @FXML
    private TableColumn<Flower, String> colourColumn;

    @FXML
    private TableColumn<Flower, Double> priceColumn;

    @FXML
    private TableColumn<Flower, Double> lengthColumn;

    @FXML
    private TableColumn<Flower, Date> deliveryDateColumn;

    //---------------------------------------------------------------------


    @FXML
    private Button deleteFlowerButton; // button that removes selected item from database

    @FXML
    private ChoiceBox<FlowerType> flowerType; // choosing flower type for flower filtration

    @FXML
    private ChoiceBox<OrderSort> sortingType; // choosing sorting type for flower filtration by fresh

    @FXML
    private Button filterFlowersByTypeButton; // button that filter flowers (from database) by selected type

    @FXML
    private Button sortFlowersByFreshButton; // button that sort flowers (from database)  by selected type

    @FXML
    private Button getAllFlowersButton;  // button that get all flowers from database

    @FXML
    private Button filterInPriceRangeButton; // button that get all flowers in price range from database

    @FXML
    private Button filterInLengthRangeButton;  // button that get all flowers in length range from database

    @FXML
    private Button backOption;  // back to previous Change assortment menu


    // The info about min/max flower price in database
    @FXML
    private Label minPrice;

    @FXML
    private Label maxPrice;

    // Input price fields for user
    @FXML
    private TextField minPriceRange;

    @FXML
    private TextField maxPriceRange;


    // error label for user if he typed incorrect price info
    @FXML
    private Label errorPriceMax;

    @FXML
    private Label errorPriceMin;


    // The info about min/max flower length in database
    @FXML
    private Label minLength;

    @FXML
    private Label maxLength;


    // Input length fields for user
    @FXML
    private TextField minLengthRange;

    @FXML
    private TextField maxLengthRange;


    // error label for user if he typed incorrect length info
    @FXML
    private Label errorLengthMax;

    @FXML
    private Label errorLengthMin;


    //The min/max price of flowers in database

    private double valueMinPrice;

    private double valueMaxPrice;


    //The min/max length of flowers in database
    private double valueMaxLength;

    private double valueMinLength;

  private ObservableList<Flower> filteredFlowers; // list of filtered flowers


    /**
     * Deletes selected item from table and database
     * if user press Delete flower button
     * @param event Action of user
     */
    public void deleteSelectedItem(ActionEvent event){
        int index = tableOfFlowers.getSelectionModel().getSelectedIndex();
        if(index == -1){
            return;
        }
        Flower flowerForDelete = tableOfFlowers.getItems().get(index);

        FlowerDAO flowerDAO = new FlowerDAO();

        // message box for user
        if(flowerDAO.delete(flowerForDelete) == 1){
            display("Info", "Flower was deleted!");
        }else{
            display("Error", "Flower was not deleted! Try again!");
        }
        tableOfFlowers.getItems().remove(index);
        deleteFlowerButton.setDisable(tableOfFlowers.getItems().isEmpty());
        changeMinMaxLabels();

        clearFieldsAndDisableButtons();
    }


    /**
     * Get all flowers from database that not in use and put them into table
     * If flowers was found - delete button become able
     * @param event Action of user
     */
    @FXML
    public void getAllFlowersAction(ActionEvent event){
        filteredFlowers = FXCollections.observableList(filterAllFlowers());
        deleteFlowerButton.setDisable(filteredFlowers.isEmpty());
        tableOfFlowers.setItems(filteredFlowers);
    }


    /**
     * Get all flowers (that not in use) from database sorted by fresh and put them into table view
     * if sorting type was selected
     * If flowers was found - delete button become able
     * @param event Action of user
     */
    @FXML
    void sortFlowersByFreshAction(ActionEvent event) {
        if(!sortFlowersByFreshButton.isDisable()){
            filteredFlowers = FXCollections.observableList(sortFlowersByFresh(sortingType.getValue()));
            deleteFlowerButton.setDisable(filteredFlowers.isEmpty());
            tableOfFlowers.setItems(filteredFlowers);
        }
    }

    /**
     * Get flowers (that not in use) from database filtered by type and put them into table view
     * if flower type was selected
     * If flowers was found - delete button become able
     * @param event Action of user
     */
    @FXML
    void filterFlowersByTypeAction(ActionEvent event) {
        if(!filterFlowersByTypeButton.isDisable()){
            filteredFlowers = FXCollections.observableList(filterFlowersByType(flowerType.getValue()));
            deleteFlowerButton.setDisable(filteredFlowers.isEmpty());
            tableOfFlowers.setItems(filteredFlowers);
        }
    }

    /**
     * Get flowers (that not in use) from database filtered by length and put them into tableview
     * if the min/max length fields is correct
     * If flowers was found - delete button become able
     * @param event Action of user
     */
    public void filterFlowersInLengthRangeAction(ActionEvent event){
        if(!filterInLengthRangeButton.isDisable()){
            double minLimit = Double.parseDouble(minLengthRange.getText());
            double maxLimit = Double.parseDouble(maxLengthRange.getText());
            filteredFlowers = FXCollections.observableList(filterFlowersInLengthRange(minLimit, maxLimit));
            deleteFlowerButton.setDisable(filteredFlowers.isEmpty());
            tableOfFlowers.setItems(filteredFlowers);
        }
    }

    /**
     * Get flowers (that not in use) from database filtered by price and put them into tableview
     * if the min/max price fields is correct
     * If flowers was found - delete button become able
     * @param event Action of user
     */
    public void filterFlowersInPriceRangeAction(ActionEvent event){
        if(!filterInPriceRangeButton.isDisable()){
            double minLimit = Double.parseDouble(minPriceRange.getText());
            double maxLimit = Double.parseDouble(maxPriceRange.getText());
            filteredFlowers = FXCollections.observableList(filterFlowersInPriceRange(minLimit, maxLimit));
            deleteFlowerButton.setDisable(filteredFlowers.isEmpty());
            tableOfFlowers.setItems(filteredFlowers);
        }
    }

    /**
     *  Method find the min/max values of length/ price
     *  and refresh this values in program
     */

    public void changeMinMaxLabels(){

        SortFilterFlowersDAO sortFilterFlowersDAO = new SortFilterFlowersDAO();

        valueMinPrice = sortFilterFlowersDAO.minPrice();
        valueMaxPrice = sortFilterFlowersDAO.maxPrice();

        minPrice.setText("Min flower price: " + valueMinPrice);
        maxPrice.setText("Max flower price: " + valueMaxPrice);

        valueMinLength = sortFilterFlowersDAO.minLength();
        valueMaxLength = sortFilterFlowersDAO.maxLength();

        minLength.setText("Min flower length: " + valueMinLength);
        maxLength.setText("Max flower length: " + valueMaxLength);
    }

    /**
     * Check length fields filled in correctly by the user
     * if the length fields is correct - set Filter in length range button able
     */
    public void correctRangeLengthFields(KeyEvent event) {
        // correct min limit
        boolean minLimit = Validator.isNumberInRange(minLengthRange, errorLengthMin, valueMinLength, valueMaxLength);
        maxLengthRange.setEditable(minLimit);
        boolean maxLimit = false;

        // correct max limit
        if(maxLengthRange.isEditable()){
            maxLimit = Validator.isNumberInRangeBiggerThan(maxLengthRange, errorLengthMax, valueMinLength,
                    valueMaxLength, Double.parseDouble(minLengthRange.getText()));
        }
        filterInLengthRangeButton.setDisable(!minLimit || !maxLimit);
    }

    /**
     * Check price fields filled in correctly by the user
     * if the price fields is correct - set Filter in price range button able
     */
    public void correctRangePriceFields(KeyEvent event) {
        boolean minLimit = Validator.isNumberInRange(minPriceRange, errorPriceMin, valueMinPrice, valueMaxPrice);
        maxPriceRange.setEditable(minLimit);
        boolean maxLimit = false;
        if(maxPriceRange.isEditable()){
            maxLimit = Validator.isNumberInRangeBiggerThan(maxPriceRange, errorPriceMax, valueMinPrice,
                    valueMaxPrice, Double.parseDouble(minPriceRange.getText()));
        }

        filterInPriceRangeButton.setDisable(!minLimit || !maxLimit);
    }

    /**
     * Check if flower type for filtration was selected
     * if is - set Filter flowers by type button able
     */
    public void filterByTypeButtonDisability(ActionEvent event){
        filterFlowersByTypeButton.setDisable(flowerType.getSelectionModel().isEmpty());
    }

    /**
     * Check if sort type for fresh sorting was selected
     * if is - set Sort flowers by fresh button able
     */
    public void sortByFreshButtonDisability(ActionEvent event){
        sortFlowersByFreshButton.setDisable(sortingType.getSelectionModel().isEmpty());
    }


    /**
     * Initializes objects of graphical interface
     */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        flowerType.getItems().setAll(FlowerType.values());
        flowerType.setOnAction(this::filterByTypeButtonDisability);

        sortingType.getItems().setAll(OrderSort.values());
        sortingType.setOnAction(this::sortByFreshButtonDisability);

        // finding min max values
        changeMinMaxLabels();

        // initialize columns of table
        flowerTypeColumn.setCellValueFactory(new PropertyValueFactory<Flower, FlowerType>("flowerType"));
        colourColumn.setCellValueFactory(new PropertyValueFactory<Flower, String>("colour"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Flower, Double>("price"));
        lengthColumn.setCellValueFactory(new PropertyValueFactory<Flower, Double>("length"));
        deliveryDateColumn.setCellValueFactory(new PropertyValueFactory<Flower, Date>("date"));
    }

    public void clearFieldsAndDisableButtons(){
        minPriceRange.clear();
        maxPriceRange.clear();
        minLengthRange.clear();
        maxLengthRange.clear();
        filterInLengthRangeButton.setDisable(true);
        filterInPriceRangeButton.setDisable(true);
    }
    /**
     * Switch stage to Change Assortment Menu
     * if user selected Back button
     * @param event Action from user
     */
    @FXML
    void backOption(ActionEvent event) {
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


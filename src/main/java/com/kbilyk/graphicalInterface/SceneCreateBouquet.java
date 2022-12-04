package com.kbilyk.graphicalInterface;

import com.kbilyk.constant.AccessoryType;
import com.kbilyk.constant.FlowerType;
import com.kbilyk.constant.OrderSort;
import com.kbilyk.item.Accessory;
import com.kbilyk.item.Flower;
import com.kbilyk.sortfilter.SortFilterAccessoriesDAO;
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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.Objects;
import java.util.ResourceBundle;

import static com.kbilyk.bouquet.Bouquet.*;
import static com.kbilyk.dialog.InfoDialog.display;
import static com.kbilyk.sortfilter.FilterAccessoriesThatNotUsed.*;
import static com.kbilyk.sortfilter.FilterFlowersThatNotUsed.*;

public class SceneCreateBouquet implements Initializable {

    private Stage stage;
    private Scene scene;

    @FXML
    private Button getAllFlowersButton;

    @FXML
    private TextField maxFlowerPriceRange;

    @FXML
    private Button filterFlowersInPriceRangeButton;

    @FXML
    private Label minFlowerPrice;

    @FXML
    private Label maxFlowerPrice;

    @FXML
    private Label errorFlowerPriceMax;

    @FXML
    private TextField minFlowerPriceRange;

    @FXML
    private Label errorFlowerPriceMin;

    @FXML
    private TextField maxFlowerLengthRange;

    @FXML
    private Button filterFlowerInLengthRangeButton;

    @FXML
    private Label minFlowerLength;

    @FXML
    private Label maxFlowerLength;

    @FXML
    private Label errorFlowerLengthMax;

    @FXML
    private TextField minFlowerLengthRange;

    @FXML
    private Label errorFlowerLengthMin;

    @FXML
    private ChoiceBox<FlowerType> flowerType;

    @FXML
    private Button filterFlowersByTypeButton;

    @FXML
    private ChoiceBox<OrderSort> sortingTypeFlower;

    @FXML
    private Button sortFlowersByFreshButton;

    //-----------------------------Add Flower table---------------------------
    @FXML
    private TableView<Flower> addTableOfFlowers;

    @FXML
    private TableColumn<Flower, FlowerType> flowerTypeColumn_addTable;

    @FXML
    private TableColumn<Flower, String> flowerColourColumn_addTable;

    @FXML
    private TableColumn<Flower, Double> flowerPriceColumn_addTable;

    @FXML
    private TableColumn<Flower, Double> flowerLengthColumn_addTable;

    @FXML
    private TableColumn<Flower, Date> deliveryDateColumn_addTable;
    //----------------------------------------------------------------------

    @FXML
    private Button backOptionAddFlower;

    @FXML
    private Button addFlowerButton;

    @FXML
    private Button getAllAccessoriesButton;

    @FXML
    private ChoiceBox<AccessoryType> accessoryType;

    @FXML
    private Button filterAccessoriesByTypeButton;

    @FXML
    private TextField maxAccessoryPriceRange;

    @FXML
    private Button filterAccessoriesInPriceRangeButton;

    @FXML
    private Label minAccessoryPrice;

    @FXML
    private Label maxAccessoryPrice;

    @FXML
    private Label errorAccessoryPriceMax;

    @FXML
    private TextField minAccessoryPriceRange;

    @FXML
    private Label errorAccessoryPriceMin;

    //-----------------------------Add Accessory table-----------------------------
    @FXML
    private TableView<Accessory> addTableOfAccessories;

    @FXML
    private TableColumn<Accessory, AccessoryType> accessoryTypeColumn_addTable;

    @FXML
    private TableColumn<Accessory, String> accessoryColourColumn_addTable;

    @FXML
    private TableColumn<Accessory, Double> accessoryPriceColumn_addTable;

    @FXML
    private TableColumn<Accessory, String> additionalInfoColumn_addTable;

    //--------------------------------------------------------------------------------

    @FXML
    private Button backOptionAddAccessory;

    @FXML
    private Button addAccessoryButton;


    //------------------------------Table of flowers in bouquet (for delete)-------------
    @FXML
    private TableView<Flower> deleteTableOfFlowers;

    @FXML
    private TableColumn<Flower, FlowerType> flowerTypeColumn_deleteTable;

    @FXML
    private TableColumn<Flower, String> flowerColourColumn_deleteTable;

    @FXML
    private TableColumn<Flower, Double> flowerPriceColumn_deleteTable;

    @FXML
    private TableColumn<Flower, Double> flowerLengthColumn_deleteTable;

    @FXML
    private TableColumn<Flower, Date> deliveryDateColumn_deleteTable;

    //-------------------------------------------------------------------------------------

    @FXML
    private Button backOptionDeleteFlower;

    @FXML
    private Button deleteFlowerButton;


    //------------------------------Table of accessories in bouquet (for delete)-------------
    @FXML
    private TableView<Accessory> deleteTableOfAccessories;

    @FXML
    private TableColumn<Accessory, AccessoryType> accessoryTypeColumn_deleteTable;

    @FXML
    private TableColumn<Accessory, String> accessoryColourColumn_deleteTable;

    @FXML
    private TableColumn<Accessory, Double> accessoryPriceColumn_deleteTable;

    @FXML
    private TableColumn<Accessory, String> additionalInfoColumn_deleteTable;
    //---------------------------------------------------------------------------------


    //----------------------Table of flowers in bouquet--------------------------------
    @FXML
    private TableView<Flower> infoTableOfFlowers;

    @FXML
    private TableColumn<Flower, FlowerType> flowerTypeColumn_infoTable;

    @FXML
    private TableColumn<Flower, String> flowerColourColumn_infoTable;

    @FXML
    private TableColumn<Flower, Double> flowerPriceColumn_infoTable;

    @FXML
    private TableColumn<Flower, Double> flowerLengthColumn_infoTable;

    @FXML
    private TableColumn<Flower, Date> deliveryDateColumn_infoTable;
    //-------------------------------------------------------------------------------------



    //----------------------Table of accessories in bouquet--------------------------------
    @FXML
    private TableView<Accessory> infoTableOfAccessories;

    @FXML
    private TableColumn<Accessory, AccessoryType> accessoryTypeColumn_infoTable;

    @FXML
    private TableColumn<Accessory, String> accessoryColourColumn_infoTable;

    @FXML
    private TableColumn<Accessory, Double> accessoryPriceColumn_infoTable;

    @FXML
    private TableColumn<Accessory, String> additionalInfoColumn_infoTable;
    //----------------------------------------------------------------------------------------

    @FXML
    private Button backOptionDeleteAccessory;

    @FXML
    private Button deleteAccessoryButton;


    private ObservableList<Flower> filteredFlowers;

    Double valueMinFlowerPrice, valueMaxFlowerPrice, valueMinFlowerLength, valueMaxFlowerLength;

    Double valueMinAccessoryPrice, valueMaxAccessoryPrice;

    private ObservableList<Accessory> filteredAccessories;

    private ObservableList<Flower> bouquetFlowersForTable;

    private ObservableList<Accessory> bouquetAccessoriesForTable;

    @FXML
    private Button buyBouquetButton;

    @FXML
    private Label priceOfBouquet;

    @FXML
    private Label avgLengthOfFlower;

    /**
     * Add selected item to bouquet
     * if user press Add flower to bouquet button
     * @param event Action of user
     */
    public void addSelectedFlower(ActionEvent event){
        int index = addTableOfFlowers.getSelectionModel().getSelectedIndex();
        if(index == -1){
            return;
        }
        Flower flowerForAdd = addTableOfFlowers.getItems().get(index);
        bouquetFlowers.add(flowerForAdd);
        addTableOfFlowers.getItems().remove(index);
        changeFlowerMinMaxLabels();
        refreshFlowerBouquetTable();
        changeInfoBouquet();
        buyBouquetButton.setDisable(infoTableOfFlowers.getItems().isEmpty() && infoTableOfAccessories.getItems().isEmpty());
        addFlowerButton.setDisable(addTableOfFlowers.getItems().isEmpty());
    }

    /**
     * Get all flowers from database that not in use and put them into table
     * If flowers was found - Add flower to bouquet button become able
     * @param event Action of user
     */
    @FXML
    public void getAllFlowersAction(ActionEvent event){
        filteredFlowers = FXCollections.observableList(filterAllFlowers());
        addFlowerButton.setDisable(filteredFlowers.isEmpty());
        addTableOfFlowers.setItems(filteredFlowers);
    }

    /**
     * Get flowers (that not in use) from database filtered by length and put them into tableview
     * if the min/max length fields is correct
     * If flowers was found - Add flower to bouquet button become able
     * @param event Action of user
     */
    public void filterFlowersInLengthRangeAction(ActionEvent event){
        if(!filterFlowerInLengthRangeButton.isDisable()){
            double minLimit = Double.parseDouble(minFlowerLengthRange.getText());
            double maxLimit = Double.parseDouble(maxFlowerLengthRange.getText());
            filteredFlowers = FXCollections.observableList(filterFlowersInLengthRange(minLimit, maxLimit));
            addFlowerButton.setDisable(filteredFlowers.isEmpty());
            addTableOfFlowers.setItems(filteredFlowers);
        }
    }

    /**
     * Get flowers (that not in use) from database filtered by price and put them into tableview
     * if the min/max price fields is correct
     * If flowers was found - Add flower to bouquet button become able
     * @param event Action of user
     */
    public void filterFlowersInPriceRangeAction(ActionEvent event){
        if(!filterFlowersInPriceRangeButton.isDisable()){
            double minLimit = Double.parseDouble(minFlowerPriceRange.getText());
            double maxLimit = Double.parseDouble(maxFlowerPriceRange.getText());
            filteredFlowers = FXCollections.observableList(filterFlowersInPriceRange(minLimit, maxLimit));
            addFlowerButton.setDisable(filteredFlowers.isEmpty());
            addTableOfFlowers.setItems(filteredFlowers);
        }
    }

    /**
     * Get flowers (that not in use) from database filtered by type and put them into table view
     * if flower type was selected
     * If flowers was found - Add flower to bouquet button become able
     * @param event Action of user
     */
    @FXML
    void filterFlowersByTypeAction(ActionEvent event) {
        if(!filterFlowersByTypeButton.isDisable()){
            filteredFlowers = FXCollections.observableList(filterFlowersByType(flowerType.getValue()));
            addFlowerButton.setDisable(filteredFlowers.isEmpty());
            addTableOfFlowers.setItems(filteredFlowers);
        }
    }

    /**
     * Get all flowers (that not in use) from database sorted by fresh and put them into table view
     * if sorting type was selected
     * If flowers was found - Add flower to bouquet button become able
     * @param event Action of user
     */
    @FXML
    public void sortFlowersByFreshAction(ActionEvent event) {
        if(!sortFlowersByFreshButton.isDisable()){
            filteredFlowers = FXCollections.observableList(sortFlowersByFresh(sortingTypeFlower.getValue()));
            addFlowerButton.setDisable(filteredFlowers.isEmpty());
            addTableOfFlowers.setItems(filteredFlowers);
        }
    }

    /**
     * Check length fields filled in correctly by the user
     * if the length fields is correct - set Filter in length range button able
     */
    public void correctFlowerRangeLengthFields(KeyEvent event) {
        boolean minLimit = Validator.isNumberInRange(minFlowerLengthRange, errorFlowerLengthMin,
                valueMinFlowerLength, valueMaxFlowerLength);
        maxFlowerLengthRange.setEditable(minLimit);
        boolean maxLimit = false;
        // if min limit is correct - user can fill
        if(maxFlowerLengthRange.isEditable()){
            maxLimit = Validator.isNumberInRangeBiggerThan(maxFlowerLengthRange, errorFlowerLengthMax, valueMinFlowerLength,
                    valueMaxFlowerLength, Double.parseDouble(minFlowerLengthRange.getText()));
        }
        filterFlowerInLengthRangeButton.setDisable(!minLimit || !maxLimit);
    }

    /**
     * Check price fields filled in correctly by the user
     * if the price fields is correct - set Filter in price range button able
     */
    public void correctFlowerRangePriceFields(KeyEvent event) {
        boolean minLimit = Validator.isNumberInRange(minFlowerPriceRange, errorFlowerPriceMin, valueMinFlowerPrice,
                valueMaxFlowerPrice);
        maxFlowerPriceRange.setEditable(minLimit);
        boolean maxLimit = false;
        if(maxFlowerPriceRange.isEditable()){
            maxLimit = Validator.isNumberInRangeBiggerThan(maxFlowerPriceRange, errorFlowerPriceMax, valueMinFlowerPrice,
                    valueMaxFlowerPrice, Double.parseDouble(minFlowerPriceRange.getText()));
        }

        filterFlowersInPriceRangeButton.setDisable(!minLimit || !maxLimit);
    }

    /**
     * Add selected item to bouquet
     * if user press Add accessory to bouquet button
     * @param event Action of user
     */
    public void addSelectedAccessory(ActionEvent event){
        int index = addTableOfAccessories.getSelectionModel().getSelectedIndex();
        if(index == -1){
            return;
        }
        Accessory accessoryForAdd = addTableOfAccessories.getItems().get(index);
        bouquetAccessories.add(accessoryForAdd);
        addTableOfAccessories.getItems().remove(index);
        changeAccessoryMinMaxPriceLabels();
        refreshAccessoryBouquetTable();
        changeInfoBouquet();
        buyBouquetButton.setDisable(infoTableOfFlowers.getItems().isEmpty() && infoTableOfAccessories.getItems().isEmpty());
        addAccessoryButton.setDisable(addTableOfAccessories.getItems().isEmpty());
    }

    /**
     * Get all accessories (that not in use) from database that not in use and put them into table
     * If flowers was found - add button to bouquet become able
     * @param event Action of user
     */
    public void getAllAccessoriesAction(ActionEvent event){
        filteredAccessories = FXCollections.observableList(filterAllAccessories());
        addAccessoryButton.setDisable(filteredAccessories.isEmpty());
        addTableOfAccessories.setItems(filteredAccessories);
    }

    /**
     * Get accessories (that not in use) from database filtered by type and put them into table view
     * if accessory type was selected
     * If accessories was found - add button to bouquet become able
     * @param event Action of user
     */
    public void filterAccessoriesByTypeAction(ActionEvent event){
        if(!filterAccessoriesByTypeButton.isDisable()){
            filteredAccessories = FXCollections.observableList(filterAccessoriesByType(accessoryType.getValue()));
            addAccessoryButton.setDisable(filteredAccessories.isEmpty());
            addTableOfAccessories.setItems(filteredAccessories);
        }
    }

    /**
     * Get accessories (that not in use) from database filtered by price and put them into tableview
     * if the min/max price fields is correct
     * If accessory was found - add button to bouquet become able
     * @param event Action of user
     */
    public void filterAccessoriesInPriceRangeAction(ActionEvent event){
        if(!filterAccessoriesInPriceRangeButton.isDisable()){
            double minLimit = Double.parseDouble(minAccessoryPriceRange.getText());
            double maxLimit = Double.parseDouble(maxAccessoryPriceRange.getText());
            filteredAccessories = FXCollections.observableList(filterAccessInPriceRange(minLimit, maxLimit));
            addAccessoryButton.setDisable(filteredAccessories.isEmpty());
            addTableOfAccessories.setItems(filteredAccessories);
        }
    }

    /**
     * Check accessory price fields filled in correctly by the user
     * if the price fields is correct - set Filter in price range button able
     */
    public void correctAccessoryRangePriceFields(KeyEvent event) {
        boolean minLimit = Validator.isNumberInRange(minAccessoryPriceRange, errorAccessoryPriceMin,
                valueMinAccessoryPrice, valueMaxAccessoryPrice);
        maxAccessoryPriceRange.setEditable(minLimit);
        boolean maxLimit = false;
        if(maxAccessoryPriceRange.isEditable()){
            maxLimit = Validator.isNumberInRangeBiggerThan(maxAccessoryPriceRange, errorAccessoryPriceMax,
                    valueMinAccessoryPrice, valueMaxAccessoryPrice, Double.parseDouble(minAccessoryPriceRange.getText()));
        }
        filterAccessoriesInPriceRangeButton.setDisable(!minLimit || !maxLimit);
    }

    /**
     *  Method find the min/max values of price/length of flower
     *  and refresh this values in program
     */
    public void changeFlowerMinMaxLabels(){
        SortFilterFlowersDAO sortFilterFlowersDAO = new SortFilterFlowersDAO();

        valueMinFlowerPrice = sortFilterFlowersDAO.minPrice();
        valueMaxFlowerPrice = sortFilterFlowersDAO.maxPrice();

        minFlowerPrice.setText("Min flower price: " +  valueMinFlowerPrice);
        maxFlowerPrice.setText("Max flower price: " + valueMaxFlowerPrice);

        valueMinFlowerLength = sortFilterFlowersDAO.minLength();
        valueMaxFlowerLength = sortFilterFlowersDAO.maxLength();

        minFlowerLength.setText("Min flower length: " + valueMinFlowerLength);
        maxFlowerLength.setText("Max flower length: " + valueMaxFlowerLength);
    }

    /**
     *  Method find the min/max values of price of accessory
     *  and refresh this values in program
     */
    public void changeAccessoryMinMaxPriceLabels(){
        SortFilterAccessoriesDAO sortFilterAccessoriesDAO = new SortFilterAccessoriesDAO();
        valueMinAccessoryPrice = sortFilterAccessoriesDAO.minPrice();
        valueMaxAccessoryPrice = sortFilterAccessoriesDAO.maxPrice();
        minAccessoryPrice.setText("Min accessory price: " + valueMinAccessoryPrice);
        maxAccessoryPrice.setText("Max accessory price: " + valueMaxAccessoryPrice);
    }

    /**
     * Deletes selected item from bouquet
     * if user press Delete flower button
     * @param event Action of user
     */
    public void deleteSelectedFlowerBouquet(ActionEvent event){
        int index = deleteTableOfFlowers.getSelectionModel().getSelectedIndex();
        if(index == -1){
            return;
        }
        deleteTableOfFlowers.getItems().remove(index);
        changeInfoBouquet();
        buyBouquetButton.setDisable(infoTableOfFlowers.getItems().isEmpty() && infoTableOfAccessories.getItems().isEmpty());
        deleteFlowerButton.setDisable(deleteTableOfFlowers.getItems().isEmpty());
    }

    /**
     * Deletes selected item from bouquet
     * if user press Delete accessory button
     * @param event Action of user
     */
    public void deleteSelectedAccessoryBouquet(ActionEvent event){
        int index = deleteTableOfAccessories.getSelectionModel().getSelectedIndex();
        if(index == -1){
            return;
        }
        deleteTableOfAccessories.getItems().remove(index);
        changeInfoBouquet();
        buyBouquetButton.setDisable(infoTableOfFlowers.getItems().isEmpty() && infoTableOfAccessories.getItems().isEmpty());
        deleteAccessoryButton.setDisable(deleteTableOfAccessories.getItems().isEmpty());
    }

    /**
     * Refresh the flowers(that in bouquet) in tableview
     */
    private void refreshFlowerBouquetTable(){
        bouquetFlowersForTable = FXCollections.observableList(bouquetFlowers);
        deleteFlowerButton.setDisable(bouquetFlowersForTable.isEmpty());
        deleteTableOfFlowers.setItems(bouquetFlowersForTable);
        infoTableOfFlowers.setItems(bouquetFlowersForTable);
    }

    /**
     * Refresh the accessories (that in bouquet) in tableview
     */
    private void refreshAccessoryBouquetTable(){
        bouquetAccessoriesForTable = FXCollections.observableList(bouquetAccessories);
        deleteAccessoryButton.setDisable(bouquetAccessoriesForTable.isEmpty());
        deleteTableOfAccessories.setItems(bouquetAccessoriesForTable);
        infoTableOfAccessories.setItems(bouquetAccessoriesForTable);
    }

    /**
     * Refresh bouquet info labels
     */
    private void changeInfoBouquet(){
        priceOfBouquet.setText("Price: " + priceOfBouquet());
        avgLengthOfFlower.setText("Avg length of flower: " + averageLengthOfFlowers());
    }

    /**
     * Initialize types of table columns for accessories and flowers to add to bouquet
     */
    private void initAddTables(){
        
        flowerTypeColumn_addTable.setCellValueFactory(new PropertyValueFactory<Flower, FlowerType>("flowerType"));
        flowerColourColumn_addTable.setCellValueFactory(new PropertyValueFactory<Flower, String>("colour"));
        flowerPriceColumn_addTable.setCellValueFactory(new PropertyValueFactory<Flower, Double>("price"));
        flowerLengthColumn_addTable.setCellValueFactory(new PropertyValueFactory<Flower, Double>("length"));
        deliveryDateColumn_addTable.setCellValueFactory(new PropertyValueFactory<Flower, Date>("date"));

        accessoryTypeColumn_addTable.setCellValueFactory(new PropertyValueFactory<Accessory, AccessoryType>("accessoryType"));
        accessoryColourColumn_addTable.setCellValueFactory(new PropertyValueFactory<Accessory, String>("colour"));
        accessoryPriceColumn_addTable.setCellValueFactory(new PropertyValueFactory<Accessory, Double>("price"));
        additionalInfoColumn_addTable.setCellValueFactory(new PropertyValueFactory<Accessory, String>("info"));
        
    }

    /**
     * Initialize types of table columns for accessories and flowers in bouquet for delete
     */
    private void initDeleteTables(){
        // type of flower table columns for deleting
        flowerTypeColumn_deleteTable.setCellValueFactory(new PropertyValueFactory<Flower, FlowerType>("flowerType"));
        flowerColourColumn_deleteTable.setCellValueFactory(new PropertyValueFactory<Flower, String>("colour"));
        flowerPriceColumn_deleteTable.setCellValueFactory(new PropertyValueFactory<Flower, Double>("price"));
        flowerLengthColumn_deleteTable.setCellValueFactory(new PropertyValueFactory<Flower, Double>("length"));
        deliveryDateColumn_deleteTable.setCellValueFactory(new PropertyValueFactory<Flower, Date>("date"));

        // type of accessory table columns for deleting
        accessoryTypeColumn_deleteTable.setCellValueFactory(new PropertyValueFactory<Accessory, AccessoryType>("accessoryType"));
        accessoryColourColumn_deleteTable.setCellValueFactory(new PropertyValueFactory<Accessory, String>("colour"));
        accessoryPriceColumn_deleteTable.setCellValueFactory(new PropertyValueFactory<Accessory, Double>("price"));
        additionalInfoColumn_deleteTable.setCellValueFactory(new PropertyValueFactory<Accessory, String>("info"));
        
    }

    /**
     * Initialize types of table columns for accessories and flowers that in bouquet
     */
    private void initInfoTables(){
        // type of flower table columns (in bouquet)
        flowerTypeColumn_infoTable.setCellValueFactory(new PropertyValueFactory<Flower, FlowerType>("flowerType"));
        flowerColourColumn_infoTable.setCellValueFactory(new PropertyValueFactory<Flower, String>("colour"));
        flowerPriceColumn_infoTable.setCellValueFactory(new PropertyValueFactory<Flower, Double>("price"));
        flowerLengthColumn_infoTable.setCellValueFactory(new PropertyValueFactory<Flower, Double>("length"));
        deliveryDateColumn_infoTable.setCellValueFactory(new PropertyValueFactory<Flower, Date>("date"));

        // type of accessory table columns (in bouquet)
        accessoryTypeColumn_infoTable.setCellValueFactory(new PropertyValueFactory<Accessory, AccessoryType>("accessoryType"));
        accessoryColourColumn_infoTable.setCellValueFactory(new PropertyValueFactory<Accessory, String>("colour"));
        accessoryPriceColumn_infoTable.setCellValueFactory(new PropertyValueFactory<Accessory, Double>("price"));
        additionalInfoColumn_infoTable.setCellValueFactory(new PropertyValueFactory<Accessory, String>("info"));

    }

    /**
     * Change filter accessories by type button disability
     * if accessory type was selected
     * @param event Action of user
     */

    private void filterAccessoriesByTypeButtonDisability(ActionEvent event) {
        filterAccessoriesByTypeButton.setDisable(accessoryType.getSelectionModel().isEmpty());
    }

    /**
     * Change filter flowers by fresh button disability
     * if sorting type was selected
     * @param event Action of user
     */
    private void sortFlowersByFreshButtonDisability(ActionEvent event) {
        sortFlowersByFreshButton.setDisable(sortingTypeFlower.getSelectionModel().isEmpty());
    }

    /**
     * Change filter flowers by type button disability
     * if flower type was selected
     * @param event Action of user
     */
    private void filterFlowersByTypeButtonDisability(ActionEvent event) {
        filterFlowersByTypeButton.setDisable(flowerType.getSelectionModel().isEmpty());
    }

    /**
     * Method deletes all flowers and accessories from database and bouquet
     * @param event Action of user
     */
    @FXML
    private void buyBouquetAction(ActionEvent event){
        if(!buyBouquetButton.isDisable()) {
            buyBouquetDAO();
            display("Info", "Bouquet was successfully bought!"); // message box
            // refreshing all tables
            infoTableOfFlowers.refresh();
            infoTableOfAccessories.refresh();
            deleteTableOfAccessories.refresh();
            deleteTableOfFlowers.refresh();

            buyBouquetButton.setDisable(infoTableOfFlowers.getItems().isEmpty() && infoTableOfAccessories.getItems().isEmpty());
        }
    }

    /**
     * Initializes objects of graphical interface in this stage
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        flowerType.getItems().setAll(FlowerType.values());
        flowerType.setOnAction(this::filterFlowersByTypeButtonDisability);

        sortingTypeFlower.getItems().setAll(OrderSort.values());
        sortingTypeFlower.setOnAction(this::sortFlowersByFreshButtonDisability);

        changeFlowerMinMaxLabels(); // change min/max flower length/price labels

        initAddTables();

        accessoryType.getItems().setAll(AccessoryType.values());
        accessoryType.setOnAction(this::filterAccessoriesByTypeButtonDisability);

        changeAccessoryMinMaxPriceLabels();  // change min/max accessory price labels

        initDeleteTables();

        initInfoTables();
    }

    /**
     * Switch stage to  Main Menu
     * if user selected Back button
     * also refresh the bouquet info to default
     * @param event Action from user
     */
    @FXML
    void backOption(ActionEvent event) {
        refreshBouquet();
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

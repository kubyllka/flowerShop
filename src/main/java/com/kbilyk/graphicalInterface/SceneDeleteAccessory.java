package com.kbilyk.graphicalInterface;import com.kbilyk.constant.AccessoryType;import com.kbilyk.item.Accessory;import com.kbilyk.itemDAO.AccessoryDAO;import com.kbilyk.sortfilter.SortFilterAccessoriesDAO;import com.kbilyk.validation.Validator;import javafx.collections.FXCollections;import javafx.collections.ObservableList;import javafx.event.ActionEvent;import javafx.fxml.FXML;import javafx.fxml.FXMLLoader;import javafx.fxml.Initializable;import javafx.scene.Node;import javafx.scene.Parent;import javafx.scene.Scene;import javafx.scene.control.*;import javafx.scene.control.cell.PropertyValueFactory;import javafx.scene.input.KeyEvent;import javafx.stage.Stage;import java.io.IOException;import java.net.URL;import java.util.Objects;import java.util.ResourceBundle;import static com.kbilyk.dialog.InfoDialog.display;import static com.kbilyk.sortfilter.FilterAccessoriesThatNotUsed.filterAccessInPriceRange;import static com.kbilyk.sortfilter.FilterAccessoriesThatNotUsed.filterAccessoriesByType;import static com.kbilyk.sortfilter.FilterAccessoriesThatNotUsed.filterAllAccessories;public class SceneDeleteAccessory implements Initializable {    private Stage stage;    private Scene scene; // scene delete accessory    //-----------------------------Table of accessories---------------------------    @FXML    private TableView<Accessory> tableOfAccessories;    @FXML    private TableColumn<Accessory, AccessoryType> accessoryTypeColumn;    @FXML    private TableColumn<Accessory, String> colourColumn;    @FXML    private TableColumn<Accessory, Double> priceColumn;    @FXML    private TableColumn<Accessory, String> additionalInfoColumn;    //---------------------------------------------------------------------    @FXML    private ChoiceBox<AccessoryType> accessoryType; // choosing accessory type for filtration    @FXML    private Button deleteAccessoryButton; // button that remove selected item from database    @FXML    private Button getAllAccessoriesButton; // button that get all accessories from database    @FXML    private Button filterAccessoriesByTypeButton;  // button that sort accessories (from database) by selected type    @FXML    private Button filterInPriceRangeButton;  // button that get all accessories in price range from database    @FXML    private Button backOption; // back to previous Change assortment menu    // The info about min/max accessory price in database    @FXML    private Label minPrice;    @FXML    private Label maxPrice;    // error label for user if he typed incorrect price info    @FXML    private Label errorPriceMax;    @FXML    private Label errorPriceMin;    // Input price fields for user    @FXML    private TextField maxPriceRange;    @FXML    private TextField minPriceRange;    //The min/max price of accessories in database    private double valueMinPrice;    private double valueMaxPrice;    private ObservableList<Accessory> filteredAccessories;    /**     * Deletes selected item from table and database     * if user press Delete accessory button     * @param event Action of user     */    public void deleteSelectedItem(ActionEvent event){        int index = tableOfAccessories.getSelectionModel().getSelectedIndex();        if(index == -1){            return;        }        Accessory accessoryForDelete = tableOfAccessories.getItems().get(index);        AccessoryDAO accessoryDAO = new AccessoryDAO();        if(accessoryDAO.delete(accessoryForDelete) == 1){            display("Info", "Accessory was deleted!");        }else{            display("Error", "Accessory was not deleted! Try again!");        }        tableOfAccessories.getItems().remove(index);        deleteAccessoryButton.setDisable(tableOfAccessories.getItems().isEmpty());        changeMinMaxPriceLabels();        clearFieldsAndDisableButtons();    }    /**     * Get all accessories (that not in use) from database that not in use and put them into table     * If flowers was found - delete button become able     * @param event Action of user     */    public void getAllAccessoriesAction(ActionEvent event){        filteredAccessories = FXCollections.observableList(filterAllAccessories());        deleteAccessoryButton.setDisable(filteredAccessories.isEmpty());        tableOfAccessories.setItems(filteredAccessories);    }    /**     * Get accessories (that not in use) from database filtered by type and put them into table view     * if accessory type was selected     * If accessories was found - delete button become able     * @param event Action of user     */    public void filterAccessoriesByTypeAction(ActionEvent event){        if(!filterAccessoriesByTypeButton.isDisable()){            filteredAccessories = FXCollections.observableList(filterAccessoriesByType(accessoryType.getValue()));            deleteAccessoryButton.setDisable(filteredAccessories.isEmpty());            tableOfAccessories.setItems(filteredAccessories);        }    }    /**     * Get accessories (that not in use) from database filtered by price and put them into tableview     * if the min/max price fields is correct     * If accessory was found - delete button become able     * @param event Action of user     */    public void filterAccessoriesInPriceRangeAction(ActionEvent event){        if(!filterInPriceRangeButton.isDisable()){            double minLimit = Double.parseDouble(minPriceRange.getText());            double maxLimit = Double.parseDouble(maxPriceRange.getText());            filteredAccessories = FXCollections.observableList(filterAccessInPriceRange(minLimit, maxLimit));            deleteAccessoryButton.setDisable(filteredAccessories.isEmpty());            tableOfAccessories.setItems(filteredAccessories);        }    }    /**     * Check price fields filled in correctly by the user     * if the price fields is correct - set Filter in price range button able     */    public void correctRangePriceFields(KeyEvent event) {        // correct min limit        boolean minLimit = Validator.isNumberInRange(minPriceRange, errorPriceMin, valueMinPrice, valueMaxPrice);        maxPriceRange.setEditable(minLimit);        // correct max limit        boolean maxLimit = false;        if(maxPriceRange.isEditable()){            maxLimit = Validator.isNumberInRangeBiggerThan(maxPriceRange, errorPriceMax, valueMinPrice,                    valueMaxPrice, Double.parseDouble(minPriceRange.getText()));        }        filterInPriceRangeButton.setDisable(!minLimit || !maxLimit);    }    /**     * Check if flower type for filtration was selected     * if is - set Filter flowers by type button able     */    public void filterByTypeButtonDisability(ActionEvent event){        filterAccessoriesByTypeButton.setDisable(accessoryType.getSelectionModel().isEmpty());    }    /**     *  Method find the min/max values of price     *  and refresh this values in program     */    public void changeMinMaxPriceLabels(){        SortFilterAccessoriesDAO sortFilterAccessoriesDAO = new SortFilterAccessoriesDAO();        valueMinPrice = sortFilterAccessoriesDAO.minPrice();        valueMaxPrice = sortFilterAccessoriesDAO.maxPrice();        minPrice.setText("Min accessory price: " + valueMinPrice);        maxPrice.setText("Max accessory price: " + valueMaxPrice);    }    /**     * Initialize objects of graphical interface     */    @Override    public void initialize(URL url, ResourceBundle resourceBundle) {        accessoryType.getItems().setAll(AccessoryType.values());        accessoryType.setOnAction(this::filterByTypeButtonDisability);        // finding min max values        changeMinMaxPriceLabels();        // initialize columns of table        accessoryTypeColumn.setCellValueFactory(new PropertyValueFactory<Accessory, AccessoryType>("accessoryType"));        colourColumn.setCellValueFactory(new PropertyValueFactory<Accessory, String>("colour"));        priceColumn.setCellValueFactory(new PropertyValueFactory<Accessory, Double>("price"));        additionalInfoColumn.setCellValueFactory(new PropertyValueFactory<Accessory, String>("info"));    }    public void clearFieldsAndDisableButtons(){        minPriceRange.clear();        maxPriceRange.clear();        filterInPriceRangeButton.setDisable(true);    }    /**     * Switch stage to Change Assortment Menu     * if user selected Back button     * @param event Action from user     */    @FXML    public void backOption(ActionEvent event) {        try {            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxmlFiles/changeAssortmentMenu.fxml")));            stage = (Stage)((Node)event.getSource()).getScene().getWindow();            scene = new Scene(root);            stage.setScene(scene);        } catch (IOException ex) {            throw new RuntimeException(ex);        }    }}
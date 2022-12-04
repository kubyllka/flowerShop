package com.kbilyk.sortfilter;

import com.kbilyk.constant.AccessoryType;
import com.kbilyk.item.Accessory;

import java.util.ArrayList;

import static com.kbilyk.bouquet.Bouquet.bouquetAccessories;



public class FilterAccessoriesThatNotUsed {

    /**
     * The method that searches for accessories of a given type that not in use
     * (not in bouquet)
     * @return The list of accessories of a given type that not in use
     */
    public static ArrayList<Accessory> filterAccessoriesByType(AccessoryType accessoryType) {
        SortFilterAccessoriesDAO sortFilterAccessoriesDAO = new SortFilterAccessoriesDAO();
        ArrayList<Accessory> filteredAccessories = sortFilterAccessoriesDAO.sortByType(accessoryType);
        filteredAccessories.removeAll(bouquetAccessories);
        return filteredAccessories;
    }

    /**
     * The method that returns all accessories that not in use
     * (not in bouquet)
     * @return The list of all accessories that not used
     */

    public static ArrayList<Accessory> filterAllAccessories() {
        SortFilterAccessoriesDAO sortFilterAccessoriesDAO = new SortFilterAccessoriesDAO();
        ArrayList<Accessory> filteredAccessories = sortFilterAccessoriesDAO.getAll();
        filteredAccessories.removeAll(bouquetAccessories);
        return filteredAccessories;
    }

    /**
     * The method that searches for accessories in a price range that not in use
     * (not in bouquet)
     * @return The list of accessories in the price range that not in use
     */
    public static ArrayList<Accessory> filterAccessInPriceRange(double enteredMin, double enteredMax) {
        SortFilterAccessoriesDAO filtrationAccessory = new SortFilterAccessoriesDAO();
        ArrayList<Accessory> filteredAccessories = filtrationAccessory.filterInPriceRange(enteredMin, enteredMax);
        filteredAccessories.removeAll(bouquetAccessories);
        return filteredAccessories;
    }
}

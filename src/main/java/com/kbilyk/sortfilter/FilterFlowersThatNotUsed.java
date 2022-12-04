package com.kbilyk.sortfilter;

import com.kbilyk.constant.FlowerType;
import com.kbilyk.constant.OrderSort;
import com.kbilyk.item.Flower;

import java.util.ArrayList;

import static com.kbilyk.bouquet.Bouquet.bouquetFlowers;


public class FilterFlowersThatNotUsed {

    /**
     * The method that sorts all flowers in ascending or descending order of freshness that not in use
     * (not in bouquet)
     * @return The list of all flowers in ascending or descending order of freshness
     */
    public static ArrayList<Flower> sortFlowersByFresh(OrderSort orderSort){
        SortFilterFlowersDAO filtrationFlower = new SortFilterFlowersDAO();
        ArrayList<Flower> filteredFlowers = filtrationFlower.sortByFresh(orderSort);
        filteredFlowers.removeAll(bouquetFlowers);
        return filteredFlowers;
    }

    /**
     * The method that returns all flowers that not in use
     * (not in bouquet)
     * @return The list of all flowers that not used
     */
    public static ArrayList<Flower> filterAllFlowers(){
        SortFilterFlowersDAO filtrationFlower = new SortFilterFlowersDAO();
        ArrayList<Flower> filteredFlowers = filtrationFlower.getAll();
        filteredFlowers.removeAll(bouquetFlowers);
        return filteredFlowers;
    }

    /**
     * The method that searches for flowers of a given type that not in use
     * (not in bouquet)
     * @return The list of flowers of a given type that not in use
     */
    public static ArrayList<Flower> filterFlowersByType(FlowerType flowerType){
        SortFilterFlowersDAO filtrationFlower = new SortFilterFlowersDAO();
        ArrayList<Flower> filteredFlowers = filtrationFlower.sortByType(flowerType);
        filteredFlowers.removeAll(bouquetFlowers);
        return filteredFlowers;
    }

    /**
     * The method that searches for flowers in a price range that not in use
     * (not in bouquet)
     * @return The list of flowers in the price range that not in use
     */
    public static ArrayList<Flower> filterFlowersInPriceRange(double enteredMin, double enteredMax){
        SortFilterFlowersDAO filtrationFlower = new SortFilterFlowersDAO();
        ArrayList<Flower> filteredFlowers = filtrationFlower.filterInPriceRange(enteredMin, enteredMax);
        filteredFlowers.removeAll(bouquetFlowers);
        return filteredFlowers;
    }

    /**
     * The method that searches for flowers in a length range that not in use
     * (not in bouquet)
     * @return The list of flowers in the length range
     */
    public static ArrayList<Flower> filterFlowersInLengthRange(double enteredMin, double enteredMax){
        SortFilterFlowersDAO filtrationFlower = new SortFilterFlowersDAO();
        ArrayList<Flower> filteredFlowers = filtrationFlower.filterInLengthRange(enteredMin, enteredMax);
        filteredFlowers.removeAll(bouquetFlowers);
        return filteredFlowers;
    }
}

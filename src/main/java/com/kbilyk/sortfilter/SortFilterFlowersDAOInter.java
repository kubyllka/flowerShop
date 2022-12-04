package com.kbilyk.sortfilter;

import com.kbilyk.constant.FlowerType;
import com.kbilyk.constant.OrderSort;
import com.kbilyk.item.Flower;

import java.util.List;

public interface SortFilterFlowersDAOInter {
    /**
     * The method that returns all flowers
     * @return The list of all flowers
     */
    List<Flower> getAll();

    /**
     * The method that searches for flowers in a price range
     * @param min The minimum search limit
     * @param max The maximum search limit
     * @return The list of flowers in the price range
     */
    List<Flower> filterInPriceRange(double min, double max);

    /**
     * The method that searches for flowers in a length range
     * @param min The minimum search limit
     * @param max The maximum search limit
     * @return The list of flowers in the length range
     */
    List<Flower> filterInLengthRange(double min, double max);

    /**
     * The method that searches for flowers of a given type
     * @param flowerType The flower type
     * @return The list of flowers of a given type
     */
    List<Flower> sortByType(FlowerType flowerType);

    /**
     * The method that sorts all flowers in ascending or descending order of freshness
     * @param orderSort Sorting type (ascending OR descending)
     * @return The list of all flowers in ascending or descending order of freshness
     */
    List<Flower> sortByFresh(OrderSort orderSort);

    /**
     * The method that looks for the maximum price of a flower
     * @return The maximum price of a flower
     */
    Double maxPrice();

    /**
     * The method that looks for the minimum price of a flower
     * @return The minimum price of a flower
     */
    Double minPrice();

    /**
     * The method that looks for the minimum length of a flower
     * @return The minimum price of a flower
     */

    Double minLength();

    /**
     * The method that looks for the maximum length of a flower
     * @return The maximum length of a flower
     */
    Double maxLength();
}

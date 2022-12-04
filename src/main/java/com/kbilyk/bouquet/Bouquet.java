package com.kbilyk.bouquet;
import com.kbilyk.item.Accessory;
import com.kbilyk.item.Flower;
import com.kbilyk.itemDAO.AccessoryDAO;
import com.kbilyk.itemDAO.FlowerDAO;

import java.util.ArrayList;

public class Bouquet {

    // The flowers in bouquet
    public static ArrayList<Flower> bouquetFlowers = new ArrayList<>();
    // The accessories in bouquet
    public static ArrayList<Accessory> bouquetAccessories = new ArrayList<>();

    /**
     * Method that calculates the price of a bouquet
     * @return The price of bouquet
     */
    public static double priceOfBouquet(){
        double price = 0.0;
        for(Flower flower : bouquetFlowers) price += flower.getPrice();
        for(Accessory accessory : bouquetAccessories) price += accessory.getPrice();
        return price;
    }

    /**
     * Method that calculates the average length of stems of a bouquet
     * @return The average length of bouquet
     */
    public static double averageLengthOfFlowers(){
        double length = 0.0;
        for(Flower flower : bouquetFlowers) length += flower.getLength();
        if(length == 0){
            return 0.0;
        }else{
            return length / bouquetFlowers.size();
        }
    }

    /**
     * Method buy a bouquet
     * All flowers and accessories that are in the bouquet are removed from the database
     */
    public static void buyBouquetDAO(){
        AccessoryDAO aDao = new AccessoryDAO();
        aDao.deleteAllFromBouquet();
        FlowerDAO fDao = new FlowerDAO();
        fDao.deleteAllFromBouquet();
    }

    /**
     * A method that updates the bouquet when the user exits the bouquet creation menu
     * (removes all flowers and accessories from it)
     */
    public static void refreshBouquet(){
        bouquetFlowers.clear();
        bouquetAccessories.clear();
    }
}

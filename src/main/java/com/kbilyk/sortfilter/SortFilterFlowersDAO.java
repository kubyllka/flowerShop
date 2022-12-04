package com.kbilyk.sortfilter;

import com.kbilyk.item.Flower;
import com.kbilyk.databaseConnection.Database;
import com.kbilyk.constant.FlowerType;
import com.kbilyk.constant.OrderSort;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static com.kbilyk.databaseConnection.Database.getConnection;



public class SortFilterFlowersDAO implements SortFilterFlowersDAOInter {

    private static final Logger logger = LogManager.getLogger(SortFilterFlowersDAO.class.getName());
    private static final String GET_ALL_FLOWER = "SELECT * FROM flowers";

    private static final String SORT_BY_TYPE_FLOWER = "SELECT * FROM flowers WHERE flowerTypeID = ?";

    private static final String SORT_BY_FRESH_FLOWER = "SELECT flowerID, flowerTypeID, flowerColour, flowerLength, " +
            "flowerPrice, fDateDelivery FROM flowers ORDER BY fDateDelivery ";

    private static final String FILTER_FLOWERS_IN_PRICE_RANGE = "SELECT * FROM flowers WHERE flowerPrice >= ? AND" +
            " flowerPrice <= ? ";

    private static final String FILTER_FLOWERS_IN_LENGTH_RANGE = "SELECT * FROM flowers WHERE flowerLength >= ? AND" +
            " flowerLength <= ? ";

    private static final String MIN_PRICE_FLOWER = "SELECT MIN(flowerPrice) FROM flowers";

    private static final String MAX_PRICE_FLOWER = "SELECT MAX(flowerPrice) FROM flowers";

    private static final String MIN_LENGTH_FLOWER = "SELECT MIN(flowerLength) FROM flowers";

    private static final String MAX_LENGTH_FLOWER = "SELECT MAX(flowerLength) FROM flowers";


    /**
     * The method that returns all flowers from database
     * @return The list of all flowers from database
     */
    public ArrayList<Flower> getAll() {
        Database.connection = getConnection();
        ArrayList<Flower> flowers = new ArrayList<>();

        try {
            PreparedStatement ps = Database.connection.prepareStatement(GET_ALL_FLOWER);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Flower flower = Flower.builder()
                        .setId(rs.getInt("flowerID"))
                        .setFlowerType(FlowerType.getFlowerType(rs.getInt("flowerTypeID")))
                        .setColour(rs.getString("flowerColour"))
                        .setLength(rs.getDouble("flowerLength"))
                        .setPrice(rs.getDouble("flowerPrice"))
                        .setDate(rs.getDate("fDateDelivery"))
                        .built();

                flowers.add(flower);
            }

            if(flowers.isEmpty()){
                logger.info("getAll(). There are no flowers in database.");
            }else{
                logger.info("getAll(). All flowers are retrieved from the database.");
            }

        } catch (SQLException e) {
            logger.error("Сan`t do " + GET_ALL_FLOWER + " in database: " + e.getMessage());
        }
        return flowers;
    }


    /**
     * The method that searches for flowers in some range depends on statement
     * @param min The minimum search limit
     * @param max The maximum search limit
     * @return The list of flowers in some range depends on statement
     */
    public ArrayList<Flower> filterInRangeWithStatement(double min, double max, String statement){
        Database.connection = getConnection();
        ArrayList<Flower> flowers = new ArrayList<>();
        try {
            PreparedStatement ps = Database.connection.prepareStatement(statement);
            ps.setDouble(1, min);
            ps.setDouble(2, max);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Flower flower = Flower.builder()
                        .setId(rs.getInt("flowerID"))
                        .setFlowerType(FlowerType.getFlowerType(rs.getInt("flowerTypeID")))
                        .setColour(rs.getString("flowerColour"))
                        .setLength(rs.getDouble("flowerLength"))
                        .setPrice(rs.getDouble("flowerPrice"))
                        .setDate(rs.getDate("fDateDelivery"))
                        .built();

                flowers.add(flower);
            }

            if(flowers.isEmpty()){
                logger.info("filterInRangeWithStatement(). There are no flowers in this range:\n" + statement
                + "min = " + min + " max = " + max);
            }else{
                logger.info("filterInRangeWithStatement(). All accessories in range are retrieved from the database:\n"
                        + statement + " min = " + min + " max = " + max);
            }

        } catch (SQLException e) {
            logger.error("Сan`t do " + statement + " in database: " + e.getMessage());
        }
        return flowers;
    }

    /**
     * The method that searches for flowers in a price range in database
     * @param min The minimum search limit
     * @param max The maximum search limit
     * @return The list of flowers in the price range in database
     */

    public ArrayList<Flower> filterInPriceRange(double min, double max) {
        return filterInRangeWithStatement(min, max, FILTER_FLOWERS_IN_PRICE_RANGE);
    }

    /**
     * The method that searches for flowers in a length range from database
     * @param min The minimum search limit
     * @param max The maximum search limit
     * @return The list of flowers in the length range from database
     */

    public ArrayList<Flower> filterInLengthRange(double min, double max) {
        return filterInRangeWithStatement(min, max, FILTER_FLOWERS_IN_LENGTH_RANGE);
    }

    /**
     * The method that searches for flowers of a given type from database
     * @param flowerType The flower type
     * @return The list of flowers of a given type
     */

    public ArrayList<Flower> sortByType(FlowerType flowerType) {
        Database.connection = getConnection();
        ArrayList<Flower> flowers = new ArrayList<>();

        try {
            PreparedStatement ps = Database.connection.prepareStatement(SORT_BY_TYPE_FLOWER);
            ps.setInt(1, flowerType.getNumType());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Flower flower = Flower.builder()
                        .setId(rs.getInt("flowerID"))
                        .setFlowerType(FlowerType.getFlowerType(rs.getInt("flowerTypeID")))
                        .setColour(rs.getString("flowerColour"))
                        .setLength(rs.getDouble("flowerLength"))
                        .setPrice(rs.getDouble("flowerPrice"))
                        .setDate(rs.getDate("fDateDelivery"))
                        .built();

                flowers.add(flower);
            }

            if(flowers.isEmpty()){
                logger.info("sortByType(). There are no flowers with type" + flowerType + " in database.");
            }else{
                logger.info("sortByType(). All flowers with type " + flowerType +
                        " are retrieved from the database.");
            }
        } catch (SQLException e) {
            logger.error("Сan`t do " + SORT_BY_TYPE_FLOWER + " in database: " + e.getMessage());
        }
        return flowers;
    }

    /**
     * The method that sorts all flowers in ascending or descending order of freshness from database
     * @param sort Sorting type (ascending OR descending)
     * @return The list of all flowers in ascending or descending order of freshness from database
     */

    public ArrayList<Flower> sortByFresh(OrderSort sort) {
        Database.connection = getConnection();
        ArrayList<Flower> flowers = new ArrayList<>();

        try {
            PreparedStatement ps = Database.connection.prepareStatement(SORT_BY_FRESH_FLOWER + sort.getEnumType());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Flower flower = Flower.builder()
                        .setId(rs.getInt("flowerID"))
                        .setFlowerType(FlowerType.getFlowerType(rs.getInt("flowerTypeID")))
                        .setColour(rs.getString("flowerColour"))
                        .setLength(rs.getDouble("flowerLength"))
                        .setPrice(rs.getDouble("flowerPrice"))
                        .setDate(rs.getDate("fDateDelivery"))
                        .built();

                flowers.add(flower);
            }

            if(flowers.isEmpty()){
                logger.info("sortByFresh(). There are no flowers in database.");
            }else {
                logger.info("sortByFresh(). All flowers are retrieved from the database: \n"
                        + SORT_BY_FRESH_FLOWER + sort);
            }
        } catch (SQLException e) {
            logger.error("Сan`t do " + SORT_BY_FRESH_FLOWER + sort + " in database: \n" + e.getMessage());
        }
        return flowers;
    }

    /**
     * The method that processes a sql query to find some maximum or minimum value from database
     * @param statement The statement of sql query
     * @return The min or max value
     */
    public Double minOrMaxStatement(String statement){
        Database.connection = getConnection();

        try {
            PreparedStatement ps = Database.connection.prepareStatement(statement);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return rs.getDouble(1);
            }
        } catch (SQLException e) {
            logger.error("Сan`t do " + statement + " in database: " + e.getMessage());
        }
        return null;
    }

    /**
     * The method that looks for the maximum price of a flower in database
     * @return The maximum price of a flower in database
     */

    public Double maxPrice() {
        return minOrMaxStatement(MAX_PRICE_FLOWER);
    }

    /**
     * The method that looks for the minimum price of a flower in database
     * @return The minimum price of a flower in database
     */

    public Double minPrice() {
        return minOrMaxStatement(MIN_PRICE_FLOWER);
    }

    /**
     * The method that looks for the minimum length of a flower in database
     * @return The minimum price of a flower
     */

    public Double minLength(){
        return minOrMaxStatement(MIN_LENGTH_FLOWER);
    }

    /**
     * The method that looks for the maximum length of a flower in database
     * @return The maximum length of a flower in database
     */

    public Double maxLength(){
        return minOrMaxStatement(MAX_LENGTH_FLOWER);
    }
}

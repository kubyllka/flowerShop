package com.kbilyk.itemDAO;
import com.kbilyk.constant.FlowerType;
import com.kbilyk.databaseConnection.Database;
import com.kbilyk.item.Flower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

import static com.kbilyk.bouquet.Bouquet.bouquetFlowers;

public class FlowerDAO implements ItemDAO<Flower> {

    private static final Logger logger = LogManager.getLogger(FlowerDAO.class);
    private static final String GET_FLOWER_BY_ID = "SELECT * FROM flowers WHERE flowerID = ?";
    private static final String DELETE_FLOWER_BY_ID = "DELETE FROM flowers WHERE flowerID = ?";
    private static final String INSERT_FLOWER = "INSERT INTO flowers ( flowerTypeID, flowerColour, flowerLength," +
            " flowerPrice) VALUES( ?, ?, ?, ?);";

    /**
     * Method searches for a flower with the specified id in the database
     * @param id The id of flower
     * @return The flower with the specified id
     */
    @Override
    public Flower getById(int id) {
       Database.connection = Database.getConnection();
        Flower flower = null;

        try {
            PreparedStatement ps = Database.connection.prepareStatement(GET_FLOWER_BY_ID);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                flower = Flower.builder()
                        .setId(rs.getInt("flowerID"))
                        .setFlowerType(FlowerType.getFlowerType(rs.getInt("flowerTypeID")))
                        .setColour(rs.getString("flowerColour"))
                        .setLength(rs.getDouble("flowerLength"))
                        .setPrice(rs.getDouble("flowerPrice"))
                        .setDate(rs.getDate("fDateDelivery"))
                        .built();
            }

            if(flower == null){
                logger.info("Cannot find the flower with id = " + id + " in database.");
            }else{
                logger.info("The flower with id = " + id + " was found in database.");
            }

            return flower;

        } catch (SQLException e) {
            logger.error("An error occurred while searching flower with id = " + id + ".\n" + e.getMessage());
        }
        return null;
    }

    /**
     * Method that deletes the specified flower from database
     * @param flower The flower to delete
     * @return The number of successful removal operations
     */

    @Override
    public int delete(Flower flower) {
        Database.connection = Database.getConnection();

        try {
            PreparedStatement ps = Database.connection.prepareStatement(DELETE_FLOWER_BY_ID);
            ps.setInt(1, flower.getId());
            int res = ps.executeUpdate();

            if(res == 0){
                logger.info("The flower " + flower + " was NOT deleted from database.");
            }else{
                logger.info("The flower " + flower + " was deleted from database.");
            }

            return res;

        } catch (SQLException e) {
            logger.error("An error occurred while deleting flower" + flower + ".\n " + e.getMessage());
            return 0;
        }
    }

    /**
     * Method that inserts the flower to the database
     * @param flower The flower to insert
     * @return The number of successful inserting operations
     */
    @Override
    public int insert(Flower flower) {
        Database.connection = Database.getConnection();

        try {
            PreparedStatement ps = Database.connection.prepareStatement(INSERT_FLOWER);
            ps.setInt(1, flower.getFlowerType().getNumType());
            ps.setString(2, flower.getColour());
            ps.setDouble(3,  flower.getLength());
            ps.setDouble(4,  flower.getPrice());

            int res = ps.executeUpdate();

            if(res == 0){
                logger.info("The flower " + flower + " was NOT inserted to database.");
            }else{
                logger.info("The flower " + flower + " was inserted to database.");
            }

            return res;

        } catch (SQLException e) {
            logger.error("An error occurred during insertion new flower" + flower + ".\n " + e.getMessage());
        }
        return 0;
    }

    /**
     * Method deletes all flowers that is in bouquet from database
     */
    @Override
    public void deleteAllFromBouquet(){
        for(Flower flower : bouquetFlowers){
            delete(flower);
        }
        bouquetFlowers.clear();
    }
}

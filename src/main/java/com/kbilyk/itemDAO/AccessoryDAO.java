package com.kbilyk.itemDAO;
import com.kbilyk.item.Accessory;
import com.kbilyk.databaseConnection.Database;
import com.kbilyk.constant.AccessoryType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static com.kbilyk.bouquet.Bouquet.bouquetAccessories;


public class AccessoryDAO implements ItemDAO<Accessory> {
    private static final Logger logger = LogManager.getLogger(AccessoryDAO.class);

    // SQL statements
    private static final String GET_ACCESSORY_BY_ID = "SELECT * FROM accessories WHERE accessoryID = ?";
    private static final String DELETE_ACCESSORY_BY_ID = "DELETE FROM accessories WHERE accessoryID = ?";
    private static final String INSERT_ACCESSORY = "INSERT INTO accessories (accessoryTypeID, accessoryColour," +
            " accessoryPrice, accessoryInfo) VALUES( ?, ?, ?, ?);";

    /**
     * Method searches for an accessory with the specified id in the database
     * @param id The id of accessory
     * @return The accessory with the specified id
     */

    @Override
    public Accessory getById(int id) {
        Database.connection = Database.getConnection();
        Accessory accessory = null;
        try {
            PreparedStatement ps = Database.connection.prepareStatement(GET_ACCESSORY_BY_ID);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                accessory = Accessory.builder()
                        .setId(rs.getInt("accessoryID"))
                        .setAccessoryType(AccessoryType.getAccessoryType(rs.getInt("accessoryTypeID")))
                        .setColour(rs.getString("accessoryColour"))
                        .setPrice(rs.getDouble("accessoryPrice"))
                        .setInfo(rs.getString("accessoryInfo"))
                        .built();
            }

            if(accessory == null){
                logger.info("Cannot find the accessory with id = " + id);
            }else{
                logger.info("The accessory with id = " + id + " was found.");
            }

            return accessory;

        } catch (SQLException e) {
            logger.error("An error occurred while searching accessory with id = " + id + ".\n" + e.getMessage());
        }
        return null;
    }

    /**
     * Method that deletes the specified accessory in the database
     * @param accessory The accessory to delete
     * @return The number of successful removal operations
     */
    @Override
    public int delete(Accessory accessory) {
        Database.connection = Database.getConnection();

        try {
            PreparedStatement ps = Database.connection.prepareStatement(DELETE_ACCESSORY_BY_ID);
            ps.setInt(1, accessory.getId());
            int res = ps.executeUpdate();

            if(res == 0){
                logger.info("The accessory " + accessory + " was NOT deleted from database.");
            }else{
                logger.info("The accessory " + accessory + " was deleted from database.");
            }

            return res;

        } catch (SQLException e) {
            logger.error("An error occurred while deleting accessory" + accessory + ".\n " + e.getMessage());
        }
        return 0;
    }

    /**
     * Method that inserts the specified accessory to the database
     * @param accessory The accessory to insert
     * @return The number of successful inserting operations
     */
    @Override
    public int insert(Accessory accessory) {
        Database.connection = Database.getConnection();

        try {
            PreparedStatement ps = Database.connection.prepareStatement(INSERT_ACCESSORY);
            ps.setInt(1, accessory.getAccessoryType().getNumType());
            ps.setString(2, accessory.getColour());
            ps.setDouble(3,  accessory.getPrice());
            ps.setString(4,  accessory.getInfo());

            int res = ps.executeUpdate();

            if(res == 0){
                logger.info("The accessory " + accessory + " was NOT inserted to database.");
            }else{
                logger.info("The accessory " + accessory + " was inserted to database.");
            }

            return res;

        } catch (SQLException e) {
            logger.error("An error occurred during insertion new accessory" + accessory + ".\n " + e.getMessage());
        }
        return 0;
    }



    /**
     * Method deletes all accessories that is in bouquet from database
     */
    @Override
    public void deleteAllFromBouquet(){
        for(Accessory accessory : bouquetAccessories){
            delete(accessory);
        }
        bouquetAccessories.clear();
    }
}

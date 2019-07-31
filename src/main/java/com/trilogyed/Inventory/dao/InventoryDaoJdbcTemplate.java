package com.trilogyed.Inventory.dao;

import com.trilogyed.Inventory.model.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class InventoryDaoJdbcTemplate implements InventoryDao {

    //-------------------------Prepared Statements (CRUD QUERIES) ---------------------//

    private static final String INSERT_INVENTORY_SQL =
            "insert into inventory (product_id,quantity) values (?,?)";

    private static final String SELECT_INVENTORY_SQL =
            "select * from inventory where inventory_id = ?";

    private static final String SELECT_ALL_INVENTORY_SQL =
            "select * from inventory";

    private static final String UPDATE_INVENTORY_SQL =
            "update inventory set product_id = ?, quantity =? where inventory_id=?";

    private static final String DELETE_INVENTORY_SQL =
            "delete from inventory where inventory_id = ?";

    //---------------------------------------------------------------------------------//

    //=============================== Implementing Methods from InventoryDao Interface Class ===========================//


    @Autowired
    JdbcTemplate jdbcTemplate;

    public InventoryDaoJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // ------------------ Using the jdbcTemplate variable to run each prepared statement and map to the DTO ----------//

    @Override
    @Transactional
    public Inventory createInventory(Inventory inventory){
        jdbcTemplate.update(INSERT_INVENTORY_SQL,
                inventory.getProductId(),
                inventory.getQuantity());

        int id = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);

        inventory.setInventoryId(id);

        return inventory;
    }

    @Override
    public Inventory getInventory(int id){

        try{
            return jdbcTemplate.queryForObject(SELECT_INVENTORY_SQL, this::mapRowToInventory,id);
        } catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public List<Inventory> getAllInventorys(){
        return jdbcTemplate.query(SELECT_ALL_INVENTORY_SQL, this::mapRowToInventory);
    }

    @Override
    public void updateInventory(Inventory inventory){
        jdbcTemplate.update(UPDATE_INVENTORY_SQL,
                inventory.getProductId(),
                inventory.getQuantity(),
                inventory.getInventoryId());
    }


    @Override
    public void deleteInventory(int id){

        jdbcTemplate.update(DELETE_INVENTORY_SQL,id);
    }

    private Inventory mapRowToInventory(ResultSet rs, int rowNum) throws SQLException {

        Inventory inventory = new Inventory();
        inventory.setInventoryId(rs.getInt("inventory_id"));
        inventory.setProductId(rs.getInt("product_id"));
        inventory.setQuantity(rs.getInt("quantity"));

        return inventory;

    }
    //----------------------------------------------------------------------------------------------------------------//
    //================================================================================================================//


}

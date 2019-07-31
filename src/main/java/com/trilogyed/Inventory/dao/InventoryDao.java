package com.trilogyed.Inventory.dao;


import com.trilogyed.Inventory.model.Inventory;

import java.util.List;

public interface InventoryDao {

    //-------------- Basic CRUD Methods ------------//

    Inventory createInventory(Inventory inventory);
    Inventory getInventory(int id);
    List<Inventory> getAllInventorys();
    void updateInventory(Inventory inventory);
    void deleteInventory(int id);

    // -------------------------------------------//

}

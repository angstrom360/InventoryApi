package com.trilogyed.Inventory.dao;


import com.trilogyed.Inventory.model.Inventory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class InventoryDaoTest {

    @Autowired
    InventoryDao inventoryDao;
    @Before
    public void SetUp() throws Exception{

        List<Inventory> cList = inventoryDao.getAllInventorys();

        for(Inventory c : cList){
            inventoryDao.deleteInventory(c.getInventoryId());
        }

    }

    @Test
    public void addGetDeleteInventory(){
        Inventory inventory = new Inventory();
        inventory.setProductId(1);
        inventory.setQuantity(12);
        inventory = inventoryDao.createInventory(inventory);

        Inventory inventory1 = inventoryDao.getInventory(inventory.getInventoryId());

        assertEquals(inventory1,inventory);

        inventoryDao.deleteInventory(inventory.getInventoryId());

        inventory1 = inventoryDao.getInventory(inventory.getInventoryId());

        assertNull(inventory1);

    }

    @Test
    public void getAllInventorys(){

        Inventory inventory = new Inventory();
        inventory.setProductId(1);
        inventory.setQuantity(12);
        inventory = inventoryDao.createInventory(inventory);

        inventory =new Inventory();
        inventory.setProductId(2);
        inventory.setQuantity(10);
        inventoryDao.createInventory(inventory);

        List<Inventory> cList = inventoryDao.getAllInventorys();

        assertEquals(cList.size(),2);
    }

    @Test
    public void updateInventory(){
        Inventory inventory = new Inventory();
        inventory.setProductId(1);
        inventory.setQuantity(12);
        inventory = inventoryDao.createInventory(inventory);

        inventory.setQuantity(22);
        inventoryDao.updateInventory(inventory);

        Inventory inventory1 = inventoryDao.getInventory(inventory.getInventoryId());

        assertEquals(inventory1,inventory);
    }




}

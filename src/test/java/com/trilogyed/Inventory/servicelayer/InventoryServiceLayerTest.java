package com.trilogyed.Inventory.servicelayer;

import com.trilogyed.Inventory.dao.InventoryDao;
import com.trilogyed.Inventory.dao.InventoryDaoJdbcTemplate;
import com.trilogyed.Inventory.model.Inventory;
import com.trilogyed.Inventory.viewmodel.InventoryViewModel;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class InventoryServiceLayerTest {

    @Autowired
    InventoryDao inventoryDao;

    @Autowired
    InventoryServiceLayer inventoryServiceLayer;

    private void setUpInventoryDaoMock(){
        inventoryDao = mock(InventoryDaoJdbcTemplate.class);

        Inventory inventory = new Inventory();
        inventory.setInventoryId(1);
        inventory.setProductId(1);
        inventory.setQuantity(25);

        Inventory inventory1 = new Inventory();
        inventory1.setProductId(1);
        inventory1.setQuantity(25);

        List<Inventory> cList = new ArrayList<>();
        cList.add(inventory);

        doReturn(inventory).when(inventoryDao).createInventory(inventory1);
        doReturn(inventory).when(inventoryDao).getInventory(1);
        doReturn(cList).when(inventoryDao).getAllInventorys();

    }

    @Before
    public void setUp()throws Exception{
        setUpInventoryDaoMock();
        inventoryServiceLayer = new InventoryServiceLayer(inventoryDao);
    }

    @Test
    public void createInventory(){
        InventoryViewModel inventory = new InventoryViewModel();
        inventory.setInventoryId(1);
        inventory.setProductId(1);
        inventory.setQuantity(25);
        inventory = inventoryServiceLayer.createInventory(inventory);

        InventoryViewModel inventory1 = new InventoryViewModel();
        inventory1.setInventoryId(1);
        inventory1.setProductId(1);
        inventory1.setQuantity(25);

        InventoryViewModel fromService = inventoryServiceLayer.createInventory(inventory1);
        assertEquals(inventory,fromService);

    }

    @Test
    public void getInventory(){

        InventoryViewModel inventory = new InventoryViewModel();
        inventory.setInventoryId(1);
        inventory.setProductId(1);
        inventory.setQuantity(25);
        inventory = inventoryServiceLayer.createInventory(inventory);

        InventoryViewModel fromService = inventoryServiceLayer.getInventory(1);
        assertEquals(inventory,fromService);

    }

    @Test
    public void getAllInventory(){

        InventoryViewModel inventory = new InventoryViewModel();
        inventory.setInventoryId(1);
        inventory.setProductId(1);
        inventory.setQuantity(25);
        inventory = inventoryServiceLayer.createInventory(inventory);

        List<InventoryViewModel> fromService = inventoryServiceLayer.getAllInventory();
        assertEquals(1,fromService.size());
        assertEquals(inventory,fromService.get(0));

    }
}

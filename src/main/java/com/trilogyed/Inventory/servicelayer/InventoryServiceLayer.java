package com.trilogyed.Inventory.servicelayer;


import com.trilogyed.Inventory.dao.InventoryDao;
import com.trilogyed.Inventory.model.Inventory;
import com.trilogyed.Inventory.viewmodel.InventoryViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
@RefreshScope
public class InventoryServiceLayer {

    @Autowired
    InventoryDao inventoryDao;

    @Autowired
    public InventoryServiceLayer(InventoryDao inventoryDao) {
        this.inventoryDao = inventoryDao;
    }
    private InventoryViewModel buildInventoryViewModel (Inventory inventory){
        InventoryViewModel inventoryViewModel = new InventoryViewModel();
        inventoryViewModel.setInventoryId(inventory.getInventoryId());
        inventoryViewModel.setProductId(inventory.getProductId());
        inventoryViewModel.setQuantity(inventory.getQuantity());

        return inventoryViewModel;
    }
    /**---------------------------------------------------------------------------------------------------------------*/

    /**The Business Logic for the service Layer is CRUD */
    @Transactional
    public InventoryViewModel createInventory(InventoryViewModel inventoryViewModel){

        Inventory inventory = new Inventory();
        inventory.setProductId(inventoryViewModel.getProductId());
        inventory.setQuantity(inventoryViewModel.getQuantity());
        inventory = inventoryDao.createInventory(inventory);
        inventoryViewModel.setInventoryId(inventory.getInventoryId());

        return inventoryViewModel;
    }

    public InventoryViewModel getInventory(int id){
        Inventory inventory = inventoryDao.getInventory(id);
        if(inventory == null)
            return null;
        else
            return buildInventoryViewModel(inventory);

    }

    public List<InventoryViewModel> getAllInventory(){
        List<Inventory> inventoryList = inventoryDao.getAllInventorys();
        List<InventoryViewModel> inventoryViewModelList = new ArrayList<>();

        for(Inventory cList : inventoryList){
            InventoryViewModel inventoryViewModel = buildInventoryViewModel(cList);
            inventoryViewModelList.add(inventoryViewModel);
        }

        return inventoryViewModelList;
    }

    public void updateInventory(InventoryViewModel inventoryViewModel){
        Inventory inventory = new Inventory();
        inventory.setInventoryId(inventoryViewModel.getInventoryId());
        inventory.setProductId(inventoryViewModel.getProductId());
        inventory.setQuantity(inventoryViewModel.getQuantity());
        inventoryDao.updateInventory(inventory);

    }
    public void deleteInventory(int id){
        inventoryDao.deleteInventory(id);
    }




}

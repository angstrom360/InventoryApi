package com.trilogyed.Inventory.controller;

import com.sun.jersey.api.NotFoundException;
import com.trilogyed.Inventory.servicelayer.InventoryServiceLayer;
import com.trilogyed.Inventory.viewmodel.InventoryViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class InventoryController {

    /**----------------------------------- REST CONTROLLER ----------------------------------------------------------*/
    @Autowired
    InventoryServiceLayer service;

    @RequestMapping(value = "/inventory", method = RequestMethod.POST)
    public InventoryViewModel createInventorys(@RequestBody @Valid InventoryViewModel inventoryViewModel){
        return service.createInventory(inventoryViewModel);
    }

    @RequestMapping(value = "/inventory/{id}", method = RequestMethod.GET)
    public InventoryViewModel getInventorys(@PathVariable int id){
        InventoryViewModel inventoryViewModel = service.getInventory(id);
        if(inventoryViewModel == null)
            throw new NotFoundException("Inventory could not be found for id "+id);
        return inventoryViewModel;

    }

    @RequestMapping(value = "/inventory/all", method = RequestMethod.GET)
    public List<InventoryViewModel> getAllInventory(){
        return service.getAllInventory();
    }

    @RequestMapping(value = "/inventory/{id}", method = RequestMethod.PUT)
    public void updateInventorys(@RequestBody @Valid InventoryViewModel inventoryViewModel, @PathVariable int id) {
        if(inventoryViewModel.getInventoryId() == 0)
            inventoryViewModel.setInventoryId(id);
        if(id != inventoryViewModel.getInventoryId()) {

            throw new IllegalArgumentException("Inventory ID on path must match the ID in the Post Object.");
        }
        service.updateInventory(inventoryViewModel);
    }

    @RequestMapping(value = "/inventory/{id}", method = RequestMethod.DELETE)
    public void deletePost(@PathVariable int id) {
        service.deleteInventory(id);

    }

    /**----------------------------------------------------------------------------------------------------------------*/




}

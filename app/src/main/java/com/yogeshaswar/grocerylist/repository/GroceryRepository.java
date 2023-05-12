package com.yogeshaswar.grocerylist.repository;

import android.app.Application;

import com.yogeshaswar.grocerylist.database.AppDatabase;
import com.yogeshaswar.grocerylist.database.GroceryDAO;
import com.yogeshaswar.grocerylist.models.GroceryItem;

import java.util.ArrayList;
import java.util.List;

public class GroceryRepository {
    private Application application;
    private AppDatabase appDatabase;
    private GroceryDAO groceryDAO;
    private List<GroceryItem> groceryList = new ArrayList<>();

    public GroceryRepository(Application application) {
        this.application = application;
        appDatabase = AppDatabase.getINSTANCE(application);
        groceryDAO = appDatabase.groceryDAO();
    }

    public void addGroceryItem(GroceryItem groceryItem) {
        groceryDAO.addGroceryItem(groceryItem);
    }
    public void removeGroceryItem(GroceryItem groceryItem) {
        groceryDAO.removeGroceryItem(groceryItem);
    }
    public List<GroceryItem> getGroceryList() {
        groceryList = groceryDAO.getGroceryList();
        return groceryList;
    }

}

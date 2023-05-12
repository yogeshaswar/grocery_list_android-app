package com.yogeshaswar.grocerylist.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.yogeshaswar.grocerylist.models.GroceryItem;
import com.yogeshaswar.grocerylist.repository.GroceryRepository;

import java.util.ArrayList;
import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {
    private GroceryRepository groceryRepository;
    private List<GroceryItem> groceryList = new ArrayList<>();
    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        groceryRepository = new GroceryRepository(application);
    }

    public void addGroceryItem(GroceryItem groceryItem) {
        groceryRepository.addGroceryItem(groceryItem);
    }
    public void removeGroceryItem(GroceryItem groceryItem) {
        groceryRepository.removeGroceryItem(groceryItem);
    }
    public List<GroceryItem> getGroceryList() {
        groceryList = groceryRepository.getGroceryList();
        return groceryList;
    }

}

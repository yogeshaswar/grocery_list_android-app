package com.yogeshaswar.grocerylist.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.yogeshaswar.grocerylist.models.GroceryItem;

import java.util.List;

@Dao
public interface GroceryDAO {
    @Insert
    void addGroceryItem(GroceryItem groceryItem);
    @Delete
    void removeGroceryItem(GroceryItem groceryItem);
    @Query("SELECT * FROM groceryList")
    List<GroceryItem> getGroceryList();
}

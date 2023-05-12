package com.yogeshaswar.grocerylist.models;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "groceryList")
public class GroceryItem {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String groceryName;
    private String groceryQuantity;
    private boolean isBought = false;
    private boolean isPriority = false;

    public GroceryItem(int id, String groceryName, String groceryQuantity, boolean isBought, boolean isPriority) {
        this.id = id;
        this.groceryName = groceryName;
        this.groceryQuantity = groceryQuantity;
        this.isBought = isBought;
        this.isPriority = isPriority;

    }
    @Ignore
    public GroceryItem(String groceryName, String groceryQuantity) {
        this.groceryName = groceryName;
        this.groceryQuantity = groceryQuantity;
    }

    public int getId() {
        return id;
    }

    public String getGroceryName() {
        return groceryName;
    }

    public String getGroceryQuantity() {
        return groceryQuantity;
    }

    public boolean isBought() {
        return isBought;
    }

    public boolean isPriority() {
        return isPriority;
    }
}

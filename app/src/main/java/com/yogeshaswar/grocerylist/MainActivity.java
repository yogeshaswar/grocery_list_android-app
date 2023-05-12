package com.yogeshaswar.grocerylist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.yogeshaswar.grocerylist.models.GroceryItem;
import com.yogeshaswar.grocerylist.viewmodel.MainActivityViewModel;

public class MainActivity extends AppCompatActivity {
private MainActivityViewModel mainActivityViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initiateViewModel();
        initiateUI();
        addGroceryItem();
    }

    private void addGroceryItem() {
        GroceryItem groceryItem = new GroceryItem("Test","Test");
        mainActivityViewModel.addGroceryItem(groceryItem);
    }

    private void initiateUI() {
    }

    private void initiateViewModel() {
        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
    }
}
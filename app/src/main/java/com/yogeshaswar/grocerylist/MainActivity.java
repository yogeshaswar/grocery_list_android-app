package com.yogeshaswar.grocerylist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.yogeshaswar.grocerylist.adapters.GroceryAdapter;
import com.yogeshaswar.grocerylist.models.GroceryItem;
import com.yogeshaswar.grocerylist.viewmodel.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
private MainActivityViewModel mainActivityViewModel;
private Button btnAddGrocery;
private EditText etGroceryItem, etGroceryQuantity;
private RecyclerView rvGroceryList;
private List<GroceryItem> groceryList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initiateViewModel();
        initiateUI();
        btnClickHandler();
        loadRecyclerView(getGroceryList());
        // delete on swipe functionality
        deletegroceryItemOnSwipe();
    }

    private void deletegroceryItemOnSwipe() {
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                GroceryItem groceryItem = getGroceryList().get(viewHolder.getAdapterPosition());
                deleteGroceryItem(groceryItem);
                loadRecyclerView(getGroceryList());
                Toast.makeText(MainActivity.this, "Grocery Item Deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(rvGroceryList);
    }


    private void btnClickHandler() {
        btnAddGrocery.setOnClickListener((v) -> {
            addGroceryItem();
            loadRecyclerView(getGroceryList());
        });
    }

    private void addGroceryItem() {
        if((etGroceryItem.getText().toString().equals(""))) {
            return;
        }
        GroceryItem groceryItem = new GroceryItem(etGroceryItem.getText().toString(),etGroceryQuantity.getText().toString());
        mainActivityViewModel.addGroceryItem(groceryItem);
        etGroceryItem.setText("");
        etGroceryQuantity.setText("");
        Toast.makeText(this, "Added Successfully", Toast.LENGTH_SHORT).show();

    }

    private void initiateUI() {
        etGroceryItem = findViewById(R.id.etGroceryItem);
        etGroceryQuantity = findViewById(R.id.etGroceryQuantity);
        btnAddGrocery = findViewById(R.id.btnAdd);
        rvGroceryList = (RecyclerView) findViewById(R.id.rvGroceryList);
    }

    private void loadRecyclerView(List<GroceryItem> groceries) {
        rvGroceryList.setLayoutManager(new LinearLayoutManager(this));
        rvGroceryList.setAdapter(new GroceryAdapter(groceries));
    }

    private List<GroceryItem> getGroceryList() {
        groceryList = mainActivityViewModel.getGroceryList();
        return groceryList;
    }

    private void initiateViewModel() {
        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
    }

    private void deleteGroceryItem(GroceryItem groceryItem) {
        mainActivityViewModel.removeGroceryItem(groceryItem);
    }
}
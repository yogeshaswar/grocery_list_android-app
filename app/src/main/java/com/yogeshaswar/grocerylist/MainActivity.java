package com.yogeshaswar.grocerylist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.yogeshaswar.grocerylist.adapters.GroceryAdapter;
import com.yogeshaswar.grocerylist.models.GroceryItem;
import com.yogeshaswar.grocerylist.viewmodel.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

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
        deleteGroceryItemOnSwipe();
    }

    private void deleteGroceryItemOnSwipe() {
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                // Set the message show for the Alert time
                builder.setMessage("Do you want to delete this item?");

                // Set Alert Title
                builder.setTitle("Alert");

                // Set Cancelable false for when the user clicks on the outside the Dialog Box then it will remain show
                builder.setCancelable(false);

                // Set the positive button with yes name Lambda OnClickListener method is use of DialogInterface interface.
                builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
                    // When the user click yes button then app will close
                    GroceryItem groceryItem = getGroceryList().get(viewHolder.getAdapterPosition());
                    deleteGroceryItem(groceryItem);
                    loadRecyclerView(getGroceryList());
                    Toast.makeText(MainActivity.this, "Grocery Item Deleted", Toast.LENGTH_SHORT).show();
                });

                // Set the Negative button with No name Lambda OnClickListener method is use of DialogInterface interface.
                builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
                    // If user click no then dialog box is canceled.
                    dialog.cancel();
                    loadRecyclerView(getGroceryList());
                });

                // Create the Alert dialog
                AlertDialog alertDialog = builder.create();
                // Show the Alert Dialog box
                alertDialog.show();
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

    // Declare the onBackPressed method when the back button is pressed this method will call
    @Override
    public void onBackPressed() {
        // Create the object of AlertDialog Builder class
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        // Set the message show for the Alert time
        builder.setMessage("Do you want to exit ?");

        // Set Alert Title
        builder.setTitle("Alert !");

        // Set Cancelable false for when the user clicks on the outside the Dialog Box then it will remain show
        builder.setCancelable(false);

        // Set the positive button with yes name Lambda OnClickListener method is use of DialogInterface interface.
        builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
            // When the user click yes button then app will close
            finish();
        });

        // Set the Negative button with No name Lambda OnClickListener method is use of DialogInterface interface.
        builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
            // If user click no then dialog box is canceled.
            dialog.cancel();
        });

        // Create the Alert dialog
        AlertDialog alertDialog = builder.create();
        // Show the Alert Dialog box
        alertDialog.show();



    }}
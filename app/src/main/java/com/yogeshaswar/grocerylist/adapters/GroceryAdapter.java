package com.yogeshaswar.grocerylist.adapters;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yogeshaswar.grocerylist.R;
import com.yogeshaswar.grocerylist.models.GroceryItem;

import java.util.List;

public class GroceryAdapter extends RecyclerView.Adapter<GroceryAdapter.GroceryViewHolder> {
    public List<GroceryItem> groceryItemList;

    public GroceryAdapter(List<GroceryItem> groceryItemList) {
        this.groceryItemList = groceryItemList;
    }

    @NonNull
    @Override
    public GroceryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.grocery_card, parent, false);
        return new GroceryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GroceryViewHolder holder, int position) {
        holder.tvGroceryItem.setText(groceryItemList.get(position).getGroceryName());
        holder.tvGroceryQuantity.setText(groceryItemList.get(position).getGroceryQuantity());
        if(holder.cbIsBought.isChecked()) {
            holder.tvGroceryItem.setPaintFlags(holder.tvGroceryItem.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }
    }

    @Override
    public int getItemCount() {
        return groceryItemList.size();
    }


    public class GroceryViewHolder extends RecyclerView.ViewHolder {
        TextView tvGroceryItem, tvGroceryQuantity;
        CheckBox cbIsBought;
        public GroceryViewHolder(@NonNull View itemView) {
            super(itemView);
            tvGroceryItem = itemView.findViewById(R.id.tvGroceryItem);
            tvGroceryQuantity = itemView.findViewById(R.id.tvGroceryQuantity);
            cbIsBought = itemView.findViewById(R.id.cbIsBought);
        }
    }

}

package com.yogeshaswar.grocerylist.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.yogeshaswar.grocerylist.models.GroceryItem;

@Database(entities = {GroceryItem.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "grocery.db";

    public abstract GroceryDAO groceryDAO();

    public static AppDatabase INSTANCE;

    public static synchronized AppDatabase getINSTANCE(Context context) {
        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }

}

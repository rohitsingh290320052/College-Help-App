package com.example.itsadeal;

import androidx.room.RoomDatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;

@Database(entities = Expense.class,exportSchema = false,version = 1)

public abstract class databasehelper extends RoomDatabase {
    private static final String db_name="rental_service";
    private static databasehelper instance;

    public static synchronized databasehelper getdb(Context context){
        if(instance==null){
            instance= Room.databaseBuilder(context,databasehelper.class,db_name)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
    public abstract Expense_Dao expense_dao();
}


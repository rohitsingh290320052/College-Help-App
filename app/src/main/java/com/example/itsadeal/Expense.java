package com.example.itsadeal;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName ="product_supply")


public class Expense {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "product")
    private String product;

    @ColumnInfo(name = "time")
    private String time;

    @ColumnInfo(name="roll")
    private String roll;


    Expense(int id,String product,String time,String roll){
        this.id=id;
        this.product=product;
        this.time=time;
        this.roll=roll;
    }
    public String getTime(){return time;}
    public void setTime(String time){
        this.time=time;
    }


    public String getRoll(){ return roll;}
    public void setRoll(String roll){
        this.roll=roll;
    }

    @Ignore
    Expense(String product, String time,String roll){
        this.product=product;
        this.time=time;
        this.roll=roll;
    }


    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

package com.alien.xenocorez.plarnpoung;

/**
 * Created by XenoCoreZ on 29/4/2558.
 */
public class foodItem {
    public String FoodName;
    public int FoodCal;

    public foodItem(){

    }

    public foodItem(String fN, int fC){
        FoodName = fN;
        FoodCal = fC;
    }

    public String getFoodName() {
        return FoodName;
    }

    public void setFoodName(String foodName) {
        FoodName = foodName;
    }

    public int getFoodCal() {
        return FoodCal;
    }

    public void setFoodCal(int foodCal) {
        FoodCal = foodCal;
    }



}

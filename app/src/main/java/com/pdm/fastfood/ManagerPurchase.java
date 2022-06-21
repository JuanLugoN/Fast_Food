package com.pdm.fastfood;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ManagerPurchase {
    private final String CART_PURCHASES = "CART PURCHASES";
    private final String SUCCESSFUL_PURCHASES = "SUCCESSFUL PURCHASES";
    private final String ID_SP = "ID SP";
    private final String ID_CP = "ID CP";
    private int idSP;
    private int idCP;
    private List<Purchase> cartPurchases;
    private List<Purchase> successfulPurchases;

    public ManagerPurchase(SharedPreferences sp) {
        cartPurchases = new ArrayList<Purchase>();
        successfulPurchases = new ArrayList<Purchase>();
        TypeToken<ArrayList<Purchase>> list = new TypeToken<ArrayList<Purchase>>(){};
        Type type = list.getType();
        cartPurchases = new Gson().fromJson(sp.getString(CART_PURCHASES, ""), type);
        successfulPurchases = new Gson().fromJson(sp.getString(SUCCESSFUL_PURCHASES, ""), type);
        idSP = sp.getInt(ID_SP,-1);
        idCP = sp.getInt(ID_CP, -1);
    }

    public List<Purchase> getCartPurchases() {
        return cartPurchases;
    }

    public List<Purchase> getSuccessfulPurchases() {
        return successfulPurchases;
    }

    public int getIdSP() {
        return ++idSP;
    }

    public int getIdCP() {
        return ++idCP;
    }

    public void addCartPurchase(Purchase purchase) {cartPurchases.add(purchase);}

    public void removeCartPurchase(Purchase purchase) {cartPurchases.remove(purchase);}

    public void addSuccessfulPurchase(Purchase purchase) {successfulPurchases.add(purchase);};
}
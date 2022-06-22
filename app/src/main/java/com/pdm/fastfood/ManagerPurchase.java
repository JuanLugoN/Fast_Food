package com.pdm.fastfood;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ManagerPurchase {
    private final String CART_PURCHASES = "CART PURCHASES";
    private final String SUCCESSFUL_PURCHASES = "SUCCESSFUL PURCHASES";
    private final String ID = "ID";
    private int id;
    private List<Purchase> cartPurchases;
    private List<Purchase> successfulPurchases;
    private SharedPreferences sp;

    public ManagerPurchase(SharedPreferences sp) {
        if (sp == null)
            throw new IllegalArgumentException("SharedPreferences is null");
        this.sp = sp;
        Type type = new TypeToken<ArrayList<Purchase>>(){}.getType();
        String list = sp.getString(CART_PURCHASES, "");
        cartPurchases = list.isEmpty()? new ArrayList<Purchase>() : new Gson().fromJson(list, type);
        list = sp.getString(SUCCESSFUL_PURCHASES, "");
        successfulPurchases = list.isEmpty()? new ArrayList<Purchase>() : new Gson().fromJson(list, type);
    }

    public List<Purchase> getCartPurchases() {return cartPurchases;}

    public List<Purchase> getSuccessfulPurchases() {return successfulPurchases;}

    public int getId() {return ++id;}

    public void addCartPurchase(Purchase purchase) {
        cartPurchases.add(purchase);
        String json = new Gson().toJson(cartPurchases);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(CART_PURCHASES, json);
        editor.apply();
    }

    public void removeCartPurchase(Purchase purchase) {
        cartPurchases.remove(purchase);
        String json = new Gson().toJson(cartPurchases);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(CART_PURCHASES, json);
        editor.apply();
    }

    public void buyAll() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String stringDate = formatter.format(date);
        for(Purchase purchase: cartPurchases) {
            purchase.setDate(stringDate);
            successfulPurchases.add(purchase);
        }
        cartPurchases.clear();
        Gson gson = new Gson();
        String json = gson.toJson(successfulPurchases);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(SUCCESSFUL_PURCHASES, json);
        json = gson.toJson(cartPurchases);
        editor.putString(CART_PURCHASES, json);
        editor.apply();
    }

    public void addSuccessfulPurchase(Purchase purchase) {
        successfulPurchases.add(purchase);
        String json = new Gson().toJson(successfulPurchases);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(SUCCESSFUL_PURCHASES, json);
        editor.apply();
    };
}
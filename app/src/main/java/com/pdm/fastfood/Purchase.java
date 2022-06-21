package com.pdm.fastfood;

import java.util.Objects;

public class Purchase {
    private int id;
    private String pizza;
    private int quantity;
    private String date;

    public Purchase(int id, String pizza, int quantity, String date) {
        this.id = id;
        this.pizza = pizza;
        this.quantity = quantity;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPizza() {
        return pizza;
    }

    public void setPizza(String pizza) {
        this.pizza = pizza;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Purchase purchase = (Purchase) o;
        return id == purchase.id && quantity == purchase.quantity && Objects.equals(pizza, purchase.pizza) && Objects.equals(date, purchase.date);
    }
}

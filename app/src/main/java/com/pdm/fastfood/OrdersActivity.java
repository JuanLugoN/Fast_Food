package com.pdm.fastfood;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OrdersActivity extends AppCompatActivity implements View.OnClickListener{

    private final String EXTRA_PIZZA = "PIZZA";
    private Pizza pizza;
    private Button btnAddCart;
    private Button btnBuy;
    private Button btnDecrease;
    private Button btnIncrease;
    private TextView txtPrice;
    private TextView txtQuantity;
    private int quantity = 1;
    private SharedPreferences sp;
    private ManagerPurchase mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);
        Intent intent = getIntent();
        pizza = intent.getParcelableExtra(EXTRA_PIZZA);
        ImageView imageView = (ImageView) findViewById(R.id.img_pizza);
        TextView textView = (TextView) findViewById(R.id.txt_pizza_type);
        textView.setText(pizza.getType());
        imageView.setImageResource(pizza.getSrcImg());
        btnAddCart = (Button) findViewById(R.id.btn_add_cart);
        btnAddCart.setOnClickListener(this);
        btnBuy = (Button) findViewById(R.id.btn_buy);
        btnBuy.setOnClickListener(this);
        btnDecrease = (Button) findViewById(R.id.btn_decrease);
        btnDecrease.setOnClickListener(this);
        btnIncrease = (Button) findViewById(R.id.btn_increase);
        btnIncrease.setOnClickListener(this);
        txtPrice = (TextView) findViewById(R.id.txt_total_price);
        txtQuantity = (TextView) findViewById(R.id.txt_quantity);
        txtQuantity.setText(String.valueOf(quantity));
        txtPrice.setText("$ " + String.valueOf(pizza.getPrice()));
        btnDecrease.setEnabled(false);
        sp = getSharedPreferences("SP", MODE_PRIVATE);
        mp = new ManagerPurchase(sp);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        Purchase purchase;
        switch (id) {
            case R.id.btn_add_cart:
                purchase = new Purchase(mp.getId(), pizza, quantity);
                mp.addCartPurchase(purchase);
                Toast.makeText(this, getString(R.string.toast_add_cart), Toast.LENGTH_SHORT).show();
                onBackPressed();
                break;
            case R.id.btn_buy:
                Toast.makeText(this, getString(R.string.toast_buy), Toast.LENGTH_SHORT).show();
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date date = new Date();
                String stringDate = formatter.format(date);
                purchase = new Purchase(mp.getId(), pizza, quantity, stringDate);
                mp.addSuccessfulPurchase(purchase);
                onBackPressed();
                break;
            case R.id.btn_decrease:
                quantity--;
                txtQuantity.setText(String.valueOf(quantity));
                txtPrice.setText("$ " + String.valueOf(quantity* pizza.getPrice()));
                if (quantity == 1)
                    btnDecrease.setEnabled(false);
                break;
            case R.id.btn_increase:
                quantity++;
                txtQuantity.setText(String.valueOf(quantity));
                txtPrice.setText(String.format("$ %,d", quantity* pizza.getPrice()));
                if (quantity > 1)
                    btnDecrease.setEnabled(true);
                break;
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
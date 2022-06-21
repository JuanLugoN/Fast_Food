package com.pdm.fastfood;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btn_add_cart:

                onBackPressed();
                break;
            case R.id.btn_buy:
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
}
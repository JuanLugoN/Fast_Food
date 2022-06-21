package com.pdm.fastfood;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PizzaAdapter extends RecyclerView.Adapter<PizzaAdapter.ViewHolder> {
    private final String EXTRA_PIZZA = "PIZZA";
    private List<Pizza> pizzaList;
    private Context context;

    public PizzaAdapter(List<Pizza> pizzaList, Context context) {
        this.pizzaList = pizzaList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_pizza,viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.typePizza.setText(pizzaList.get(i).getType());
        viewHolder.imgPizza.setImageResource(pizzaList.get(i).getSrcImg());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, OrdersActivity.class);
                intent.putExtra(EXTRA_PIZZA, pizzaList.get(i));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pizzaList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgPizza;
        private TextView typePizza;
        private final String EXTRA_SRC_IMG = "SRC_IMG";
        private  final  String EXTRA_TYPE_PIZZA = "PIZZA_TYPE";
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPizza = itemView.findViewById(R.id.img_pizza);
            typePizza = itemView.findViewById(R.id.txt_pizza_type);
        }
    }
}
package com.pdm.fastfood;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder>{
    private List<Purchase> list;
    private Context context;
    private ManagerPurchase mp;

    public CartAdapter(ManagerPurchase mp, Context context) {
        this.list = mp.getCartPurchases();
        this.context = context;
        this.mp = mp;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item,parent, false);
        return new ViewHolder(view).linkAdapter(this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int quantity = list.get(position).getQuantity();
        holder.txt_quantity.setText(String.valueOf(quantity));
        holder.txt_pizza.setText(list.get(position).getPizza().getType());
        holder.txt_price.setText(String.format("$ %,d", quantity*list.get(position).getPizza().getPrice()));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private Button btn_remove;
        private TextView txt_quantity;
        private TextView txt_pizza;
        private TextView txt_price;
        private CartAdapter adapter;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            btn_remove = (Button) itemView.findViewById(R.id.btn_remove);
            txt_quantity = (TextView) itemView.findViewById(R.id.txt_quantity);
            txt_pizza = (TextView) itemView.findViewById(R.id.txt_pizza);
            txt_price = (TextView) itemView.findViewById(R.id.txt_price);
            btn_remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    adapter.mp.removeCartPurchase(adapter.list.get(getAdapterPosition()));
                    adapter.notifyItemRemoved(getAdapterPosition());
                    if (adapter.getItemCount() == 0)
                        adapter.notifyDataSetChanged();
                }
            });
        }

        public ViewHolder linkAdapter(CartAdapter adapter) {
            this.adapter = adapter;
            return this;
        }
    }
}

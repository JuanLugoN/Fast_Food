package com.pdm.fastfood;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PurchaseAdapter extends RecyclerView.Adapter<PurchaseAdapter.ViewHolder> {
    private List<Purchase> list;
    private Context context;
    private ManagerPurchase mp;

    public PurchaseAdapter(ManagerPurchase mp, Context context) {
        this.context = context;
        this.list = mp.getSuccessfulPurchases();
        this.mp = mp;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_item,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int quantity = list.get(position).getQuantity();
        holder.txtQuantity.setText(String.valueOf(quantity));
        holder.txtPizza.setText(list.get(position).getPizza().getType());
        holder.txtPrice.setText(String.format("$ %,d", quantity*list.get(position).getPizza().getPrice()));
        holder.txtDate.setText(list.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtQuantity;
        private TextView txtPizza;
        private TextView txtPrice;
        private TextView txtDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtQuantity = (TextView) itemView.findViewById(R.id.txt_quantity);
            txtPizza = (TextView) itemView.findViewById(R.id.txt_pizza);
            txtPrice = (TextView) itemView.findViewById(R.id.txt_price);
            txtDate = (TextView) itemView.findViewById(R.id.txt_date);
        }
    }
}

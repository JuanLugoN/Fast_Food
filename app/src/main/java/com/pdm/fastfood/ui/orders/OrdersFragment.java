package com.pdm.fastfood.ui.orders;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pdm.fastfood.Pizza;
import com.pdm.fastfood.PizzaAdapter;
import com.pdm.fastfood.R;
import com.pdm.fastfood.databinding.FragmentOrdersBinding;

import java.util.ArrayList;
import java.util.List;

public class OrdersFragment extends Fragment {

    private RecyclerView recyclerView;
    private PizzaAdapter pizzaAdapter;
    private FragmentOrdersBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        OrdersViewModel ordersViewModel =
                new ViewModelProvider(this).get(OrdersViewModel.class);
        binding = FragmentOrdersBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        recyclerView = (RecyclerView) root.findViewById(R.id.recyclerView);
        initializeCards();
        return root;
    }

    private void initializeCards() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        List<Pizza> pizzaList = new ArrayList<Pizza>(5);
        pizzaList.add(new Pizza(R.string.pizza_pepperoni, R.drawable.pizza_pepperoni, 150));
        pizzaList.add(new Pizza(R.string.pizza_hawaiian, R.drawable.pizza_hawaiian, 200));
        pizzaList.add(new Pizza(R.string.pizza_mushroom, R.drawable.pizza_mushroom, 175));
        pizzaList.add(new Pizza(R.string.pizza_four_cheese, R.drawable.pizza_four_cheeses, 230));
        pizzaList.add(new Pizza(R.string.pizza_neapolitan, R.drawable.pizza_neapolitan, 200));
        pizzaAdapter = new PizzaAdapter(pizzaList, getContext());
        recyclerView.setAdapter(pizzaAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
package com.pdm.fastfood.ui.history;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pdm.fastfood.ManagerPurchase;
import com.pdm.fastfood.PurchaseAdapter;
import com.pdm.fastfood.R;
import com.pdm.fastfood.databinding.FragmentHistoryBinding;

public class HistoryFragment extends Fragment {

    private RecyclerView recyclerView;
    private PurchaseAdapter purchaseAdapter;
    private ManagerPurchase mp;
    private FragmentHistoryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HistoryViewModel historyViewModel =
                new ViewModelProvider(this).get(HistoryViewModel.class);

        binding = FragmentHistoryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        recyclerView = (RecyclerView) root.findViewById(R.id.recyclerViewHistory);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        SharedPreferences sp = this.getActivity().getSharedPreferences("SP", Context.MODE_PRIVATE);
        mp = new ManagerPurchase(sp);
        purchaseAdapter = new PurchaseAdapter(mp, getContext());
        recyclerView.setAdapter(purchaseAdapter);
        return root;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
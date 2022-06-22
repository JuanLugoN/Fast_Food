package com.pdm.fastfood.ui.cart;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.pdm.fastfood.CartAdapter;
import com.pdm.fastfood.ManagerPurchase;
import com.pdm.fastfood.R;
import com.pdm.fastfood.databinding.FragmentCartBinding;

public class CartFragment extends Fragment {

    private Button btn_buy_all;
    private RecyclerView recyclerView;
    private CartAdapter cartAdapter;
    private FragmentCartBinding binding;
    private ManagerPurchase mp;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CartViewModel cartViewModel =
                new ViewModelProvider(this).get(CartViewModel.class);

        binding = FragmentCartBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        recyclerView = (RecyclerView) root.findViewById(R.id.recyclerViewCart);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        SharedPreferences sp = this.getActivity().getSharedPreferences("SP", Context.MODE_PRIVATE);
        mp = new ManagerPurchase(sp);
        cartAdapter = new CartAdapter(mp, getContext());
        recyclerView.setAdapter(cartAdapter);
        btn_buy_all = root.findViewById(R.id.btn_buy_all);
        if (cartAdapter.getItemCount() == 0) btn_buy_all.setEnabled(false);
        cartAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                btn_buy_all.setEnabled(false);
            }
        });
        linkAlertDialog();
        return root;
    }

    private void linkAlertDialog (){
        MaterialAlertDialogBuilder builder= new MaterialAlertDialogBuilder(getActivity(), R.style.AlertDialogTheme);
        builder.setTitle(R.string.ask_buy_all);
        builder.setMessage(R.string.buy_all_description);
        builder.setNeutralButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.setPositiveButton(R.string.accept, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                int count = cartAdapter.getItemCount();
                mp.buyAll();
                cartAdapter.notifyDataSetChanged();
                btn_buy_all.setEnabled(false);
            }
        });
        btn_buy_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder.show();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
package com.projet.projectfood;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.ViewHolder> {
    ArrayList<Categorie> categories;
    private ManagementCart managementCart;
    ChangeNumberItemsListener changeNumberItemsListener;

    public CartListAdapter(ArrayList<Categorie> categories, ManagementCart managementCart, ChangeNumberItemsListener changeNumberItemsListener) {
        this.categories = categories;
        this.managementCart = managementCart;
        this.changeNumberItemsListener = changeNumberItemsListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart, parent, false);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(categories.get(position).getName());
        holder.feeEachItem.setText("$" + categories.get(position).getPrice());
        holder.totalEachItem.setText("$" + Math.round((categories.get(position).getNumberInCart() * categories.get(position).getPrice())));
        holder.num.setText(String.valueOf(categories.get(position).getNumberInCart()));
        Picasso.get().load(categories.get(position).getImage()).into(holder.pic);



        holder.plusItem.setOnClickListener(v -> managementCart.plusNumberFood(categories, position, () -> {
            notifyDataSetChanged();
            changeNumberItemsListener.changed();
        }));

        holder.minusItem.setOnClickListener(v -> managementCart.minusNumberFood(categories, position, () -> {
            notifyDataSetChanged();
            changeNumberItemsListener.changed();
        }));

    }


    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, feeEachItem;
        ImageView pic, plusItem, minusItem;
        TextView totalEachItem, num;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titleTxt);
            pic = itemView.findViewById(R.id.pic);
            feeEachItem = itemView.findViewById(R.id.feeEachItem);
            totalEachItem = itemView.findViewById(R.id.totalEachItem);
            plusItem = itemView.findViewById(R.id.plusCardBtn);
            minusItem = itemView.findViewById(R.id.minusCardBtn);
            num = itemView.findViewById(R.id.numberItemTxt);

        }
    }
}

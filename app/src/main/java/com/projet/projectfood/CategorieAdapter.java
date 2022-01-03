package com.projet.projectfood;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class CategorieAdapter extends RecyclerView.Adapter<CategorieAdapter.AdapterHolder>  {

    private Context context;
    private ArrayList<Categorie> categorieList;

    public CategorieAdapter(Context context, ArrayList<Categorie> categorieList) {
        this.context = context;
        this.categorieList = categorieList;
    }


    @NonNull
    @Override
    public AdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.viewholder_recommended,parent,false);

        return new AdapterHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterHolder holder, int position) {
        Categorie categorie = categorieList.get(position);

        holder.title.setText(categorie.getName());
        holder.fee.setText(String.valueOf(categorieList.get(position).getPrice()));
        Picasso.get().load(categorie.getImage()).into(holder.pic); // picasso librarie put the url link from the categorie into our image view
        holder.categorieItem.setOnClickListener(v -> {
            Intent i = new Intent(context,DescriptionActivity.class);
            i.putExtra("categorie",categorie);
            context.startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        return categorieList.size();
    }

    public void filterlist(ArrayList<Categorie>filteredList){
        categorieList =filteredList;

    }

    public class AdapterHolder extends RecyclerView.ViewHolder {
        TextView title, fee;
        ImageView pic;
        ImageView addBtn;
        ConstraintLayout categorieItem;


        public AdapterHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            pic = itemView.findViewById(R.id.pic);
            fee = itemView.findViewById(R.id.fee);
            addBtn = itemView.findViewById(R.id.addBtn);
            categorieItem = itemView.findViewById(R.id.categoriieItem);
        }
    }
}

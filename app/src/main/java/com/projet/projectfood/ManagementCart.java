package com.projet.projectfood;

import android.content.Context;
import android.widget.Toast;
import java.util.ArrayList;

public class ManagementCart {
    private Context context;
    private TinyDB tinyDB;

    public ManagementCart(Context context) {
        this.context = context;
        this.tinyDB = new TinyDB(context);
    }

    public void insertFood(Categorie item) {
        ArrayList<Categorie> categories = getListCart();
        boolean existAlready = false;
        int n = 0;
        for (int i = 0; i < categories.size(); i++) {
            if (categories.get(i).getName().equals(item.getName())) {
                existAlready = true;
                n = i;
                break;
            }
        }

        if (existAlready) {
            categories.get(n).setNumberInCart(item.getNumberInCart());
        } else {
            categories.add(item);
        }

        tinyDB.putListObject("CardList", categories);
        Toast.makeText(context, "Added to your Cart", Toast.LENGTH_SHORT).show();
    }

    public ArrayList<Categorie> getListCart() {
        return tinyDB.getListObject("CardList");
    }

    public void minusNumberFood(ArrayList<Categorie> listfood, int position, ChangeNumberItemsListener changeNumberItemsListener) {
        if (listfood.get(position).getNumberInCart() == 1) {
            listfood.remove(position);
        } else {
            listfood.get(position).setNumberInCart(listfood.get(position).getNumberInCart() - 1);
        }
        tinyDB.putListObject("CardList", listfood);
        changeNumberItemsListener.changed();
    }
    public void clearData() {
        tinyDB.clear();
    }

    public void plusNumberFood(ArrayList<Categorie> listfood, int position, ChangeNumberItemsListener changeNumberItemsListener) {
        listfood.get(position).setNumberInCart(listfood.get(position).getNumberInCart() + 1);
        tinyDB.putListObject("CardList", listfood);
        changeNumberItemsListener.changed();
    }

    public Double getTotalFee() {
        ArrayList<Categorie> listfood2 = getListCart();
        double fee = 0;
        for (int i = 0; i < listfood2.size(); i++) {
            fee = fee + (listfood2.get(i).getPrice() * listfood2.get(i).getNumberInCart());
        }
        return fee;
    }
}

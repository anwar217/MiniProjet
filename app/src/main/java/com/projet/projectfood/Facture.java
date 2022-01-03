package com.projet.projectfood;

import java.util.List;

public class Facture {
    private String id;
    private List<CategorieByFacture> categorieByFacture;
    private double tax;
    private double totalPrice;
    private double totalPriceHt;

    public Facture() {
    }

    public Facture(String id, List<CategorieByFacture> categorieByFacture, double tax, double totalPrice, double totalPriceHt) {
        this.id = id;
        this.categorieByFacture = categorieByFacture;
        this.tax = tax;
        this.totalPrice = totalPrice;
        this.totalPriceHt = totalPriceHt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<CategorieByFacture> getCategorieByFacture() {
        return categorieByFacture;
    }

    public void setCategorieByFacture(List<CategorieByFacture> categorieByFacture) {
        this.categorieByFacture = categorieByFacture;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getTotalPriceHt() {
        return totalPriceHt;
    }

    public void setTotalPriceHt(double totalPriceHt) {
        this.totalPriceHt = totalPriceHt;
    }
}

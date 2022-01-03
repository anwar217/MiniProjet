package com.projet.projectfood;

public class CategorieByFacture {
    private String name;
    private int numberOfItems;

    public CategorieByFacture(String name, int numberOfItems) {
        this.name = name;
        this.numberOfItems = numberOfItems;
    }

    public CategorieByFacture() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfItems() {
        return numberOfItems;
    }

    public void setNumberOfItems(int numberOfItems) {
        this.numberOfItems = numberOfItems;
    }
}

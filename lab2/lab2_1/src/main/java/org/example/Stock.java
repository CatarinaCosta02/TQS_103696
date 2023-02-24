package org.example;

public class Stock {
    private String label;
    private int quantity;

    public Stock(String lable, int quantity) {
        this.label = lable;
        this.quantity = quantity;
    }


    public int getQuantity() {
        return quantity;
    }

    public String getLabel() {
        return label;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}

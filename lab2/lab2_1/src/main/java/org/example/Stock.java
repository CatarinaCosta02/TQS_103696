package org.example;

public class Stock {
    private String label;
    private Integer quantity;

    public Stock(String lable, Integer quantity) {
        this.label = lable;
        this.quantity = quantity;
    }


    public Integer getQuantity() {

        return quantity;
    }

    public String getLabel() {
        return label;
    }

    public void setQuantity(Integer quantity) {

        this.quantity = quantity;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}

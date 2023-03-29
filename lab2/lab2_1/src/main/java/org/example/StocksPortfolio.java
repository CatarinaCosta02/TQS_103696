package org.example;

import java.util.ArrayList;

public class StocksPortfolio {



    private ArrayList<Stock> stocks = new ArrayList<>();
    private IStockmarketService stockmarket;
    
    public StocksPortfolio(IStockmarketService stockmarket) {

        this.stockmarket = stockmarket;
    }

    public void addStock(Stock stock) {
        this.stocks.add(stock);
    }

    public IStockmarketService getStockmarket() {
        return stockmarket;
    }

    public void setStockmarket(IStockmarketService stockmarket) {
        this.stockmarket = stockmarket;
    }

    public double getTotalValue() {
        double total = 0;
        try{
        for (Stock stock : this.stocks) {
            total += stock.getQuantity() * this.stockmarket.lookUpPrice(stock.getLabel());
        }
        return total;
    }catch (Exception e){
            return 0;
        }
    }

    public void getPrice(String anyString) {
        this.stockmarket.lookUpPrice(anyString);
    }
}

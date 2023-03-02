package org.example;

import java.util.List;

public class StocksPortfolio {

    private List<Stock> stocks;
    private IStockmarketService stockmarket;

    public StocksPortfolio(IStockmarketService stockmarket) {

        this.stockmarket = stockmarket;
    }

    public void addStock(Stock stock) {
        stocks.add(stock);
    }

    public IStockmarketService getStockmarket() {
        return stockmarket;
    }

    public void setStockmarket(IStockmarketService stockmarket) {
        this.stockmarket = stockmarket;
    }

    public double getTotalValue() {
        double total = 0.0;
        for (Stock stock : stocks) {
            total += stock.getQuantity() * stockmarket.lookUpPrice(stock.getLabel());
        }
        return total;
    }
}

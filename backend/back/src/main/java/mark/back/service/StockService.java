package mark.back.service;

import mark.back.entity.Stock;

public interface StockService {
    //create stock
    Stock saveStock(Stock stock);

    //get stock
    Stock getStock(Long id);
    Stock getStock(String symbol);

    //check if stock exists in db
    boolean stockExists(String symbol);

}
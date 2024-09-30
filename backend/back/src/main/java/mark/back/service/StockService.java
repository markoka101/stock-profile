package mark.back.service;

import mark.back.entity.Stock;

import java.util.Date;
import java.util.List;

public interface StockService {
    //create stock
    Stock saveStock(Stock stock);

    //get stock
    Stock getStock(Long id);
    Stock getStock(String symbol);
    List<Stock> getAllStocks();

    //check if stock exists in db
    boolean stockExists(String symbol);

    //update stock value
    Stock updateValue(Long id, float value, Date date);

}
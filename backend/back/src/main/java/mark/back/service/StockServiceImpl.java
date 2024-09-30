package mark.back.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import mark.back.entity.Stock;
import mark.back.repository.StockRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StockServiceImpl  implements StockService {
    StockRepository stockRepository;

    //add stock to db
    @Override
    public Stock saveStock(Stock stock) {
        return stockRepository.save(stock);
    }

    //get stock by id
    @Override
    public Stock getStock(Long id) {
        return stockRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    //get stock by symbol
    @Override
    public Stock getStock(String symbol) {
        return stockRepository.findBySymbol(symbol).orElseThrow(EntityNotFoundException::new);
    }

    //get all stocks in table
    @Override
    public List<Stock> getAllStocks() {
        return stockRepository.findAll();
    }

    //check if stock exists
    @Override
    public boolean stockExists(String symbol) {
        return stockRepository.findBySymbol(symbol).isPresent();
    }

    //update stock value and date
    @Override
    public Stock updateValue(Long id, float value, Date date) {
        Stock stock = stockRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        stock.setValue(value);
        stock.setDate(date);
        return stockRepository.save(stock);
    }
}
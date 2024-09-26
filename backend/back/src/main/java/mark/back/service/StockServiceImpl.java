package mark.back.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import mark.back.entity.Stock;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StockServiceImpl  implements StockService {

    @Override
    public Stock saveStock(Stock stock) {
        return null;
    }

    @Override
    public Stock getStock(Long id) {
        return null;
    }

    @Override
    public Stock getStock(String symbol) {
        return null;
    }

    @Override
    public List<Stock> getAllStocks() {
        return null;
    }

    @Override
    public boolean stockExists(String symbol) {
        return false;
    }

    @Override
    public boolean updateValue(Long id, float value, Date date) {
        return false;
    }
}
package mark.back.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import mark.back.entity.Stock;
import mark.back.entity.User;
import mark.back.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
    UserRepository userRepository;
    StockService stockService;
    BCryptPasswordEncoder bCryptPasswordEncoder;

    //get user by id
    @Override
    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    //get user by username
    @Override
    public User getUser(String username) {
        return userRepository.findByUsername(username).orElseThrow(EntityNotFoundException::new);
    }

    //save user to db
    @Override
    public User saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    //save stock to user's saved stock
    @Override
    public boolean saveStock(Long userId, Long stockId) {
        //get user
        User user = getUser(userId);
        //get stock
        Stock stock = stockService.getStock(stockId);

        //add to set and save user
        user.getStocksSaved().add(stock);
        user = userRepository.save(user);

        return user.getStocksSaved().contains(stock);
    }

    //remove stock from saved stock
    @Override
    public boolean removeSaved(Long userId, Long stockId) {
        //get user
        User user = getUser(userId);
        //get stock
        Stock stock = stockService.getStock(stockId);

        //remove stock from set and save user
        user.getStocksSaved().remove(stock);
        user = userRepository.save(user);

        return !user.getStocksSaved().contains(stock);
    }
}
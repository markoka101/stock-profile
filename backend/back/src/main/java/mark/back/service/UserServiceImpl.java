package mark.back.service;

import lombok.AllArgsConstructor;
import mark.back.entity.User;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
    @Override
    public User getUser(Long id) {
        return null;
    }

    @Override
    public User getUser(String username) {
        return null;
    }

    @Override
    public boolean saveUser(User user) {
        return false;
    }

    @Override
    public boolean saveStock(Long userId, Long stockId) {
        return false;
    }

    @Override
    public boolean removeSaved(Long userId, Long stockId) {
        return false;
    }
}
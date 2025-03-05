package mark.back.service;

import mark.back.entity.User;

public interface UserService {
    //accessing user
    User getUser(Long id);

    User getUser(String username);

    User saveUser(User user);

    //check if user exists
    boolean userExists(Long id);

    boolean userExists(String username);

    //save stock
    boolean saveStock(Long userId, Long stockId);

    //remove saved stock
    boolean removeSaved(Long userId, Long stockId);
}
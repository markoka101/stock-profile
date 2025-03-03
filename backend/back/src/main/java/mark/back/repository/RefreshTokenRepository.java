package mark.back.repository;

import mark.back.entity.RefreshToken;
import mark.back.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String> {
    Optional<RefreshToken> findByUser(User user);

    //@Modifying
    int deleteByUser(User user);
}
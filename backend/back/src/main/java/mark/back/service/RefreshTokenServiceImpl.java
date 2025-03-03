package mark.back.service;

import lombok.AllArgsConstructor;
import mark.back.entity.RefreshToken;
import mark.back.entity.User;
import mark.back.repository.RefreshTokenRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@AllArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {
    RefreshTokenRepository refreshTokenRepository;

    @Override
    public RefreshToken createRefreshToken(User user) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUser(user);
        return refreshTokenRepository.save(refreshToken);
    }

    @Override
    public RefreshToken saveRefreshToken(RefreshToken refreshToken) {
        return refreshTokenRepository.save(refreshToken);
    }


    @Override
    public boolean refreshTokenExists(User user) {
        return refreshTokenRepository.findByUser(user).isPresent();
    }


    @Override
    @Transactional
    public int deleteToken(User user) {
        refreshTokenRepository.deleteByUser(user);

        if (refreshTokenExists(user)) {
            return 0;
        }

        return 1;
    }

    @Override
    public RefreshToken getRefreshToken(String id) {
        return refreshTokenRepository.findById(id).isPresent() ? refreshTokenRepository.findById(id).get() : null;
    }
}

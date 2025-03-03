package mark.back.service;

import mark.back.entity.RefreshToken;
import mark.back.entity.User;

public interface RefreshTokenService {
    //create refresh token
    RefreshToken createRefreshToken(User user);

    //find refresh token by id
    RefreshToken getRefreshToken(String id);

    //save changes to refresh token
    RefreshToken saveRefreshToken(RefreshToken refreshToken);


    //check if refresh token with user if exists
    boolean refreshTokenExists(User user);

    //delete token by user
    int deleteToken(User user);
}

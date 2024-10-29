package mark.back.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import mark.back.entity.RefreshToken;
import mark.back.entity.User;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;

import java.util.Date;

@Component
public class JWTUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(JWTUtil.class);
    static final String issuer = "spring-data";


    //generate token
    public String generateAccessToken(User user) {
        return JWT.create()
                .withIssuer(issuer)
                .withSubject(String.format("%s,%s", user.getId(), user.getUsername()))
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstraints.TOKEN_EXPIRATION))
                .sign(Algorithm.HMAC512(SecurityConstraints.SECRET_KEY));
    }

    //generate refresh token
    public String generateRefreshToken(User user, RefreshToken refreshToken) {
        return JWT.create()
                .withIssuer(issuer)
                .withSubject(String.format("%s,%s", user.getId(), user.getUsername()))
                .withClaim("tokenId", refreshToken.getToken())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstraints.TOKEN_EXPIRATION))
                .sign(Algorithm.HMAC512(SecurityConstraints.REFRESH_KEY));
    }
}
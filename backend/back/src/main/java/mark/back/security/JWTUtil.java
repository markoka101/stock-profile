package mark.back.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import mark.back.entity.RefreshToken;
import mark.back.entity.User;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;

import java.util.Date;
import java.util.Optional;

@Component
public class JWTUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(JWTUtil.class);

    private Algorithm accessTokenAlgorithm;
    private Algorithm refreshTokenAlgorithm;
    private JWTVerifier accessTokenVerifier;
    private JWTVerifier refreshTokenVerifier;
    static final String issuer = "spring-data";

    //initialize the token algorithms and verifiers
    public JWTUtil() {
        accessTokenAlgorithm = Algorithm.HMAC512(SecurityConstraints.SECRET_KEY);
        refreshTokenAlgorithm = Algorithm.HMAC512(SecurityConstraints.REFRESH_KEY);

        accessTokenVerifier  = JWT.require(accessTokenAlgorithm)
                .withIssuer(issuer)
                .build();

        refreshTokenVerifier = JWT.require(refreshTokenAlgorithm)
                .withIssuer(issuer)
                .build();
    }

    //generate token
    public String generateAccessToken(User user) {
        return JWT.create()
                .withIssuer(issuer)
                .withSubject(String.format("%s,%s", user.getId(), user.getUsername()))
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstraints.TOKEN_EXPIRATION))
                .sign(accessTokenAlgorithm);
    }

    //generate refresh token
    public String generateRefreshToken(User user, RefreshToken refreshToken) {
        return JWT.create()
                .withIssuer(issuer)
                .withSubject(String.format("%s,%s", user.getId(), user.getUsername()))
                .withClaim("tokenId", refreshToken.getToken())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstraints.TOKEN_EXPIRATION))
                .sign(refreshTokenAlgorithm);
    }

    //decode refresh token
    private Optional<DecodedJWT> decodeRefresh(String token) {
        try {
            return Optional.of(refreshTokenVerifier.verify(token));
        } catch(JWTVerificationException e) {
            LOGGER.error("refresh token error",e);
        }
        return Optional.empty();
    }

    //decode access token
    private Optional<DecodedJWT> decodeAccess(String token) {
        try {
            return Optional.of(accessTokenVerifier.verify(token));
        } catch(JWTVerificationException e) {
            LOGGER.error("access token error", e);
        }
        return Optional.empty();
    }
}
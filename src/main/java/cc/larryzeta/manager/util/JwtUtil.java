package cc.larryzeta.manager.util;

import cc.larryzeta.manager.config.JwtConfig;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

public class JwtUtil {

    public static final String USERNAME = "username";

    public static final String EMAIL = "email";


    public static void verify(String token, String username, String email, JwtConfig config) {

        Algorithm algorithm = config.getAlgorithm();
        JWTVerifier verifier = JWT.require(algorithm)
                .withClaim(USERNAME, username)
                .withClaim(EMAIL, email)
                .build();

        verifier.verify(token);

    }

    public static String getClaimField(String token, String claim){
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim(claim).asString();
        } catch (JWTDecodeException e){
            e.printStackTrace();
            return  null;
        }
    }

    public static String sign(String username, String email, JwtConfig config) {
        Date date = new Date(System.currentTimeMillis() + config.getTimeOut());
        Algorithm algorithm = Algorithm.HMAC256(config.getSecret());
        return JWT.create()
                .withClaim(USERNAME, username)
                .withClaim(EMAIL, email)
                .withExpiresAt(date)
                .sign(algorithm);
    }
}



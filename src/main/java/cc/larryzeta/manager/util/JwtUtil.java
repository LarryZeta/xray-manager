package cc.larryzeta.manager.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

public class JwtUtil {
    public static final String ACCOUNT = "username";
    public static final long EXPIRE_TIME = 30 * 60 * 1000;

    public static boolean verify(String token, String username, String secret) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim(ACCOUNT, username)
                    .build();

            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
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

    public static String sign(String username, String secret) {
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.create()
                .withClaim(ACCOUNT, username)
                .withExpiresAt(date)
                .sign(algorithm);
    }
}



package cc.larryzeta.manager.config;

import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class JwtConfig {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.timeout}")
    private Long timeOut;

    public String getSecret() {
        return secret;
    }

    public Long getTimeOut() {
        return timeOut;
    }

    @Bean
    public Algorithm getAlgorithm() {
        return Algorithm.HMAC256(secret);
    }
}

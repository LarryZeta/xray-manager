package cc.larryzeta.manager.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Feign;
import feign.Logger;
import feign.Request;
import feign.Retryer;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.slf4j.Slf4jLogger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    public Request.Options options() {
        return new Request.Options(15000, 30000);
    }

    static public <T> T getFlaskApi(Class<T> apiClazz, String apiUrl) {
        ObjectMapper mapper = new ObjectMapper();
        return Feign.builder()
                .retryer(Retryer.NEVER_RETRY)
                .decoder(new JacksonDecoder(mapper))
                .encoder(new JacksonEncoder())
                .logger(new Slf4jLogger())
                .logLevel(Logger.Level.FULL)
                .target(apiClazz, apiUrl);
    }

}

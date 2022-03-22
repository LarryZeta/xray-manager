package cc.larryzeta.manager.config;

import cc.larryzeta.manager.external.FlaskApi;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Feign;
import feign.Logger;
import feign.Request;
import feign.Retryer;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.slf4j.Slf4jLogger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class FeignConfig {

    @Value("${flask.base.url.list}")
    private List<String> flaskUrlList;

    @Bean
    public List<FlaskApi> getFlaskApi() {

        List<FlaskApi> flaskApiList = new ArrayList<>(flaskUrlList.size());
        for (String flaskUrl : flaskUrlList) {
            flaskApiList.add(this.getFlaskApi(FlaskApi.class, flaskUrl));
        }

        return flaskApiList;
    }

    @Bean
    public Request.Options options() {
        return new Request.Options(15000, 30000);
    }

    public <T> T getFlaskApi(Class<T> apiClazz, String apiUrl) {
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

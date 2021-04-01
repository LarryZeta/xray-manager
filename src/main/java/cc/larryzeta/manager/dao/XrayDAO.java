package cc.larryzeta.manager.dao;


import cc.larryzeta.manager.entity.XrayConfig;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class XrayDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(XrayDAO.class);
    private static final ObjectMapper MAPPER = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);

    @Value("${xray.config-file}")
    private String CONFIG_FILE;

    public XrayConfig getXrayConfig() {

        try {
            return MAPPER.readValue(new File(CONFIG_FILE), XrayConfig.class);
        } catch (JsonProcessingException jsonProcessingException) {
            LOGGER.error("JSON解析错误", jsonProcessingException);
        } catch (IOException e) {
            LOGGER.error("获取配置文件I/O错误", e);
        }
        return null;

    }

    public void saveXrayConfig(XrayConfig xrayConfig) {

        try {
            MAPPER.writeValue(new File(CONFIG_FILE), xrayConfig);
        } catch (IOException e) {
            LOGGER.error("写入配置文件I/O错误", e);
        }

    }

}

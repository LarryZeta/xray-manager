package cc.larryzeta.bill.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

import java.util.Map;

/**
 * ProjectName xray-manager
 * ClassName JSONUtil
 * Date 2021/01/13 13:55
 *
 * @author zhanglingyu
 * @description: JSON 工具类
 */

public class JSONUtil {

    private static ObjectMapper mapper = new ObjectMapper();

    public static Map<String, Object> getMap(String json) throws JsonProcessingException {

        return mapper.readValue(json, Map.class);

    }

    public static String toJsonString(Map<String, Object> jsonMap) throws JsonProcessingException {

        return mapper.writeValueAsString(jsonMap);

    }

}

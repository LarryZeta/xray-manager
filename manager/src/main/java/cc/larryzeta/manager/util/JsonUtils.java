package cc.larryzeta.manager.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class JsonUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtils.class);

    protected static ObjectMapper mapper;

    /**
     * 设置一些通用的属性
     */
    static {
        mapper = new ObjectMapper();
        // 反序列化配置
        // 如果json中有新增的字段并且是实体类类中不存在的，不报错
        mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        // 如果存在未知属性，则忽略不报错
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 允许key没有双引号
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        // 允许key有单引号
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        // 允许整数以0开头
        mapper.configure(JsonParser.Feature.ALLOW_NUMERIC_LEADING_ZEROS, true);
        // 允许字符串中存在回车换行控制符
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        // key忽略大小写
        mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        //序列化时为null时，不包含
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }


    public static String toJSONString(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (Throwable e) {
            LOGGER.error(String.format("object %s to json String exception!", object), e);
            throw new RuntimeException("java object to json String exception", e);
        }
    }

    public static <T> T parseObject(String jsonString, Class<T> clazz) {
        try {
            return isBlank(jsonString) ? null : mapper.readValue(jsonString, clazz);
        } catch (Throwable e) {
            LOGGER.error(String.format("parseObject exception:  %s to %s", jsonString, clazz), e);
            throw new RuntimeException("parseObject exception!", e);
        }
    }


    public static <T> T parseObject(byte[] bytes, Class<T> clazz) {
        try {
            return null == bytes ? null : mapper.readValue(bytes, clazz);
        } catch (Throwable e) {
            LOGGER.error(String.format("parseObject exception"), e);
            throw new RuntimeException("parseObject exception!", e);
        }
    }


    public static <T> List<T> parseArray(String jsonString, Class<T> clazz) {
        try {
            if (isBlank(jsonString)) {
                return null;
            }
            JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, clazz);
            return mapper.readValue(jsonString, javaType);
        } catch (Throwable e) {
            LOGGER.error(String.format("to object list exception %s to %s", jsonString, clazz), e);
            throw new RuntimeException("to object list exception!", e);
        }
    }


    public static <T> T objectClone(Object object, Class<T> clazz) {
        return object != null ? parseObject(toJSONString(object), clazz) : null;
    }


    public static Map<String, Object> toMap(Object object) {
        if (object == null) {
            return null;
        }
        try {
            if (object instanceof Map) {
                return (Map<String, Object>) object;
            }
        } catch (Exception e) {
            LOGGER.info("fail to convert to map:{}", toJSONString(object), e);
        }
        return toMap(toJSONString(object));
    }


    public static Map<String, Object> toMap(String jsonString) {
        if (isBlank(jsonString)) {
            return null;
        }
        try {
            return parseObject(jsonString, HashMap.class);
        } catch (Exception e) {
            LOGGER.error(String.format("toMap exception\n%s", jsonString), e);
            throw new RuntimeException("toMap exception!", e);
        }
    }


    @SuppressWarnings("All")
    public static Map<String, Object> toLinkedMap(Object object) {
        if (Objects.isNull(object)) {
            return null;
        }
        try {
            if (object instanceof Map) {
                return (Map<String, Object>) object;
            }
        } catch (Exception e) {
            LOGGER.info("fail to convert to LinkedHashMap:{}", toJSONString(object), e);
        }
        return parseObject(toJSONString(object), LinkedHashMap.class);
    }

    private static boolean isBlank(final CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (Character.isWhitespace(cs.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }

}

//package com.yhf.xuedaoqian.util;
//
//import com.boshang.core.biz.exception.SystemException;
//import com.fasterxml.jackson.annotation.JsonInclude.Include;
//import com.fasterxml.jackson.core.JsonGenerator.Feature;
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.DeserializationFeature;
//import com.fasterxml.jackson.databind.JavaType;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.SerializationFeature;
//import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
//import org.omg.CORBA.SystemException;
//
//import java.util.List;
//
///**
// * JSON 工具类
// *
// * @author WanWei
// * @date 2014-9-8 下午5:17:42
// */
//public final class JSONUtil {
//
//    private static ObjectMapper objectMapper;
//
//    static {
//        objectMapper = new ObjectMapper();
//        objectMapper.configure(Feature.IGNORE_UNKNOWN, true);
//        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
//        objectMapper.setSerializationInclusion(Include.NON_NULL);
//
//        objectMapper.setFilterProvider(new SimpleFilterProvider().setFailOnUnknownId(false));
//    }
//
//    /**
//     * 对象转化为json字符串
//     *
//     * @param object
//     * @return json字符串
//     * @author WanWei
//     * @date 2015-9-9 下午2:16:52
//     */
//    public static String getJsonString(Object object) {
//        String result = null;
//        try {
//            result = objectMapper.writeValueAsString(object);
//        } catch (Exception e) {
//            // 抛出业务异常
//            new SystemException(SystemException.JSON_OBJ_TO_STRING_ERROR, e, object.getClass())
//                    .throwEx();
//        }
//        return result;
//    }
//
//    /**
//     * 将json字符串转化为T类型的实例
//     *
//     * @param jsonString
//     * @param clazz
//     * @return T类型的实例
//     * @author WanWei
//     * @date 2015-9-9 下午2:17:31
//     */
//    public static <T> T getSimpleObject(String jsonString, Class<T> clazz) {
//        T result = null;
//        try {
//            result = objectMapper.readValue(jsonString, clazz);
//        } catch (Exception e) {
//            // 抛出业务异常
//            new SystemException(SystemException.JSON_STRING_TO_OBJ_ERROR, e, clazz, jsonString)
//                    .throwEx();
//        }
//        return result;
//    }
//
//    /**
//     * 将json字符串转化为T类型的列表
//     *
//     * @param text
//     * @param clazz
//     * @return T类型的列表
//     * @author WanWei
//     * @date 2015-9-9 下午2:19:24
//     */
//    public static <T> List<T> getObjectList(String text, Class<T> clazz) {
//        List<T> result = null;
//        JavaType javaType = objectMapper.getTypeFactory().constructParametrizedType(List.class, List.class, clazz);
//        try {
//            result = objectMapper.readValue(text, javaType);
//        } catch (Exception e) {
//            // 抛出业务异常
//            new SystemException(SystemException.JSON_STRING_TO_LIST_ERROR, e, clazz, text).throwEx();
//        }
//        return result;
//    }
//
//    /**
//     * 将json字符串转化为复杂类型
//     *
//     * @param text
//     * @param type
//     * @return 复杂类型
//     * @author WanWei
//     * @date 2015-9-9 下午2:20:02
//     */
//    public static <T> T getComplexObject(String text, TypeReference<T> type) {
//        T result = null;
//        try {
//            result = objectMapper.readValue(text, type);
//        } catch (Exception e) {
//            // 抛出业务异常
//            new SystemException(SystemException.JSON_STRING_TO_COMPLEX_OBJ_ERROR, e, type, text)
//                    .throwEx();
//        }
//
//        return result;
//    }
//}

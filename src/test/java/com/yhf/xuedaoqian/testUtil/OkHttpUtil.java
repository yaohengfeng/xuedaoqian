package com.yhf.xuedaoqian.testUtil;

import com.alibaba.fastjson.JSONObject;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.util.Objects;

/**
 * 发送请求的方法
 */
public class OkHttpUtil {

    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");

    // 在@BeforeEach方法里登录后返回jwt存入
    private static String currentJwt;

    public static String getCurrentJwt() {
        return currentJwt;
    }

    public static void setCurrentJwt(String currentJwt) {
        OkHttpUtil.currentJwt = currentJwt;
    }

    //post请求
    public static TestResponse httpPost(String body, String url, String jwt) throws IOException {
        TestResponse testResponse = new TestResponse();
        RequestBody requestBody = RequestBody.create(JSON, body);
        Request request;
        if (currentJwt != null && !"".equals(currentJwt)) {
            request = new Request.Builder()
                    .url(url)
                    .addHeader("jwt_token", currentJwt)
                    .post(requestBody)
                    .build();
        } else if (jwt != null && !"".equals(jwt)) {
            request = new Request.Builder()
                    .url(url)
                    .addHeader("jwt_token", jwt)
                    .post(requestBody)
                    .build();
        } else {
            request = new Request.Builder()
                    .url(url)
                    .post(requestBody)
                    .build();
        }
        OkHttpClient httpClient = OkHttpSingleton.getInstance();
        try (Response response = httpClient.newCall(request).execute()) {
            testResponse.setSuccessful(response.isSuccessful());
            testResponse.setHeaders(response.headers());
            testResponse.setMessage(response.message());
            testResponse.setCode(response.code());
            if (response.body() != null) {
                MediaType responseMediaType = Objects.requireNonNull(response.body()).contentType();
                String content = Objects.requireNonNull(response.body()).string();
                testResponse.setBodyString(content);
                testResponse.setResponseMediaType(responseMediaType);
            }
            return testResponse;
        }
    }

    //get请求
    public static TestResponse httpGet(String url, String jwt) throws IOException {
        TestResponse testResponse = new TestResponse();
        Request request;
        if (currentJwt != null && !"".equals(currentJwt)) {
            request = new Request.Builder()
                    .url(url)
                    .addHeader("jwt_token", currentJwt)
                    .build();
        }else if (jwt != null && !"".equals(jwt)) {
            request = new Request.Builder()
                    .url(url)
                    .addHeader("jwt_token", jwt)
                    .build();
        } else {
            request = new Request.Builder()
                    .url(url)
                    .build();
        }
        OkHttpClient httpClient = OkHttpSingleton.getInstance();
        try (Response response = httpClient.newCall(request).execute()) {
            testResponse.setSuccessful(response.isSuccessful());
            testResponse.setHeaders(response.headers());
            testResponse.setMessage(response.message());
            testResponse.setCode(response.code());
            if (response.body() != null) {
                MediaType responseMediaType = Objects.requireNonNull(response.body()).contentType();
                String content = Objects.requireNonNull(response.body()).string();
                testResponse.setBodyString(content);
                testResponse.setResponseMediaType(responseMediaType);
            }
            return testResponse;
        }
    }

    static final String myjwt = "eyJhbGciOiJIUzUxMiJ9.eyJvcmdOYW1lIjoi5Y2a5bCa6ZuG5ZuiIiwidXNlcl9pZCI6IjQ4MzY4YzQ5LTQxZGQtNDFkZi1iNjhjLTQ5NDFkZGIxZGZlNyIsInJvbGVJZCI6MSwidXNlcl9uYW1lIjoi5byg5LiJIiwibW9iaWxlIjoiMTMwMTIzNDEyMzUiLCJyb2xlTmFtZSI6Iui2hee6p-euoeeQhuWRmCIsInVzZXJfcGhvbmUiOiIxMzAxMjM0MTIzNSIsInVzZXJfYWNjb3VudCI6InpoYW5nc2FuIiwidXNlcklkIjoiNDgzNjhjNDktNDFkZC00MWRmLWI2OGMtNDk0MWRkYjFkZmU3Iiwib3JnSWQiOjJ9.OTlSVOeiVgxyZ9YCjz_-onNL6esJ1Vlt0zGR0gVr0jbXb55IQs5lrC_irVDE9c_ZQ7wJA6UT9LKnYmfZ6v7SXA";


    public static void main(String[] args) throws IOException {
//        Map<String, String> mobileAndPassword = new HashMap<>();
//        mobileAndPassword.put("mobile", "13012341235");
    //        mobileAndPassword.put("password", "5b70f596f3895ef0ce13189f833d32cb");
//        ObjectMapper objectMapper = new ObjectMapper();
//        String body = objectMapper.writeValueAsString(mobileAndPassword);
//        String url = "http://119.3.14.184:9001/login2B";
//        TestResponse response = OkHttpUtil.httpPost(body, url, null);
//        System.out.println("==getBodyString three times==");
//        System.out.println(response.getBodyString());
//        System.out.println(response.getBodyString());
//        System.out.println(response.getBodyString());
//        System.out.println("==getHeaders==");
//        System.out.println(response.getHeaders());
//        System.out.println("==getMessage==");
//        System.out.println(response.getMessage());
//        System.out.println("==getSuccessful==");
//        System.out.println(response.getSuccessful());
//        System.out.println("==getCode==");
//        System.out.println(response.getCode());

        String url = "http://119.3.14.184:9001/yxapi/settings/role/getRoleById?id=1";
        TestResponse response = OkHttpUtil.httpGet(url, myjwt);
        String responseBodyString = response.getBodyString();
        Assertions.assertTrue(response.getMessage().isEmpty());
        Assertions.assertEquals(200, response.getCode());

        JSONObject responseBodyObject = (JSONObject) JSONObject.parse(responseBodyString);
        String responseRoleName = (String) responseBodyObject.get("name");
        Assertions.assertTrue(responseRoleName.contains("管理员"));
    }
}

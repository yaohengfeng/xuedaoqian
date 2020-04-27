package com.yhf.xuedaoqian.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author SuyuZhuang
 * @date 2019/12/17 2:32 下午
 */
public class OkHttpUtil {

    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");

    private static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.MINUTES)
            .readTimeout(5, TimeUnit.MINUTES)
            .build();


    public static OkHttpClient getOkHttpInstance() {
        return okHttpClient;
    }


    /**
     * post同步请求
     *
     * @param url     链接
     * @param bodyStr 参数列表
     * @throws Exception
     */
    public static String post(String url, String bodyStr) throws Exception {
        RequestBody requestBody = RequestBody.create(bodyStr, JSON);
        OkHttpClient client = getOkHttpInstance();

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                return Objects.requireNonNull(response.body()).string();
            } else {
                throw new IOException("Unexpected code " + response);
            }
        } catch (Exception e) {
            throw new IOException("okhttp post 请求失败");
        }

    }

    /**
     * HTTP get请求
     *
     * @param url URL
     * @return
     * @throws Exception
     */
    public static String get(String url) throws Exception {
        OkHttpClient okHttpClient = getOkHttpInstance();
        Request request = new Request.Builder()
                .url(url)
                .build();
        try (Response response = okHttpClient.newCall(request).execute()) {
            return Objects.requireNonNull(response.body()).string();
        } catch (Exception e) {
            throw new IOException("okhttp get 请求失败");
        }

    }


    public static void main(String[] args) throws Exception {
        // get
        String getResp = OkHttpUtil.get("http://tieba.baidu.com/f?kw=qq&fr=ala0&tpl=5");
        System.out.println(getResp);

        System.out.println("===========================");
        // post
        Map<String, String> mobileAndPassword = new HashMap<>();
        String mobile = "13012341235";
        String password = "5b70f596f3895ef0ce13189f833d32cb";
        mobileAndPassword.put("mobile", mobile);
        mobileAndPassword.put("password", password);
        ObjectMapper objectMapper = new ObjectMapper();
        String body = objectMapper.writeValueAsString(mobileAndPassword);
        String postResp = OkHttpUtil.post("http://119.3.14.184:9001/login2B", body);
        System.out.println(postResp);

    }
}

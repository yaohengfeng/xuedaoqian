package com.yhf.xuedaoqian.testUtil;

import okhttp3.OkHttpClient;

/**
 * 对OKHttp进行单例封装
 */
public class OkHttpSingleton {
    /*
    懒汉式加载
    private static OkHttpClient okHttpClient;
    private MyOkHttp(){}
    public  static OkHttpClient getInstance(){
        if (okHttpClient == null){
            synchronized (OkHttpClient.class){
                if (okHttpClient == null){
                    okHttpClient = new OkHttpClient();
                }
            }
        }
        return okHttpClient;
    }*/

    //饿汉式加载
    private static OkHttpClient okHttpClient=new OkHttpClient();
    private OkHttpSingleton(){}
    public  static OkHttpClient getInstance(){
        return okHttpClient;
    }

}

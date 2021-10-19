package com.itwyx.classroomsearch.utils;


import com.google.gson.Gson;
import okhttp3.*;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertNotNull;

public class OkHttpUtil {
    private static OkHttpUtil OK_HTTP_UTIL;

    private static final MediaType JSON = MediaType.parse("application/x-www-form-urlencoded;charset=UTF-8");

    private final OkHttpClient client;

    private static final Gson gson = new Gson();

    /**
     * 初始化的同时设置okHttp3中超时方法
     */
    private OkHttpUtil() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(10244, TimeUnit.SECONDS)
                .readTimeout(10244, TimeUnit.SECONDS)
                .writeTimeout(10244, TimeUnit.SECONDS);
        client = builder.build();
    }

    /**
     * 单例模式获取OkHttpUtil
     * @return
     */
    public static OkHttpUtil getInstance(){
        if(OK_HTTP_UTIL==null){
            synchronized (OkHttpUtil.class){
                if(OK_HTTP_UTIL==null){
                    OK_HTTP_UTIL=new OkHttpUtil();
                }
            }
        }
        return OK_HTTP_UTIL;
    }


    /**
     * 参考OkHttp官网
     * @param url
     * @return
     * @throws IOException
     */
    public String get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
        assertNotNull(response.body());
        return Objects.requireNonNull(response.body()).string();

    }

    /**
     * 参考OkHttp官网
     * @param url
     * @param a
     * @return
     * @throws IOException
     */
    public String post(String url,String a) throws IOException {
        RequestBody  body = RequestBody .create(JSON, a);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        assertNotNull(response.body());
        return Objects.requireNonNull(response.body()).string();
    }


    /**
     * java对象转json格式传给前端(注意设置RequestBody的MediaType)
     * 未作登录手动传入cookies。。
     * @param url
     * @param RequestJsonbean
     * @return
     * @throws IOException
     */
    public String post(String url,Object RequestJsonbean,String cookies) throws IOException {
        String json = gson.toJson(RequestJsonbean);
        RequestBody  body = RequestBody .create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
//                .header("Cookie",cookies)
                .addHeader("Cookie",cookies)
                .build();
        Response response = client.newCall(request).execute();
        assertNotNull(response.body());
        return Objects.requireNonNull(response.body()).string();
    }




    /**
     * java对象转json格式传给前端(注意设置RequestBody的MediaType)
     * @param url
     * @param RequestJsonbean
     * @return
     * @throws IOException
     */
    public String post(String url,Object RequestJsonbean) throws IOException {
        String json = gson.toJson(RequestJsonbean);
        RequestBody  body = RequestBody .create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
//                .header("Cookie",cookies)
                .build();
        Response response = client.newCall(request).execute();
        assertNotNull(response.body());
        return Objects.requireNonNull(response.body()).string();
    }


    /**
     * form表单提交(无需指定MediaType)
     * @param url
     * @param formBody
     * @param cookies
     * @return
     * @throws IOException
     */
    public StringBuffer post(String url, FormBody formBody, String cookies) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
//                .header("Cookie",cookies)
                .header("Cookie",cookies)
                .build();
        Response response = client.newCall(request).execute();
        assertNotNull(response.body());
        String string = Objects.requireNonNull(response.body()).string();
        return new StringBuffer(string);
    }

}

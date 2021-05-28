package com.course.httpclient.dome;

import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class MzyLogin {
    private String url;
    private ResourceBundle bundle;
    private CookieStore cookieStore;

    @BeforeTest
    public void beforeTest(){
        bundle = ResourceBundle.getBundle("application_mock", Locale.CHINA);
        url = bundle.getString("mzy.url");
    }
    @Test
    public void login() throws IOException {
        String uri = bundle.getString("mzyLogin.url");
        String testUrl = this.url+uri;
        this.cookieStore = new BasicCookieStore();
        CloseableHttpClient client = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
        HttpPost httpPost = new HttpPost(testUrl);
        JSONObject param = new JSONObject();
        param.put("username","xiaoyi@zhiyanmz.com");
        param.put("password","123456");
        param.put("code","120909");
        httpPost.setHeader("content-type","application/json");
        StringEntity entity = new StringEntity(param.toString(),"utf-8");
        httpPost.setEntity(entity);
        String result;
        CloseableHttpResponse response = client.execute(httpPost);
        result = EntityUtils.toString(response.getEntity());
        System.out.println(result);
    }

    @Test(dependsOnMethods = {"login"})
    public void toIndex() throws IOException {
        String uri = bundle.getString("index.uri");
        String testUrl = this.url+uri;
        CloseableHttpClient client = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
        System.out.println(this.cookieStore);
        HttpPost httpPost = new HttpPost(testUrl);
        JSONObject param = new JSONObject();
        param.put("username","xiaoyi@zhiyanmz.com");
        param.put("password","123456");
        param.put("code","120909");
        httpPost.setHeader("content-type","application/json");
        StringEntity entity = new StringEntity(param.toString(),"utf-8");
        httpPost.setEntity(entity);
        String result;
        CloseableHttpResponse response = client.execute(httpPost);
        result = EntityUtils.toString(response.getEntity());
        System.out.println(result);
    }
}

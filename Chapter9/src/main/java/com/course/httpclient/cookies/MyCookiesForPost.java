package com.course.httpclient.cookies;

import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MyCookiesForPost {

    private String url;
    private ResourceBundle bundle;
    //用来存储cookies信息的变量
    private CookieStore cookieStore;

    @BeforeTest
    public void beforeTest(){
        bundle = ResourceBundle.getBundle("application_mock", Locale.CHINA);
        url = bundle.getString("test.url");
    }
    @Test
    public void testGetCookies() throws IOException {
        String result;
        //从配置文件中拼接url
        HttpGet get = new HttpGet(this.url+bundle.getString("getCookies.uri"));
        this.cookieStore = new BasicCookieStore();
        CloseableHttpClient client = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
        CloseableHttpResponse response = client.execute(get);
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);

        //获取cookies信息
        List<Cookie> cookies = cookieStore.getCookies();
        for (Cookie cookie:cookies){
            String name = cookie.getName();
            String value = cookie.getValue();
            System.out.println(name+":"+value);
        }
    }
    @Test(dependsOnMethods = {"testGetCookies"})
    public void testPostMethod() throws IOException {
        String uri = bundle.getString("postWithCookies.uri");
        String testUrl = this.url+uri;
        //声明一个client对象，用来进行方法的执行
        CloseableHttpClient client = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
        //声明一个方法，post方法
        HttpPost httpPost = new HttpPost(testUrl);
        //添加参数
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name","huhansan");
        jsonObject.put("age","18");
        //设置请求头信息 header
        httpPost.setHeader("content-type","application/json");
        //将参数信息添加到方法中
        StringEntity entity = new StringEntity(jsonObject.toString(),"utf-8");
        httpPost.setEntity(entity);
        //声明一个对象来存储响应结果
        String result;
        //设置cookies信息，声明client已经加上
        //执行post
        CloseableHttpResponse response = client.execute(httpPost);
        //获取响应结果
        result = EntityUtils.toString(response.getEntity());
        System.out.println(result);
        //判断返回结果是否符合预期
        //将返回的响应结果字符串转化为json对象
        JSONObject resultJson = new JSONObject(result);
        //获取结果值
        String success = resultJson.getString("huhansan");
        String status = (String) resultJson.get("status");
        //判断返回结果的值
        Assert.assertEquals("success",success);
        System.out.println(success);
        Assert.assertEquals("1",status);
        System.out.println(status);
    }
}

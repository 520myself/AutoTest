package com.course.server;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@Api(value = "/",description = "这是我的全部get方法")
public class MyGetMethod {
    @RequestMapping(value = "/getCookies",method = RequestMethod.GET)
    @ApiOperation(value = "通过这个方法可以获取到Cookies",httpMethod = "GET")
    public String getCookies(HttpServletResponse response){
        //HttpServletRequest  装请求信息的类
        //HttpServletResponse  装响应信息的类
        Cookie cookie = new Cookie("login","true");
        response.addCookie(cookie);
        return "恭喜你获得cookies信息成功";
    }
    /*
    * 要求客户端携带Cookies信息访问
    * */
    @RequestMapping(value = "/getWithCookies",method = RequestMethod.GET)
    @ApiOperation(value = "需要携带cookies信息才能访问",httpMethod = "GET")
    public String getWithCookies(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if (Objects.isNull(cookies)) {
            return "你必须携带cookies信息来";
        }
        for (Cookie cookie:cookies){
            if (cookie.getName().equals("login")&cookie.getValue().equals("true")){
                return "这是一个需要携带cookies信息才能访问的get请求";
            }
        }
        return "你必须携带cookies信息来";
    }
    /*
    * 需要携带参数才能访问的get请求
    * 第一种实现方式 url:key=value&key=value
    * 模拟获取商品列表
    * */
    @RequestMapping(value = "getWithParam",method = RequestMethod.GET)
    @ApiOperation(value = "需要携带参数才能访问的get请求方法一",httpMethod = "GET")
    public Map<String,Integer> getList(@RequestParam Integer start,
                                       @RequestParam Integer end){
        Map<String,Integer> myList = new HashMap<>();
        myList.put("手机",5499);
        myList.put("T恤",89);
        myList.put("纸巾",1);
        return myList;
    }
    /*
     * 需要携带参数才能访问的get请求
     * 第二种实现方式 url:ip:port/getWithParam/10/20
     * */
    @RequestMapping("/getWithParam/{start}/{end}")
    @ApiOperation(value = "需要携带参数才能访问的get请求方法二",httpMethod = "GET")
    public Map<String, Integer> myGetList(@PathVariable Integer start,
                                          @PathVariable Integer end){
        Map<String,Integer> myList = new HashMap<>();
        myList.put("手机",5499);
        myList.put("T恤",89);
        myList.put("纸巾",1);
        return myList;
    }
}

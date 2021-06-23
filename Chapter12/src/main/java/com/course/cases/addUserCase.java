package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.AddUserCase;
import com.course.model.User;
import com.course.utils.DataBaseUtil;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class addUserCase {
    @Test(dependsOnGroups = "loginTrue",description = "添加用户接口测试")
    public void addUser() throws IOException, InterruptedException {
        SqlSession session = DataBaseUtil.getSqlSession();
        AddUserCase addUserCase = session.selectOne("addUserCase",1);
        System.out.println(addUserCase.toString());
        System.out.println(TestConfig.addUserInfoUrl);

        //发请求，获取结果
        String result = getResult(addUserCase);
        Thread.sleep(5000);
        //验证返回结果

        User user = session.selectOne("addUser",addUserCase);
//        System.out.println(user.toString());
        Assert.assertEquals(addUserCase.getExpected(),result);
    }

    private String getResult(AddUserCase addUserCase) throws IOException {
        HttpPost post = new HttpPost(TestConfig.addUserInfoUrl);
        JSONObject param = new JSONObject();
        param.put("userName",addUserCase.getUserName());
        param.put("password",addUserCase.getPassword());
        param.put("age",addUserCase.getAge());
        param.put("sex",addUserCase.getSex());
        param.put("permission",addUserCase.getPermission());
        param.put("isDelete",addUserCase.getIsDelete());

        //设置请求头
        post.setHeader("Content-Type","application/json");
        StringEntity entity = new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);
        //设置cookies
        TestConfig.httpClient = HttpClients.custom().setDefaultCookieStore(TestConfig.store).build();
        System.out.println(TestConfig.store.toString());
        String result;//存放返回结果
        CloseableHttpResponse response = TestConfig.httpClient.execute(post);
        result = EntityUtils.toString(response.getEntity());
        return result;

    }
}

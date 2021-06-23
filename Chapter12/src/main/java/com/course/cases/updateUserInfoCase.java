package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.UpdateUserInfoCase;
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

public class updateUserInfoCase {
    @Test(dependsOnGroups = "loginTrue",description = "更改用户信息")
    public void updateUserInfo() throws IOException, InterruptedException {
        SqlSession session = DataBaseUtil.getSqlSession();
        //获取测试用例
        UpdateUserInfoCase updateUserInfoCase = session.selectOne("updateUserInfoCase",1);
        System.out.println(updateUserInfoCase.toString());
        System.out.println(TestConfig.updateUserInfoUrl);
        //根据测试用例执行测试，获得返回结果
        int result = getResult(updateUserInfoCase);
        Thread.sleep(5000);
        System.out.println(updateUserInfoCase.getExpected());
        //根据测试用例查询修改后的数据
        User user = session.selectOne(updateUserInfoCase.getExpected(),updateUserInfoCase);
        System.out.println(updateUserInfoCase.getUserId());

        session.selectOne(updateUserInfoCase.getExpected(),updateUserInfoCase);
        Assert.assertNotNull(user);
        Assert.assertNotNull(result);
    }

    @Test(dependsOnGroups = "loginTrue",description = "删除用户信息")
    public void deleteUser() throws IOException, InterruptedException {
        SqlSession session = DataBaseUtil.getSqlSession();
        UpdateUserInfoCase updateUserInfoCase = session.selectOne("updateUserInfoCase",2);
        System.out.println(updateUserInfoCase.toString());
        System.out.println(TestConfig.updateUserInfoUrl);

        int result = getResult(updateUserInfoCase);
        Thread.sleep(5000);
        System.out.println(updateUserInfoCase.getExpected());
        User user = session.selectOne(updateUserInfoCase.getExpected(),updateUserInfoCase);
        Assert.assertNotNull(user);
        Assert.assertNotNull(result);
    }

    private int getResult(UpdateUserInfoCase updateUserInfoCase) throws IOException {
        HttpPost post = new HttpPost(TestConfig.updateUserInfoUrl);
        JSONObject param = new JSONObject();
        param.put("id",updateUserInfoCase.getUserId());
        param.put("userName",updateUserInfoCase.getUserName());
        param.put("sex",updateUserInfoCase.getSex());
        param.put("age",updateUserInfoCase.getAge());
        param.put("permission",updateUserInfoCase.getPermission());
        param.put("isDelete",updateUserInfoCase.getIsDelete());
        post.setHeader("Content-Type","application/json");
        StringEntity entity = new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);
        TestConfig.httpClient = HttpClients.custom().setDefaultCookieStore(TestConfig.store).build();
        String i;
        CloseableHttpResponse response = TestConfig.httpClient.execute(post);
        i = EntityUtils.toString(response.getEntity(),"utf-8");
        int result = Integer.parseInt(i);
        return result;
    }
}

package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.GetUserListCase;
import com.course.model.User;
import com.course.utils.DataBaseUtil;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class getUserListCase {
    @Test(dependsOnGroups = "loginTrue",description = "获取性别为男的用户信息")
    public void getUserList() throws IOException {
        SqlSession session = DataBaseUtil.getSqlSession();
        GetUserListCase getUserListCase = session.selectOne("getUserListCase",1);
        System.out.println(getUserListCase.toString());
        System.out.println(TestConfig.getUserListUrl);

        //发请求，获取结果
        JSONArray resultJson = getResult(getUserListCase);
        //验证
        List<User> userList = session.selectList(getUserListCase.getExpected(),getUserListCase);
        for(User u:userList){
            System.out.println("获取的user:"+u.toString());
        }
        JSONArray userListJson = new JSONArray(userList);
        Assert.assertEquals(userListJson.length(),resultJson.length());
        for (int i=0;i<userListJson.length();i++){
            JSONObject expect = (JSONObject) resultJson.get(i);
            JSONObject actual = (JSONObject) userListJson.get(i);
            Assert.assertEquals(actual.toString(),expect.toString());
        }
    }

    private JSONArray getResult(GetUserListCase getUserListCase) throws IOException {
        HttpPost post = new HttpPost(TestConfig.getUserListUrl);
        JSONObject param = new JSONObject();
        param.put("userName",getUserListCase.getUserName());
        param.put("age",getUserListCase.getAge());
        param.put("sex",getUserListCase.getSex());
        post.setHeader("Content-Type","application/json");
        StringEntity entity = new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);
        TestConfig.httpClient = HttpClients.custom().setDefaultCookieStore(TestConfig.store).build();
        String result;
        CloseableHttpResponse response = TestConfig.httpClient.execute(post);
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        JSONArray jsonArray = new JSONArray(result);
        return jsonArray;
    }
}

package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.GetUserListCase;
import com.course.model.User;
import com.course.utils.DataBaseUtil;
import org.apache.ibatis.session.SqlSession;
import org.bson.json.JsonObject;
import org.json.JSONArray;
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
        JSONArray userListJson = new JSONArray();
        Assert.assertEquals(userListJson.length(),resultJson.length());
        for (int i=0;i<userListJson.length();i++){
            JsonObject expect = (JsonObject) resultJson.get(i);
            JsonObject actual = (JsonObject) userListJson.get(i);
            Assert.assertEquals(expect,actual);
        }
    }

    private JSONArray getResult(GetUserListCase getUserListCase) {
        return null;
    }
}

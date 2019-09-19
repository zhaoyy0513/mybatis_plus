package com.example.mybatisplus;
import com.alibaba.fastjson.JSON;
import com.example.mybatisplus.entity.User;
import com.example.mybatisplus.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisplusApplicationTests {
    @Resource
    UserMapper userMapper;

    @Test
    public void contextLoads() {
        User user = new User();
        user.setId(1);
        User user1 = userMapper.selectById(user);
        System.out.println("通过Id查询selectById:"+ JSON.toJSONString(user1));
    }

}

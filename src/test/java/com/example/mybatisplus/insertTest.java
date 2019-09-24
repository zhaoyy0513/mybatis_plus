package com.example.mybatisplus;

import com.example.mybatisplus.entity.Focus;
import com.example.mybatisplus.entity.User;
import com.example.mybatisplus.mapper.FocusMapper;
import com.example.mybatisplus.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author zhaoyuyang
 * @createTime 2019/9/20 0020 14:49
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class insertTest {
    @Resource
    FocusMapper focusMapper;

    /**
     * insert目前只有这一个方法
     *
     * @param
     * @return void
     * @author zhaoyuyang
     * @since 2019/9/20 0020 14:51
     */
    @Test
    public void a() {
        Focus focus = new Focus();
        focus.setUserId(6);
        focus.setFocusedId("1;3;");
        focusMapper.insert(focus);
    }
}

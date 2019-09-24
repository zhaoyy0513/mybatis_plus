package com.example.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mybatisplus.entity.Focus;
import com.example.mybatisplus.mapper.FocusMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author zhaoyuyang
 * @createTime 2019/9/24 0024 16:25
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class updateTest {
    @Resource
    FocusMapper mapper;


    /**
    * 根据id进行修改
    *
    * @param
    * @return void
    * @author zhaoyuyang
    * @since 2019/9/24 0024 16:26
    */
    @Test
    public void a(){
        Focus focus = mapper.selectById(19);
        // 2;4;6
        String deleteId = "4";
        String focusStr = focus.getFocusedId();
        int index = focusStr.indexOf(deleteId);
        focusStr = focusStr.substring(0,index)+focusStr.substring(index+2);
        focus.setFocusedId(focusStr);
        mapper.updateById(focus);
    }

    /**
    * 根据条件进行更新
    *
    * @param
    * @return void
    * @author zhaoyuyang
    * @since 2019/9/24 0024 16:38
    */
    @Test
    public void b(){
        //将deleted = 0的数据进行修改  将deleted改成1
        QueryWrapper<Focus> wrapper = new QueryWrapper<>();
        wrapper.eq("deleted",0);
        Focus focus = new Focus();
        focus.setDeleted(1);
        mapper.update(focus,wrapper);
    }

}

package com.example.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.mybatisplus.entity.Focus;
import com.example.mybatisplus.mapper.FocusMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author zhaoyuyang
 * @createTime 2019/9/24 0024 15:27
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class deleteTest {
    @Resource
    FocusMapper mapper;


    /**
    * 根据id进行删除测试
    *
    * @param
    * @return void
    * @author zhaoyuyang
    * @since 2019/9/24 0024 15:30
    */
    @Test
    public void a(){
        //assertThat(mapper.deleteById(11)).isLessThan(0);
        //删除id为11的focus信息
        mapper.deleteById(11);
    }

    /**
    * 删除 focus表中id字段为4的行
    *
    * @param
    * @return void
    * @author zhaoyuyang
    * @since 2019/9/24 0024 16:02
    */
    @Test
    public void b(){
        mapper.delete(new QueryWrapper<Focus>().lambda().eq(Focus::getId,4));
    }

    /**
    * 批量删除，id在list中的所有focus
    *
    * @param
    * @return void
    * @author zhaoyuyang
    * @since 2019/9/24 0024 16:02
    */
    @Test
    public void c(){
        List<Integer> list = new ArrayList<>();
        list.add(14);
        list.add(15);
        list.add(16);
        list.add(17);
        mapper.deleteBatchIds(list);
    }


    /**
    * 把条件组成键值对进行删除
    *
    * @param
    * @return void
    * @author zhaoyuyang
    * @since 2019/9/24 0024 16:03
    */
    @Test
    public void d(){
        Map<String,Object> map = new HashMap<>(8);
        //删除id=1 user_id =7的这一行数据
        map.put("user_id",1);
        map.put("id",1);
        mapper.deleteByMap(map);
    }
}

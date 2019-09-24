package com.example.mybatisplus;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatisplus.entity.User;
import com.example.mybatisplus.mapper.UserMapper;
import com.example.mybatisplus.util.PageEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class selectTest {
    @Resource
    private UserMapper userMapper;

    /**
     * 通过id进行查询
     *
     * @param
     * @return void
     * @author zhaoyuyang
     * @since 2019/9/19 0019 17:02
     */
    @Test
    public void a() {
        User user = new User();
        user.setId(1);
        //查询id为1的值
        User user1 = userMapper.selectById(user);
        //assertThat(user1.getId()).isEqualTo(0); 这里故意让其报错，明白assertThat的用法
        System.out.println("通过selectById查询:" + JSON.toJSONString(user1));
    }

    /**
     * 正则查询
     *
     * @param
     * @return void
     * @author zhaoyuyang
     * @since 2019/9/19 0019 17:02
     */
    @Test
    public void b() {
        //查询id为2的用户
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("id", 2));
        //下面是lambda写法,二者是等效的
        //User user =  userMapper.selectOne(new QueryWrapper<User>().lambda().eq(User::getId,2));
        System.out.println("通过selectOne查询:" + JSON.toJSONString(user));
    }

    /**
     * 获取实体list
     *
     * @param
     * @return void
     * @author zhaoyuyang
     * @since 2019/9/20 0020 10:56
     */
    @Test
    public void c() {
        //条件可以参考官方文档的条件构造器 https://mp.baomidou.com/guide/wrapper.html#abstractwrapper
        //条件查询，查询id不为0的，可以理解为查询所有学生
        //(1)List<User> list = userMapper.selectList(new QueryWrapper<User>().ne("id",0));

//        (2)List<Integer> ids = new ArrayList<>();
//        ids.add(1);
//        ids.add(3);
//        List<User> list = userMapper.selectList(new QueryWrapper<User>().in("id",ids));
//        等同于List<User> users = userMapper.selectBatchIds(ids);

        List<String> names = new ArrayList<>();
        names.add("吹不散眉弯");
        names.add("朝气蓬勃");
        //查询名字在list的所有用户
        List<User> list = userMapper.selectList(new QueryWrapper<User>().in("user_name", names));
        for (User user : list) {
            System.out.println(JSONObject.toJSONString(user));
        }
    }

    /**
     * selectObjs  只返回第一个字段的值，一般用于获取id
     *
     * @param
     * @return void
     * @author zhaoyuyang
     * @since 2019/9/20 0020 11:40
     */
    @Test
    public void e() {
        //这里查询的是获取所有用户密码为111的用户所对应的的id，不是所有用户信息,仅仅返回的是用户的id
        //List<Object> id = userMapper.selectObjs(new QueryWrapper<User>().eq("user_pwd", "111"));

        /*
        这里查询的是获取所有用户密码答案像"三" 的用户所对应的的id，不是所有用户信息,仅仅返回的是用户的id
        源码发现，like里面第二个参数加不加%都行，因为源码中默认使用String.contact()方法进行拼接了
        如果没加会默认加上
           public static String concatLike(Object str, SqlLike type) {
        switch(type) {
        case LEFT:
            return "%" + str;
        case RIGHT:
            return str + "%";
        default:
            return "%" + str + "%";
        }
    }
        * */
        List<Object> id = userMapper.selectObjs(new QueryWrapper<User>().like("problem_answer", "%三%"));
        for (Object o : id) {
            System.out.println(JSONObject.toJSONString(o));
        }
    }

    /**
    * selectPage 分页查询
    * 如果没有条件,则selectPage(）第二个参数写为null
     *
    * @param
    * @return void
    * @author zhaoyuyang
    * @since 2019/9/20 0020 11:56
    */
    @Test
    public void f(){
        //前台需要传递的两个参数，[第几页，每页显示的数据条数]
        Page<User> page = new Page<>(1,5);

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.ne("id",0).orderByDesc("id");

        IPage iPage = userMapper.selectPage(page, queryWrapper);
        //上面的那些等同于下面
        //IPage<User> iPage = userMapper.selectPage(new Page<>(1, 5),new QueryWrapper<User>().ne("id",0));
        System.out.println("------数据------");
        PageEntity entity = PageEntity.getInstance(iPage);
        List data = entity.getData();
        for (Object datum : data) {
            System.out.println(JSONObject.toJSONString(datum));
        }
        System.out.println("------getSize---5---");
        System.out.println(entity.getLimit());
        System.out.println("------getTotal---103---");
        System.out.println(entity.getTotal());
        System.out.println("------getPages---21---");  //前台常用
        System.out.println(entity.getPage());
    }


    /**
     * selectMap测试,其实也是条件查询,但是似乎没有selectList灵活
     *
     * @param
     * @return void
     * @author zhaoyuyang
     * @since 2019/9/20 0020 11:22
     */
    @Test
    public void d() {
        Map<String, Object> map = new HashMap<>(8);
        //查询user_pwd=111的所有用户
        map.put("user_pwd", "111");
        List<User> users = userMapper.selectByMap(map);
        for (User user : users) {
            System.out.println(JSONObject.toJSONString(user));
        }
    }

    /**
    * selectMapsPage
    *分页查询
     *
    * @param
    * @return void
    * @author zhaoyuyang
    * @since 2019/9/20 0020 14:26
    */
    @Test
    public void g(){
        //分页条件page两个参数[第几页，每页显示的数据条数]
        IPage<Map<String, Object>> mapIPage = userMapper.selectMapsPage(new Page<>(1, 5), new QueryWrapper<User>().like("problem_answer", "三"));
        PageEntity entity  = PageEntity.getInstance(mapIPage);
        System.out.println(JSONObject.toJSONString(entity));
        //结果好像跟则selectPage()没有太大区别
    }

    /**
    * selectCount
    *根据 Wrapper 条件，查询总记录数
     *
    * @param
    * @return void
    * @author zhaoyuyang
    * @since 2019/9/20 0020 14:38
    */
    @Test
    public void h(){
        //下面进行了一下强制转换，原本静态方法是Wrappers.query(),返回的就是QueryWrapper
        //Integer integer = userMapper.selectCount((Wrappers.<User>query().like("problem_answer", "三")));

        //因此可以用Wrappers的后台queryWrapper进行查询,下面第一种使用lambda方法进行书写，结果都是一样的
       // Integer integer = userMapper.selectCount(new QueryWrapper<User>().lambda().like(User::getProblemAnswer,"三"));
        Integer integer = userMapper.selectCount(new QueryWrapper<User>().like("problem_answer","三"));
        System.out.println("问题答案中有‘三’的条数是"+integer);
    }


}

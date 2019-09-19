package com.example.mybatisplus.mapper;

import com.example.mybatisplus.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhaoyy
 * @since 2019-09-19
 */
public interface UserMapper extends BaseMapper<User> {
    @Override
    int insert(User entity);
}

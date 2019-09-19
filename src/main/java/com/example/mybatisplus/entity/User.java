package com.example.mybatisplus.entity;

import com.baomidou.ant.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhaoyy
 * @since 2019-09-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 用户角色类型(0管理员,2老师可以发学院帖子,1学生不可以)
     */
    private Integer userRole;

    /**
     * 用户id(学号、工号)
     */
    private String userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户密码
     */
    private String userPwd;

    /**
     * 用户手机号
     */
    private String userPhone;

    /**
     * 用户所处院系
     */
    private String userCollege;

    /**
     * 找回功能问题的问题id
     */
    private Integer problemId;

    /**
     * 找回功能问题答案
     */
    private String problemAnswer;

    /**
     * 用户性别
     */
    private String userSex;

    /**
     * 用户头像
     */
    private String userImg;

    /**
     * 未读消息数
     */
    private Integer unreadMessage;

    /**
     * 收藏帖子数
     */
    private Integer postCollectionNum;

    /**
     * 关注的人数
     */
    private Integer focusNumber;


}

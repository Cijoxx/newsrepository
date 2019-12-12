package com.yjls.mapper;

import com.yjls.entity.NewsEntity;
import com.yjls.entity.RoleEntity;
import com.yjls.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface UserMapper {

    //用户登录检验
    public UserEntity loginUser(UserEntity userEntity);

    //用户注册
    public boolean registUser(UserEntity userEntity);

    //用户账户唯一性检查
    public int uniqueCheck(String useraccount);

    //展示个人信息
    public UserEntity userinfo(int userid);

    //修改个人信息
    public  boolean edituser(UserEntity userEntity);

    //申请成为作者
    public boolean applywriter(UserEntity userEntity);

    //展示所有用户列表
    public List<UserEntity> listuser(UserEntity userEntity);

    //查询所有角色
    public List<RoleEntity> listrole(RoleEntity roleEntity);

    //根据用户id删除用户信息
    public boolean setStatusOfUser(@Param("userid") Integer userid,@Param("userstatus") Integer userstatus);

    //根据用户id查询用户信息
    public UserEntity queryUserById(Integer userid);

    //根据用户id修改用户信息
    public boolean updUser(UserEntity userEntity);

    //根据用户id修改多个用户信息
    public boolean setAStatusByIds(@Param("ids")String ids ,@Param("userstatus")Integer userstatus);
}

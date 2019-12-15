package com.yjls.mapper;

import com.yjls.entity.AuthEntity;
import com.yjls.entity.NewsEntity;
import com.yjls.entity.RoleEntity;
import com.yjls.entity.UserEntity;
import org.apache.catalina.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

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

    //展示作者信息
    public UserEntity getwriterdetail(UserEntity userEntity);

    //根据用户id删除用户信息
    public boolean setStatusOfUser(@Param("userid") Integer userid,@Param("userstatus") Integer userstatus);

    //根据用户id查询用户信息
    public UserEntity queryUserById(Integer userid);

    //根据用户id修改用户信息
    public boolean updUser(UserEntity userEntity);

    //根据用户id修改多个用户信息
    public boolean setAStatusByIds(@Param("ids")String ids ,@Param("userstatus")Integer userstatus);

    //作者审核通过
    public boolean checkwriterpass(Integer userid);

    //作者审核未通过
    public boolean checkwriterfail(Integer userid);

    //根据用户账号查询用户信息
    public UserEntity queryUserByAccount(String useraccount);

    //根据账号查询角色信息
    public Set<String> queryRoleByAccount(String useraccount);

    //查询所有角色
    public List<RoleEntity> listrole(RoleEntity roleEntity);

    //修改用户展示所有角色
    public List<RoleEntity> listroleForUser();

    //查询所有权限
    public List<AuthEntity> queryAuths();

    //添加角色信息
    public boolean addRole(RoleEntity roleEntity);

    //添加角色的权限信息
    public boolean addAuthOfRole(@Param("rid") Integer rid,@Param("auid") Integer auid);

    //根据角色id查询角色信息
    public RoleEntity queryRoleById(String rid);

    //修改权限信息
    public boolean updRole(RoleEntity roleEntity);

    //根据角色id删除权限信息
    public boolean delAuthOfRole(Integer rid);

    //设置角色状态
    public boolean setStatusOfRole(@Param("rid")Integer rid,@Param("rstatus")Integer rstatus);

    //设置多个角色状态
    public boolean setRStatusByIds(@Param("rids")String rids,@Param("rstatus")Integer rstatus);

}

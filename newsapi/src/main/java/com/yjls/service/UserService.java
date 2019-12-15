package com.yjls.service;

import com.github.pagehelper.PageInfo;
import com.yjls.entity.AuthEntity;
import com.yjls.entity.RoleEntity;
import com.yjls.entity.UserEntity;

import java.util.List;
import java.util.Set;

public interface UserService {
    //用户登录检验
    public UserEntity loginUser(UserEntity userEntity);

    //用户注册
    public boolean registUser(UserEntity userEntity);

    //用户账户唯一性检查
    public boolean uniqueCheck(String useraccount);

    //展示个人信息
    public UserEntity userinfo(int userid);

    //修改个人信息
    public  boolean edituser(UserEntity userEntity);

    //申请成为作者
    public boolean applywriter(UserEntity userEntity);

    //展示所有用户列表
    public PageInfo<UserEntity> listuser(UserEntity userEntity);

    //展示作者详情
    public UserEntity getwriterdetail(UserEntity userEntity);

    //查询所有角色
    public PageInfo<RoleEntity> listrole(RoleEntity roleEntity);

    //根据用户id删除用户信息
    public boolean setStatusOfUser(Integer userid,Integer userstatus);

    //根据用户id查询用户信息
    public UserEntity queryUserById(Integer userid);

    //根据用户id修改用户信息
    public boolean updUser(UserEntity userEntity);

    //根据用户id修改多个用户信息
    public boolean setAStatusByIds(String ids ,Integer userstatus);

    //作者审核通过
    public boolean checkwriterpass(Integer userid);

    //作者审核未通过
    public boolean checkwriterfail(Integer userid);

    //根据用户账号查询用户信息
    public UserEntity queryUserByAccount(String useraccount);

    //根据账号查询角色信息
    public Set<String> queryRoleByAccount(String useraccount);

    //修改用户展示所有角色
    public List<RoleEntity> listroleForUser();

    //查询所有权限
    public List<AuthEntity> queryAuths();

    //添加角色信息
    public boolean addRole(RoleEntity roleEntity,String auths);

    //根据角色id查询角色信息
    public RoleEntity queryRoleById(String rid);

    //修改权限信息
    public boolean updRole(RoleEntity roleEntity,String auths);

    //设置角色状态
    public boolean setStatusOfRole(Integer rid,Integer rstatus);

    //设置多个角色状态
    public boolean setRStatusByIds(String rids,Integer rstatus);


}

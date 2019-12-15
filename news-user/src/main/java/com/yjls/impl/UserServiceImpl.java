package com.yjls.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yjls.entity.AuthEntity;
import com.yjls.entity.RoleEntity;
import com.yjls.entity.UserEntity;
import com.yjls.mapper.UserMapper;
import com.yjls.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@com.alibaba.dubbo.config.annotation.Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    //用户登录检验
    public UserEntity loginUser(UserEntity userEntity){
        return userMapper.loginUser(userEntity);
    }

    //用户注册
    public boolean registUser(UserEntity userEntity){
        return userMapper.registUser(userEntity);
    }

    //用户账户唯一性检查
    public boolean uniqueCheck(String useraccount){
        return userMapper.uniqueCheck(useraccount)>0;
    }

    //展示个人信息
    @Override
    public UserEntity userinfo(int userid) {
        return userMapper.userinfo(userid);
    }

    //修改个人信息
    @Override
    public boolean edituser(UserEntity userEntity) {
        return userMapper.edituser(userEntity);
    }

    //申请成为作者
    @Override
    public boolean applywriter(UserEntity userEntity) {
        return userMapper.applywriter(userEntity);
    }

    //展示所有用户列表
    public PageInfo<UserEntity> listuser(UserEntity userEntity){
        PageHelper.startPage(userEntity.getPageNum(),userEntity.getPageSize());
        List<UserEntity> list = userMapper.listuser(userEntity);
        PageInfo<UserEntity> userEntityPageInfo = new PageInfo<UserEntity>(list);
        return userEntityPageInfo;
    }

    //展示作者信息
    @Override
    public UserEntity getwriterdetail(UserEntity userEntity) {
        return userMapper.getwriterdetail(userEntity);
    }

    //根据用户id删除用户信息
    public boolean setStatusOfUser(Integer userid, Integer userstatus){
        return userMapper.setStatusOfUser(userid,userstatus);
    }

    //根据用户id查询用户信息
    public UserEntity queryUserById(Integer userid){
        return  userMapper.queryUserById(userid);
    }

    //根据用户id修改用户信息
    public boolean updUser(UserEntity userEntity){
        return userMapper.updUser(userEntity);
    }

    //根据用户id修改多个用户信息
    public boolean setAStatusByIds(String ids ,Integer userstatus){
        return userMapper.setAStatusByIds(ids,userstatus);
    }

    //作者审核通过
    @Override
    public boolean checkwriterpass(Integer userid) {
        return userMapper.checkwriterpass(userid);
    }

    //作者审核通过
    @Override
    public boolean checkwriterfail(Integer userid) {
        return userMapper.checkwriterfail(userid);
    }

    //根据用户账号查询用户信息
    public UserEntity queryUserByAccount(String useraccount){
        return userMapper.queryUserByAccount(useraccount);
    }

    //根据账号查询角色信息
    public Set<String> queryRoleByAccount(String useraccount){
        return userMapper.queryRoleByAccount(useraccount);
    }

    //查询所有角色
    public PageInfo<RoleEntity> listrole(RoleEntity roleEntity){
        PageHelper.startPage(roleEntity.getPageNum(),roleEntity.getPageSize());
        List<RoleEntity> list = userMapper.listrole(roleEntity);
        PageInfo<RoleEntity> roleEntityPageInfo = new PageInfo<>(list);
        return roleEntityPageInfo;
    }

    //修改用户展示所有角色
    public List<RoleEntity> listroleForUser(){
        return userMapper.listroleForUser();
    }

    //查询所有权限
    public List<AuthEntity> queryAuths(){
        return userMapper.queryAuths();
    }

    //添加角色信息
    public boolean addRole(RoleEntity roleEntity,String auths) {
        boolean addrole = userMapper.addRole(roleEntity);
        String[] arr = auths.split(",");
        for (String authid : arr) {
            addrole = userMapper.addAuthOfRole(roleEntity.getRid(), Integer.valueOf(authid));
        }
        return addrole;
    }

    //根据角色id查询角色信息
    public RoleEntity queryRoleById(String rid){
        RoleEntity roleEntity = userMapper.queryRoleById(rid);
        return roleEntity;
    }

    //修改权限信息
    public boolean updRole(RoleEntity roleEntity,String auths){
        boolean delResult = userMapper.delAuthOfRole(roleEntity.getRid());
        boolean updResult = userMapper.updRole(roleEntity);
        String[] arr = auths.split(",");
        boolean addAuthResult = false;
        for(String auth : arr){
            addAuthResult = userMapper.addAuthOfRole(roleEntity.getRid(),Integer.valueOf(auth));
        }

        return (delResult&&updResult&&addAuthResult);
    }

    //设置角色状态
    public boolean setStatusOfRole(Integer rid,Integer rstatus){
        return userMapper.setStatusOfRole(rid,rstatus);
    }

    //设置多个角色状态
    public boolean setRStatusByIds(String rids,Integer rstatus){
        return userMapper.setRStatusByIds(rids,rstatus);
    }

}

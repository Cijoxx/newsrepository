package com.yjls.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yjls.entity.RoleEntity;
import com.yjls.entity.UserEntity;
import com.yjls.mapper.UserMapper;
import com.yjls.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        PageInfo<UserEntity> userEntityPageInfo = new PageInfo<>(list);
        return userEntityPageInfo;
    }

    //查询所有角色
    public List<RoleEntity> listrole(RoleEntity roleEntity){
        return userMapper.listrole(roleEntity);
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

}

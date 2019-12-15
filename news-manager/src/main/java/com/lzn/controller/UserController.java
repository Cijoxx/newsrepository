package com.lzn.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yjls.entity.NewsFinal;
import com.yjls.entity.ResponseResult;
import com.yjls.entity.RoleEntity;
import com.yjls.entity.UserEntity;
import com.yjls.service.UserService;
import com.yjls.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class UserController {

    @Reference(check = false)
    UserService userService;

    //主页跳转
    @RequestMapping("managerindex")
    public String jumpManager(){
        return "managerindex";
    }

    //展示所有用户
    @RequestMapping("listuser")
    public String listuser(UserEntity userEntity, HttpServletRequest request){
        request.getSession().setAttribute("userPageInfo",userService.listuser(userEntity));
        if(StringUtil.isEmpty(userEntity.getUseraccount())){
            request.setAttribute("seuseraccount","");
        }else{
            request.setAttribute("seuseraccount",userEntity.getUseraccount());
        }
        if(StringUtil.isEmpty(userEntity.getUsername())){
            request.setAttribute("seusername","");
        }else{
            request.setAttribute("seusername",userEntity.getUsername());
        }
        return "listuser";
    }

    //跳转到用户详情
    @RequestMapping("getwriterdetail")
    public String getwriterdetail(UserEntity userEntity,HttpServletRequest request){
       UserEntity userlist2 = userService.getwriterdetail(userEntity);
        request.getSession().setAttribute("userlist2",userlist2);
        return "getwriterdetail";
    }

    //跳转到添加用户
    @RequestMapping("jumpAddUser")
    public String jumpAddUser(RoleEntity roleEntity, HttpServletRequest request){
        List<RoleEntity> list = userService.listroleForUser();
        request.setAttribute("listrole",list);
        return "adduser";
    }

    //添加用户
    @RequestMapping("addUser")
    @ResponseBody
    public ResponseResult addUser(UserEntity userEntity){
        //创建返回值对象
        ResponseResult responseResult = new ResponseResult();

        if(null == userEntity.getUseraccount()){
            responseResult.setResult(NewsFinal.FILED_RESULT);
            responseResult.setResultMsg("账号不能为空！");
            return responseResult;
        }

        if(null == userEntity.getUserpass()){
            responseResult.setResult(NewsFinal.FILED_RESULT);
            responseResult.setResultMsg("密码不能为空！");
            return responseResult;
        }

        userEntity.setUserpass(StringUtil.getMD5Transfer(userEntity.getUserpass()));
        boolean result = userService.registUser(userEntity);

        if(result){
            responseResult.setResult(NewsFinal.SUCCESS_RESULT);
            responseResult.setResultMsg("添加用户成功！");
        }else{
            responseResult.setResult(NewsFinal.FILED_RESULT);
            responseResult.setResultMsg("添加失败，请重新添加！");
        }
        return responseResult;
    }

    //根据用户id设置用户状态
    @RequestMapping("setStatusOfUser")
    public String setStatusOfUser(Integer userid,Integer userstatus){
        boolean delResult = userService.setStatusOfUser(userid,userstatus);
        return  String.valueOf(delResult);
    }

    //根据用户id设置多个用户状态
    @RequestMapping("setAStatusByIds")
    public String setAStatusByIds(String ids,Integer userstatus){
        boolean delResult = userService.setAStatusByIds(ids,userstatus);
        return  String.valueOf(delResult);
    }

    //跳转到修改用户信息
    @RequestMapping("jumpUpdUser")
    public String jumpUpdUser(RoleEntity roleEntity,Integer userid,HttpServletRequest request){
        List<RoleEntity> list = userService.listroleForUser();
        request.setAttribute("listrole",list);
        UserEntity userEntity = userService.queryUserById(userid);
        request.setAttribute("userEntity",userEntity);
        return "upduser";
    }

    @RequestMapping("updUser")
    @ResponseBody
    public ResponseResult updUser(UserEntity userEntity){
        //创建返回值对象
        ResponseResult responseResult = new ResponseResult();

        if(!StringUtil.isEmpty(userEntity.getUserpass())){
            userEntity.setUserpass(StringUtil.getMD5Transfer(userEntity.getUserpass()));
        }

        boolean result = userService.updUser(userEntity);
        if(result){
            responseResult.setResult(NewsFinal.SUCCESS_RESULT);
            responseResult.setResultMsg("修改用户信息成功！");
        }else{
            responseResult.setResult(NewsFinal.FILED_RESULT);
            responseResult.setResultMsg("修改失败，请重新修改！");
        }
        return responseResult;
    }

    //跳转到后台用户登录
    @RequestMapping("jumpBackLogin")
    public String jumpLogin(){
        return "backLogin";
    }

    //跳转到后台用户登录
    @RequestMapping("/")
    public String jumpLoginback(){
        return "backLogin";
    }

    //后台用户登录
    @RequestMapping("backlogin")
    @ResponseBody
    public ResponseResult backlogin(UserEntity userEntity,HttpServletRequest request){
        ResponseResult responseResult = new ResponseResult();
        //如果没有接收到有效的登录信息，则返回登录失败消息
        if(null == userEntity){
            responseResult.setResult(NewsFinal.FILED_RESULT);
            responseResult.setResultMsg("登录信息异常，请重新登录！");
            responseResult.setResultUrl("/");
            return responseResult;
        }else{
            if("123".equals(userEntity.getUseraccount())){
                userEntity.setUserpass(StringUtil.getMD5Transfer(userEntity.getUserpass()));
                UserEntity userEntity1 = userService.loginUser(userEntity);
                if(null != userEntity1){
                    //如果查询到登录信息，则填充成功登录消息
                    request.getSession().setAttribute("adminEntity",userEntity1);
                    responseResult.setResult(NewsFinal.SUCCESS_RESULT);
                    responseResult.setResultMsg("登陆成功，前往主页！");
                    responseResult.setResultUrl("managerindex");
                }else{
                    //如果没有查到登录信息，则填充登录失败返回消息
                    responseResult.setResult(NewsFinal.FILED_RESULT);
                    responseResult.setResultMsg("账号或密码不正确，请重新登录！");
                    responseResult.setResultUrl("jumpBackLogin");
                }
            }else{
                //没有权限
                responseResult.setResult(NewsFinal.FILED_RESULT);
                responseResult.setResultMsg("您没有权限！");
                responseResult.setResultUrl("jumpBackLogin");
            }
            return responseResult;
        }
    }

    //用户注销
    @RequestMapping("backLogout")
    @ResponseBody
    public ResponseResult backLogout(UserEntity userEntity,HttpServletRequest request){
        ResponseResult responseResult = new ResponseResult();
        UserEntity userEntity1 = (UserEntity) request.getSession().getAttribute("userEntity");
        if(userEntity.getUserid()==userEntity1.getUserid()){
            request.getSession().removeAttribute("userEntity");
            responseResult.setResult(NewsFinal.SUCCESS_RESULT);
            responseResult.setResultMsg("注销成功，返回登录界面！");
        }else{
            responseResult.setResult(NewsFinal.FILED_RESULT);
            responseResult.setResultMsg("注销失败，请稍后再试！");
        }
        return responseResult;
    }

    //作者审核通过
    @RequestMapping("checkwriterpass")
    public String checkwriterpass(Integer userid){
        boolean b = userService.checkwriterpass(userid);
        if(b){

        }
        return "redirect:listuser";
    }

    //作者审核通过
    @RequestMapping("checkwriterfail")
    public String checkwriterfail(Integer userid){
        boolean b = userService.checkwriterfail(userid);
        if(b){

        }
        return "redirect:listuser";
    }

}

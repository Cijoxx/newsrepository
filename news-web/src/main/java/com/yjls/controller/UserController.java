package com.yjls.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.yjls.entity.NewsFinal;
import com.yjls.entity.ResponseResult;
import com.yjls.entity.UserEntity;
import com.yjls.service.UserService;
import com.yjls.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

@Controller
public class UserController {

    @Reference(check=false)
    UserService userService;

    //跳转到用户登录
    @RequestMapping("jumpLogin")
    public ModelAndView jumpLogin(ResponseResult responseResult,HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        modelAndView.addObject("referrerUrl",responseResult.getResultUrl());
        return modelAndView;
    }

    @RequestMapping("/")
    public ModelAndView jumpLoginBack(ResponseResult responseResult,HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        modelAndView.addObject("referrerUrl",responseResult.getResultUrl());
        return modelAndView;
    }

    //用户登录
    @RequestMapping("login")
    @ResponseBody
    public ResponseResult login(UserEntity userEntity ,ResponseResult responseResult, HttpServletRequest request){
        //如果没有接收到有效的登录信息，则返回登录失败消息
        if(null == userEntity){
            responseResult.setResult(NewsFinal.FILED_RESULT);
            responseResult.setResultMsg("登录信息异常，请重新登录！");
            responseResult.setResultUrl("jumpLogin");
            return responseResult;
        }else{
            userEntity.setUserpass(StringUtil.getMD5Transfer(userEntity.getUserpass()));
            UserEntity userEntity1 = userService.loginUser(userEntity);
            if(null != userEntity1){
                //如果查询到登录信息，则填充成功登录消息
                request.getSession().setAttribute("userEntity",userEntity1);
                responseResult.setResult(NewsFinal.SUCCESS_RESULT);
                String url = responseResult.getResultUrl();
                if(StringUtil.isEmpty(url) || "jumpIndex".equals(responseResult.getResultUrl())){
                    responseResult.setResultMsg("登录成功，前往首页！");
                    responseResult.setResultUrl("jumpIndex");
                }else{
                    responseResult.setResultMsg("登录成功，返回前页！");
                    responseResult.setResultUrl(url);
                }
            }else{
                //如果没有查到登录信息，则填充登录失败返回消息
                responseResult.setResult(NewsFinal.FILED_RESULT);
                responseResult.setResultMsg("账号或密码不正确，请重新登录！");
                responseResult.setResultUrl("jumpLogin");
            }
            return responseResult;
        }
    }


    @RequestMapping("jumpregist")
    public String jumpRegist(){ return "regist"; }

    //用户账户唯一性检查
    @RequestMapping("uniqueCheck")
    @ResponseBody
    public ResponseResult uniqueCheck(String useraccount){
        ResponseResult responseResult = new ResponseResult();
        if(StringUtil.isEmpty(useraccount)){
            //传入的用户账户信息异常
            responseResult.setResult(NewsFinal.Not_Unique_Value);
            responseResult.setResultMsg("账户信息异常！请重新填写");
            return responseResult;
        }else{
            boolean result = userService.uniqueCheck(useraccount);
            if(result){
                //用户账户已存在，不予注册
                responseResult.setResult(NewsFinal.Not_Unique_Value);
                responseResult.setResultMsg("该账户已存在！");
            }else{
                //用户账户可用
                responseResult.setResult(NewsFinal.Unique_Value);
                responseResult.setResultMsg("账户可用！");
            }
            return responseResult;
        }
    }
    //用户注册
    @RequestMapping("regist")
    @ResponseBody
    public ResponseResult userRegist(UserEntity userEntity, HttpServletRequest request){
        //创建返回值对象
        ResponseResult responseResult = new ResponseResult();

        if(null == userEntity.getUseraccount()){
            responseResult.setResult(NewsFinal.FILED_RESULT);
            responseResult.setResultMsg("账号不能为空！");
            responseResult.setResultUrl("jumpregist");
            return responseResult;
        }

        if(null == userEntity.getUserpass()){
            responseResult.setResult(NewsFinal.FILED_RESULT);
            responseResult.setResultMsg("密码不能为空！");
            responseResult.setResultUrl("jumpregist");
            return responseResult;
        }

        userEntity.setUserpass(StringUtil.getMD5Transfer(userEntity.getUserpass()));
        userEntity.setUserrole(1);
        boolean result = userService.registUser(userEntity);

        if(result){
            responseResult.setResult(NewsFinal.SUCCESS_RESULT);
            responseResult.setResultMsg("注册成功，请登录！");
            responseResult.setResultUrl("jumpLogin");
        }else{
            responseResult.setResult(NewsFinal.FILED_RESULT);
            responseResult.setResultMsg("注册失败，请重新注册！");
            responseResult.setResultUrl("jumpregist");
        }
        return responseResult;
    }

    //带头像
    @RequestMapping("registWithIcon")
    @ResponseBody
    public ResponseResult userRegistWithIcon(@RequestParam("headImage") MultipartFile icon , UserEntity userEntity, HttpServletRequest request){
        //创建返回值对象
        ResponseResult responseResult = new ResponseResult();

        if(null == userEntity.getUseraccount()){
            responseResult.setResult(NewsFinal.FILED_RESULT);
            responseResult.setResultMsg("账号不能为空！");
            responseResult.setResultUrl("jumpregist");
            return responseResult;
        }

        if(null == userEntity.getUserpass()){
            responseResult.setResult(NewsFinal.FILED_RESULT);
            responseResult.setResultMsg("密码不能为空！");
            responseResult.setResultUrl("jumpregist");
            return responseResult;
        }

        //获取头像名
        String filename = StringUtil.getUUID().substring(0,9)+icon.getOriginalFilename();
        if(null == icon || StringUtil.isEmpty(filename)){
            responseResult.setResult(NewsFinal.FILED_RESULT);
            responseResult.setResultMsg("请设置头像！");
            return responseResult;
        }

        //用户密码MD5加密
        userEntity.setUserpass(StringUtil.getMD5Transfer(userEntity.getUserpass()));
        //保存用户头像路径信息
        userEntity.setUserphoto("userimages"+ File.separator+filename);
        //进行用户信息的添加
        boolean result = userService.registUser(userEntity);
        if(result){
            String filePath ="static"+File.separator+"userimages";
            File file = new File(filePath);
            if(!file.exists()){
                file.mkdirs();
            }
            try {
                String path = filePath + File.separator + filename;
                InputStream is = icon.getInputStream();
                FileOutputStream fos = new FileOutputStream(path);
                FileCopyUtils.copy(is,fos);
            } catch (IOException e) {
                e.printStackTrace();
            }

            responseResult.setResult(NewsFinal.SUCCESS_RESULT);
            responseResult.setResultMsg("注册成功，请登录！");
            responseResult.setResultUrl("jumpLogin");
        }else{
            responseResult.setResult(NewsFinal.FILED_RESULT);
            responseResult.setResultMsg("注册失败，请重新注册！");
            responseResult.setResultUrl("jumpregist");
        }
        return responseResult;
    }

    //登出
    @RequestMapping("logout")
    public String logout(HttpServletRequest request){
       request.getSession().removeAttribute("userEntity");
       return "login";
    }

    /**
     * 跳转到个人信息页面
     * @return
     */
    @RequestMapping("jumpuserinfo")
    public String jumpuserinfo(){
        return "userinfo";
    }

    /**
     * 修改个人信息
     * @param userEntity
     * @return
     */
    @RequestMapping("edituser")
    public ModelAndView edituser(UserEntity userEntity, HttpServletRequest request, MultipartFile image){
        ModelAndView mv=new ModelAndView();
        UserEntity user= (UserEntity) request.getSession().getAttribute("userEntity");
        userEntity.setUserid(user.getUserid());
        if("".equals(userEntity.getUserpass())){
            userEntity.setUserpass(user.getUserpass());
        }else{
            userEntity.setUserpass(StringUtil.getMD5Transfer(userEntity.getUserpass()));
        }
        //上传头像
        //获取文件名时用uuid避免重复
        String fileName = StringUtil.getUUID().substring(0,6) + image.getOriginalFilename();

        //获取当前项目在tomcat中的位置
        String filepath = "static/userimages";

       File file = new File(filepath);
        //如果路径不存在就创建路径
        if(!file.exists()) {
            file.mkdirs();
        }

        //文件存放路径
        String path =filepath +File.separator+ fileName;
        userEntity.setUserphoto("userimages"+File.separator+fileName);
        try (FileOutputStream fos = new FileOutputStream(path);
             InputStream is = image.getInputStream()) {

            FileCopyUtils.copy(is,fos);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        boolean result = userService.edituser(userEntity);
        if(result){
            mv.setViewName("login");
            return mv;
        }else{
            mv.addObject("修改个人信息失败");
            mv.setViewName("userinfo");
        }
        return mv;
    }

    //跳转到申请作者页面
    @RequestMapping("jumpapplywriter")
    public String jumpapplywriter(){
        return "applywriter";
    }

    /**
     * 申请成为作者
     * @param userEntity
     * @param request
     * @return
     */
    @RequestMapping("applywriter")
    public ModelAndView applywriter(UserEntity userEntity, HttpServletRequest request){
        ModelAndView mv = new ModelAndView();
        UserEntity user= (UserEntity) request.getSession().getAttribute("userEntity");
        userEntity.setUserid(user.getUserid());
        if("".equals(userEntity.getApplyrealname())){
            mv.setViewName("applywriter");
            mv.addObject("error","您的真实姓名不能为空");
            return mv;
        }
        if("".equals(userEntity.getApplyidcard())){
            mv.setViewName("applywriter");
            mv.addObject("error","您的身份证号不能为空");
            return mv;
        }
        if("".equals(userEntity.getApplyreason())){
            mv.setViewName("applywriter");
            mv.addObject("error","请输入您要成为作者的理由");
            return mv;
        }
        System.out.println(userEntity);
        boolean result = userService.applywriter(userEntity);
        if(result){
            mv.setViewName("redirect:jumpIndex");
            return mv;
        }else{
            mv.setViewName("applywriter");
        }
        return mv;
    }
}



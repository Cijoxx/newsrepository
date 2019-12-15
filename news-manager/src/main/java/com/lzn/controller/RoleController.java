package com.lzn.controller;

import com.alibaba.dubbo.common.json.JSON;
import com.alibaba.dubbo.config.annotation.Reference;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.github.pagehelper.PageInfo;
import com.yjls.entity.AuthEntity;
import com.yjls.entity.NewsFinal;
import com.yjls.entity.ResponseResult;
import com.yjls.entity.RoleEntity;
import com.yjls.service.UserService;
import com.yjls.util.StringUtil;
import jdk.nashorn.internal.parser.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class RoleController {

    @Reference(check=false)
    UserService userService;

    //展示所有用户
    @RequestMapping("listrole")
    public String listuser(RoleEntity roleEntity, HttpServletRequest request){
        request.setAttribute("pageInfo",userService.listrole(roleEntity));
        if(null == roleEntity.getRid() || 0 == roleEntity.getRid()){
            request.setAttribute("serid",0);
        }else{
            request.setAttribute("serid",roleEntity.getRid());
        }
        if(StringUtil.isEmpty(roleEntity.getRname())){
            request.setAttribute("sername","");
        }else{
            request.setAttribute("sername",roleEntity.getRname());
        }
        return "listrole";
    }

    //跳转到添加角色
    @RequestMapping("jumpAddRole")
    public String jumpAddRole(){
        return "addrole";
    }

    //添加角色信息
    @RequestMapping("addRole")
    @ResponseBody
    public ResponseResult addRole(RoleEntity roleEntity,String auths){
        ResponseResult responseResult = new ResponseResult();
        boolean result = userService.addRole(roleEntity,auths);
        if(result){
            responseResult.setResult(NewsFinal.SUCCESS_RESULT);
        }else{
            responseResult.setResult(NewsFinal.FILED_RESULT);
        }
        return responseResult;
    }

    //查询所有权限
    @RequestMapping("queryAuths")
    @ResponseBody
    public List<AuthEntity> queryAuths(){
        List<AuthEntity> list = userService.queryAuths();
        return list;
    }

    //跳转到修改角色信息
    @RequestMapping("jumpUpdRole")
    public String jumpUpdRole(String rid , HttpServletRequest request){
        RoleEntity roleEntity = userService.queryRoleById(rid);
        //用户具有的权限
        List<AuthEntity> authPartList = roleEntity.getAuthlist();
        //所有权限
        List<AuthEntity> authAllList = userService.queryAuths();

        //保存当前用户已有的权限
        StringBuilder sb = new StringBuilder();

        //确定当前角色有哪些权限，然后勾选
        for (AuthEntity auth : authAllList) {//所有的权限

            for (AuthEntity au : authPartList) {//用户拥有的权限
                if (auth.getAuid() == au.getAuid()) {//说明用户有这个权限
                    sb.append(auth.getAuid()).append(",");
                    auth.setChecked(true);
                    break;
                }
            }
        }

        //1:使用工具类转成json串
        ObjectMapper om = new ObjectMapper();
        try {
            String str = om.writeValueAsString(authAllList);
            request.setAttribute("str", str);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        if (authPartList != null & authPartList.size() > 0) {
            request.setAttribute("partAuths", sb.substring(0, sb.length() - 1));
        }
        request.setAttribute("roleEntity", roleEntity);
        request.setAttribute("auths", authAllList);
        return "updrole";
    }

    //修改角色信息
    @RequestMapping("updRole")
    @ResponseBody
    public ResponseResult updRole(RoleEntity roleEntity,String auths){
        ResponseResult responseResult = new ResponseResult();
        boolean result = userService.updRole(roleEntity,auths);
        if(result){
            responseResult.setResult(NewsFinal.SUCCESS_RESULT);
        }else{
            responseResult.setResult(NewsFinal.FILED_RESULT);
        }
        return responseResult;
    }

    //根据用户id设置角色状态
    @RequestMapping("setStatusOfRole")
    @ResponseBody
    public String setStatusOfRole(Integer rid,Integer rstatus){
        boolean delResult = userService.setStatusOfRole(rid,rstatus);
        return  String.valueOf(delResult);
    }

    //根据用户id设置多个角色状态
    @RequestMapping("setRStatusByIds")
    @ResponseBody
    public String setRStatusByIds(String ids,Integer rstatus){
        boolean delResult = userService.setRStatusByIds(ids,rstatus);
        return  String.valueOf(delResult);
    }
}

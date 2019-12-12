package com.lzn.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.yjls.entity.CommentEntity;
import com.yjls.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CommentController {

    @Reference(check=false)
    CommentService commentService;

    //评论列表展示
    @RequestMapping("adminSeeNeesAndCon")
    public String adminSeeNewsAndCon (HttpServletRequest request, CommentEntity commentEntity){
        PageInfo<CommentEntity> pageInfo = commentService.adminSeeNewsandCon(commentEntity);
        request.getSession().setAttribute("pageInfo2",pageInfo);
        return "listcomment";
    }

    /*删除一条用户评论*/
    @RequestMapping("delcommentByCid")
    @ResponseBody
    public String delshow(int commentid){
        boolean delcomment = commentService.delcomment(commentid);
        if(delcomment){
            return "0000";
        }
        return "l111";
    }
    @RequestMapping("listcomment")
    public String jumplistuser(){
        return "redirect:adminSeeNeesAndCon";
    }

    //删除多条信息
    @RequestMapping("delmanycomment")
    @ResponseBody
    public  String delmanycomment(String ids){
        boolean delmanycomment = commentService.delmanycomment(ids);
        if(delmanycomment){
            return "0000";
        }
        return "1111";
    }


}

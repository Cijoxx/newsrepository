package com.yjls.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yjls.entity.CommentEntity;
import com.yjls.entity.NewsFinal;
import com.yjls.entity.ResponseResult;
import com.yjls.entity.UserEntity;
import com.yjls.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CommentController {

    @Reference(check=false)
    CommentService commentService;

    //添加新闻
    @RequestMapping("addComment")
    @ResponseBody
    public ResponseResult addComment(CommentEntity commentEntity,HttpServletRequest request){
        ResponseResult responseResult = new ResponseResult();
        UserEntity userEntity = (UserEntity) request.getSession().getAttribute("userEntity");
        if(null == userEntity){
            responseResult.setResult(NewsFinal.Login_ERROR);
            responseResult.setResultMsg("登录信息异常，是否重新登录！");
            return responseResult;
        }else{
            boolean result = commentService.addComment(commentEntity);
            if(result){
                responseResult.setResult(NewsFinal.SUCCESS_RESULT);
                responseResult.setResultMsg("评论添加成功！");
                responseResult.setResultUrl("jumpNews?showid="+commentEntity.getShowid());
            }else{
                responseResult.setResult(NewsFinal.FILED_RESULT);
                responseResult.setResultMsg("评论添加失败");
            }
            return responseResult;
        }
    }
}

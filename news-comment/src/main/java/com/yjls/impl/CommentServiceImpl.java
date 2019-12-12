package com.yjls.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yjls.entity.CommentEntity;
import com.yjls.mapper.CommentMapper;
import com.yjls.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@com.alibaba.dubbo.config.annotation.Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    CommentMapper commentMapper;

    //根据新闻id查询评论
    public List<CommentEntity> queryCommentByNewsId(Integer showid){
        return commentMapper.queryCommentByNewsId(showid);
    }

    //用户添加评论
    public boolean addComment(CommentEntity commentEntity){
        return commentMapper.addComment(commentEntity);
    }

    //管理员查看新闻及评论
    public PageInfo<CommentEntity> adminSeeNewsandCon(CommentEntity commentEntity){
        PageHelper.startPage(commentEntity.getPageNum(),commentEntity.getPageSize());
        List<CommentEntity> list = commentMapper.adminSeeNewsandCon();
        PageInfo<CommentEntity> commentEntityPageInfo=new PageInfo<CommentEntity>(list);
        return commentEntityPageInfo;
    }

    //删除评论
    @Override
    public boolean delcomment(int commentid) {
        return commentMapper.delcomment(commentid);
    }

    //批量删除评论
    @Override
    public boolean delmanycomment(String ids) {
        String str="("+ids.substring(0,ids.length()-1)+")";
        return commentMapper.delmanycomment(str);
    }


}

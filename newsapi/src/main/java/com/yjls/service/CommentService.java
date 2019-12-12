package com.yjls.service;

import com.github.pagehelper.PageInfo;
import com.yjls.entity.CommentEntity;

import java.util.List;

public interface CommentService {
    //根据新闻id查询评论
    public List<CommentEntity> queryCommentByNewsId(Integer showid);

    //用户添加品论
    public boolean addComment(CommentEntity commentEntity);

    //管理员查看新闻及评论
    public PageInfo<CommentEntity> adminSeeNewsandCon(CommentEntity commentEntity);

    //删除一条新闻评论
    public  boolean  delcomment(int commentid);

    //批量删除新闻评论
    public  boolean delmanycomment(String ids);
}

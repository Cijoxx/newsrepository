package com.yjls.controller;

import com.alibaba.dubbo.config.annotation.Reference;

import com.yjls.entity.CommentEntity;
import com.yjls.entity.NewsEntity;
import com.yjls.entity.NewsFinal;
import com.yjls.entity.ResponseResult;
import com.yjls.service.CommentService;
import com.yjls.service.NewsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class NewsController {

    @Reference(check=false)
    NewsService newsService;

    @Reference(check=false)
    CommentService commentService;

    //跳转到首页
    @RequestMapping("jumpIndex")
    public String jumpIndex(NewsEntity newsEntity, HttpServletRequest request){
        //按热度展示新闻
        List<NewsEntity> list = newsService.listbyviews();
        request.getSession().setAttribute("list",list);
        //展示所有新闻
        List<NewsEntity> list2 = newsService.listNews(newsEntity);
        request.getSession().setAttribute("list2",list2);
        //展示最新四条新闻
        List<NewsEntity> listNewest = newsService.listbytime(NewsFinal.NUM_FOUR);
        request.getSession().setAttribute("newestList",listNewest);
        return "index";
    }

    //搜索功能
    @RequestMapping("searchNews")
    public String searchNews(NewsEntity newsEntity,HttpServletRequest request){
        List<NewsEntity> list = newsService.listNews(newsEntity);
        //将pageInfo放入session中
        request.getSession().setAttribute("newslist",list);
        return "searchresult";
    }

    //点击类型跳转到不同的新闻页面
    @RequestMapping("category")
    public String category(NewsEntity newsEntity,HttpServletRequest request){
         List<NewsEntity> list3 = newsService.listNews(newsEntity);
         request.getSession().setAttribute("list3",list3);
         return "category";
    }

    //展示新闻详情
    @RequestMapping("jumpNews")
    public String jumpNews(Integer showid,HttpServletRequest request){
        //根据新闻id获取新闻详情
        NewsEntity newsEntity = newsService.searchNewsById(showid);
        //每次查看新闻详情使新闻浏览量加1
        boolean b = newsService.views(showid);
        request.getSession().setAttribute("newsEntity",newsEntity);
        //展示最新八条条新闻
        List<NewsEntity> listNewest = newsService.listbytime(NewsFinal.NUM_EIGHT);
        request.getSession().setAttribute("newestListOfCategory",listNewest);

        //查询用户评论
        List<CommentEntity> commentlist = commentService.queryCommentByNewsId(showid);
        request.setAttribute("commentlist",commentlist);
        return "news";
    }

    /**
     * 新闻点赞
     * @param showid
     * @return
     */
    @RequestMapping("givegood")
    @ResponseBody
    public ResponseResult givegood(Integer showid){
        ResponseResult rr = new ResponseResult();
        boolean result = newsService.givegood(showid);
        if(result){
            rr.setResult(NewsFinal.SUCCESS_RESULT);
        }
        return rr;
    }

    //页面菜单栏
    @RequestMapping("headermenu")
    public String jumpHeadermenu(){
        return "headermenu";
    }
}

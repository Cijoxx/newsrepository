package com.yjls.controller;

import com.alibaba.dubbo.config.annotation.Reference;

import com.yjls.entity.*;
import com.yjls.service.CommentService;
import com.yjls.service.NewsService;
import com.yjls.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
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
        List<NewsEntity> listNewest = newsService.listbytime(6

        );
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

    @RequestMapping("jumpaddNews")
    public String jumpaddNews(){
        return "addNews";
    }

    /*
     * 作者添加新闻
     * */
    @RequestMapping("addNews")
    public String addNews (NewsEntity newsEntity, HttpServletRequest request, MultipartFile image2) throws FileNotFoundException {
        //上传头像
        //获取文件名时用uuid避免重复
        String fileName = StringUtil.getUUID().substring(0,6) + image2.getOriginalFilename();
        //获取当前项目在tomcat中的位置
        String filepath = "static/newsimages";
        File file = new File(filepath);
        //如果路径不存在就创建路径
        if(!file.exists()) {
            file.mkdirs();
        }

        //文件存放路径
        String path =filepath +File.separator+ fileName;
        newsEntity.setShowpicture("newsimages"+File.separator+fileName);
        try (FileOutputStream fos = new FileOutputStream(path);
             InputStream is = image2.getInputStream()) {
            FileCopyUtils.copy(is,fos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        UserEntity userEntity2 = (UserEntity) request.getSession().getAttribute("userEntity");
        newsEntity.setUserid(userEntity2.getUserid());
        newsEntity.setShowviews(0);
        newsEntity.setShowcomment(0);
        newsEntity.setShowgivegood(0);
        /*newsEntity.setShowtype(1);*/
        boolean b = newsService.addNews(newsEntity);
        if (b){
            return"index";
        }
        return "addNews"; //重新添加
    }
}

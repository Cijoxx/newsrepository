package com.lzn.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yjls.entity.NewsEntity;
import com.yjls.entity.NewsFinal;
import com.yjls.entity.ResponseResult;
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

    /**
     * 管理员查看新闻
     * @param request
     * @return
     */
    @RequestMapping("getnews")
    public String getnews(NewsEntity newsEntity,HttpServletRequest request){
        PageInfo<NewsEntity> newslist= newsService.getnews(newsEntity);
        request.getSession().setAttribute("newslist",newslist);
        return "getnews";
    }

    /**
     * 管理员查看新闻详情
     * @param showid
     * @return
     */
    @RequestMapping("getnewsdetail")
    public String getnewsdetail(Integer showid,HttpServletRequest request){
        NewsEntity newsdetail = newsService.getnewsdetail(showid);
        request.getSession().setAttribute("newsdetail",newsdetail);
        return "getnewsdetail";
    }

    /**
     * 管理员根据新闻id删除新闻
     * @param showid
     * @return
     */
    @RequestMapping("delnews")
    @ResponseBody
    public ResponseResult delnews(Integer showid){
        ResponseResult rr=new ResponseResult();
        boolean result=newsService.delnews(showid);
        if(result){
            rr.setResult(NewsFinal.SUCCESS_RESULT);
        }else{
            rr.setResult(NewsFinal.FILED_RESULT);
            rr.setResultMsg("删除失败");
        }
        return rr;
    }

    /**
     * 管理员根据id批量删除新闻
     * @param ids
     * @return
     */
    @RequestMapping("delnewsbyids")
    @ResponseBody
    public ResponseResult delnewsbyids(String ids){
        ResponseResult rr=new ResponseResult();
        boolean result = newsService.delnewsbyids(ids);
        if(result){
            rr.setResult(NewsFinal.SUCCESS_RESULT);
        }else{
            rr.setResult(NewsFinal.FILED_RESULT);
            rr.setResultMsg("删除失败");
        }
        return rr;
    }

    /**
     * 管理员审核新闻通过
     * @param showid
     * @return
     */
    @RequestMapping("checknewspass")
    public String checknewspass(Integer showid){
        boolean result=newsService.checknewspass(showid);
        if(result){
            return "redirect:getnews";
        }else{
            return "false";
        }
    }

    @RequestMapping("checknewsfail")
    public String checknewsfail(Integer showid){
        boolean result = newsService.checknewsfail(showid);
        if(result){
            return "redirect:getnews";
        }else{
            return "fail";
        }
    }

    /**
     * x新闻恢复
     * @param showid
     * @return
     */
    @RequestMapping("makenewslive")
    @ResponseBody
    public ResponseResult makenewslive(Integer showid){
        ResponseResult rr = new ResponseResult();
        boolean b = newsService.makenewslive(showid);
        if(b){
            rr.setResult(NewsFinal.SUCCESS_RESULT);
        }else{
            rr.setResult(NewsFinal.FILED_RESULT);
            rr.setResultMsg("删除失败");
        }
        return rr;
    }
}

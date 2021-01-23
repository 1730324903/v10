package cn.tedu.controller;

import cn.tedu.dao.BannerDao;
import cn.tedu.entity.Banner;
import cn.tedu.entity.User;
import cn.tedu.utils.ThUtils;
import org.thymeleaf.context.Context;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ShowBannerServlet",urlPatterns = "/showbanner")
public class ShowBannerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //判断是否登录
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(user==null){//满足条件说明没有登录
            //重定向到登录页面
            response.sendRedirect("/showlogin");
            return;//结束方法 下面不执行
        }
        //查询所有轮播图
        BannerDao dao = new BannerDao();
        List<Banner> list = dao.findAll();
        //把数据和页面整合返回给客户端
        Context context = new Context();
        context.setVariable("list",list);
        ThUtils.print("banner.html",context,response);
    }
}

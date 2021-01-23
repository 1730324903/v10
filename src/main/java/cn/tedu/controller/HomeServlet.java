package cn.tedu.controller;

import cn.tedu.dao.BannerDao;
import cn.tedu.dao.CategoryDao;
import cn.tedu.dao.ProductDao;
import cn.tedu.entity.Banner;
import cn.tedu.entity.Category;
import cn.tedu.entity.Product;
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

@WebServlet(name = "HomeServlet",urlPatterns = "/home")
public class HomeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取传递过来的分类id
        String cid = request.getParameter("cid");
        //获取搜索关键字
        String keyword = request.getParameter("keyword");
        //显示首页页面
        Context context = new Context();
        //查询所有分类并添加到容器中
        CategoryDao dao = new CategoryDao();
        List<Category> list = dao.findAll();
        context.setVariable("list",list);
        //查询所有轮播图并添加到容器中
        BannerDao bDao = new BannerDao();
        List<Banner> bList = bDao.findAll();
        context.setVariable("bList",bList);

        //获取Session对象
        HttpSession session = request.getSession();
        //取出保存的用户对象
        User user = (User)session.getAttribute("user");
        //if(user==null){
        //    System.out.println("从来没登录过");
        //}else{
        //    System.out.println("已经登过");
        //}
        //把用户名对象装进容器中
        context.setVariable("user",user);

        //查询所有的作品并装进context容器
        ProductDao pDao = new ProductDao();
        if (cid!=null){//说明查询的是某个分类的作品
            //cid有值说明需要查询某个分类的所有作品
            List<Product> pList = pDao.findByCID(cid);
            context.setVariable("pList",pList);
        }else if(keyword!=null){
            List<Product> pList = pDao.findByKeyword(keyword);
            System.out.println("--------------"+pList);
            context.setVariable("pList",pList);
        }else{//查询的是所有作品
            List<Product> pList = pDao.findAll();
            context.setVariable("pList",pList);
        }
        //查询浏览最多
        List<Product> vList = pDao.findViewList();
        context.setVariable("vList",vList);
        //查询最受欢迎
        List<Product> lList = pDao.findLikeList();
        context.setVariable("lList",lList);

        ThUtils.print("home.html",context,response);

    }
}

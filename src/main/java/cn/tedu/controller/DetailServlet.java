package cn.tedu.controller;

import cn.tedu.dao.CategoryDao;
import cn.tedu.dao.ProductDao;
import cn.tedu.entity.Category;
import cn.tedu.entity.Product;
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

@WebServlet(name = "DetailServlet",urlPatterns = "/detail")
public class DetailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        System.out.println("详情id:"+id);
        //创建Dao并调用通过id查询详情的方法
        ProductDao dao = new ProductDao();

        HttpSession session = request.getSession();
        //
        String viewId = (String)session.getAttribute("view"+id);
        if(viewId==null){
            //让浏览量加1
            dao.viewById(id);
            session.setAttribute("view"+id,id);
        }

        Product product = dao.findById(id);
        //把查询到的内容装进容器中并显示
        Context context = new Context();
        context.setVariable("product",product);

        //添加分类信息
        CategoryDao cDao = new CategoryDao();
        List<Category> list = cDao.findAll();
        context.setVariable("list",list);
        //添加浏览最多和最受欢迎
        List<Product> vList = dao.findViewList();
        context.setVariable("vList",vList);
        List<Product> lList = dao.findLikeList();
        context.setVariable("lList",lList);
        //把登录的用户对象添加到容器中
        context.setVariable("user",request.getSession().getAttribute("user"));
        ThUtils.print("detail.html",context,response);
    }
}

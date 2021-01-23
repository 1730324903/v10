package cn.tedu.controller;

import cn.tedu.dao.ProductDao;
import cn.tedu.entity.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@WebServlet(name = "DeleteServlet",urlPatterns = "/delete")
public class DeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        ProductDao dao = new ProductDao();
        //得到作品的详细信息 从详细信息中得到作品图片的路径
        Product p = dao.findById(id);
        System.out.println(p.getUrl());
        //通过图片的相对路径的到绝对路径
        String path = request.getServletContext().getRealPath(p.getUrl());
        System.out.println(path);
        //删除路径对应文件
        new File(path).delete();

        dao.deleteById(id);
        //重定向首页
        response.sendRedirect("/home");
    }
}

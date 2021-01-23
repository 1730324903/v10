package cn.tedu.controller;

import cn.tedu.dao.BannerDao;
import cn.tedu.entity.Banner;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@MultipartConfig
@WebServlet(name = "SendBannerServlet",urlPatterns = "/sendbanner")
public class SendBannerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置字符集
        request.setCharacterEncoding("UTF-8");
        //获取上传文件夹
        Part filePart = request.getPart("file");
        System.out.println(filePart+"------------");
        //获取文件上传信息
        String info = filePart.getHeader("content-disposition");
        //获取文件后缀名
        String suffix = info.substring(info.lastIndexOf("."),info.length()-1);
        System.out.println(suffix);
        //得到唯一文件名
        String fileName = UUID.randomUUID()+suffix;
        //得到Tomcat管辖的images路径
        String part = request.getServletContext().getRealPath("images/");
        System.out.println(part+">>>>>>>>>>>>>>>>>>>>>>>>>>");
        filePart.write(part+fileName);
        Banner banner = new Banner(0,"images/"+fileName);
        System.out.println(banner+"++++++++++++++++++++");
        BannerDao dao = new BannerDao();
        dao.insert(banner);
        response.sendRedirect("/showbanner");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

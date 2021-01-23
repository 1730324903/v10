package cn.tedu.controller;

import cn.tedu.utils.ThUtils;
import org.thymeleaf.context.Context;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ShowLoginServlet",urlPatterns = "/showlogin")
public class ShowLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //显示登陆页面
        Context context = new Context();
        //取出所有的Cookie里面的内容
        Cookie[] cookie = request.getCookies();
        //非空判断
        if(cookie!=null){
            //遍历Cookie中保存的是否是用户名
           for(Cookie c : cookie){
                //判断Cookie中保存的是否是用户名
               if(c.getName().equals("username")){
                   String username = c.getValue();//取出cookie中的用户名
                   //把用户名保存到Context容器中
                   context.setVariable("username",username);
               }
               //判断cookie中是否是密码
               if(c.getName().equals("password")){
                   String password = c.getValue();
                   context.setVariable("password",password);
               }
            }
        }
        ThUtils.print("login.html",context,response);
    }
}

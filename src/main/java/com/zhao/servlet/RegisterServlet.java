package com.zhao.servlet;

import com.zhao.pojo.User;
import com.zhao.utils.Sendmail;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Time : 2022/8/9 15:27
 * @Author : 赵浩栋
 * @File : RegisterServlet.java
 * @Software: IntelliJ IDEA
 */
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String name = req.getParameter("name");
            String password = req.getParameter("password");
            String email = req.getParameter("email");
            User user = new User(name, password, email);

            //用户注册后给其发送邮件，使用线程来发送，防止出现耗时，和网站注册人数过多
            Sendmail send = new Sendmail(user);
            send.start();

            //注册用户
            req.setAttribute("message","注册成功，发送了一封邮件给你");
            req.getRequestDispatcher("info.jsp").forward(req,resp);
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("message","注册失败");
            req.getRequestDispatcher("index.jsp").forward(req,resp);
        }
    }
}

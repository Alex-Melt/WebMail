package com.zhao.utils;

import com.sun.mail.util.MailSSLSocketFactory;
import com.zhao.pojo.User;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @Time : 2022/8/9 15:40
 * @Author : 赵浩栋
 * @File : Sendmail.java
 * @Software: IntelliJ IDEA
 */
//网站三秒原则
public class Sendmail extends Thread{
    private String from ="2779495512@qq.com";
    private String username ="2779495512@qq.com";
    private String password ="pqwqezirlcaaddfc";
    private String host ="smtp.qq.com";

    private User user;
    public Sendmail(User user){
        this.user=user;
    }
    /**
     * If this thread was constructed using a separate
     * <code>Runnable</code> run object, then that
     * <code>Runnable</code> object's <code>run</code> method is called;
     * otherwise, this method does nothing and returns.
     * <p>
     * Subclasses of <code>Thread</code> should override this method.
     *
     * @see #start()
     * @see #stop()
     */
    @Override
    public void run() {
        try {
            //复杂文件，包含图片
            Properties prop = new Properties();
            prop.setProperty("mail.host","smtp.qq.com");//设置qq邮件服务器
            prop.setProperty("mail.transport.protocol","smtp");//邮件发送协议
            prop.setProperty("mail.smtp.auth","true");//验证用户名密码

            //===============qq还得设置ssl加密
            MailSSLSocketFactory sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            prop.put("mail.smtp.ssl.enable","true");
            prop.put("mail.smtp.ssl.socketFactory",sf);

            //使用JavaMail发送邮件五步骤

            //1.创建定义整个应用程序所欲要的换陷阱信息session,=============qq才有
            Session session = Session.getDefaultInstance(prop, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    //发件人邮件用户名，授权码
                    return new PasswordAuthentication("2779495512@qq.com", "授权码");
                }
            });

            //2.开启session的debug模式，查看邮件发送状态
            session.setDebug(true);

            //通过session得到transport对象
            Transport ts = session.getTransport();

            //3.使用用户的用户名，授权码链接
            ts.connect(host,username,password);

            //4.创建文件

            //创建邮件对象
            MimeMessage message = new MimeMessage(session);

            //指名邮件的发件人
            message.setFrom(new InternetAddress(from));

            //指名邮件的收件人
            message.setRecipient(Message.RecipientType.TO,new InternetAddress(user.getEmail()));

            //邮件的标题
            message.setSubject("用户注册邮件");

            ////准备图片
            //MimeBodyPart image = new MimeBodyPart();
            //DataHandler dh = new DataHandler(new FileDataSource("F:\\IDEA\\workspace\\邮件发送\\target\\classes\\1.png"));
            //image.setDataHandler(dh);
            //image.setContentID("1.png");
            //
            ////准备附件
            //MimeBodyPart bodyPart = new MimeBodyPart();
            //bodyPart.setDataHandler(new DataHandler(new FileDataSource("F:\\IDEA\\workspace\\邮件发送\\target\\classes\\db.properties")));
            //bodyPart.setFileName("db.properties");
            //
            //
            ////邮件的内容
            //MimeBodyPart text = new MimeBodyPart();
            //text.setContent("这不只是一封邮件带图片<img src='cid:1.png'>邮件","text/html;charset=utf-8");
            //
            ////描述数据关系
            //MimeMultipart mm = new MimeMultipart();
            //mm.addBodyPart(text);
            //mm.addBodyPart(image);
            //mm.setSubType("related");
            //
            ////将这两个拼接的设置为主体
            //MimeBodyPart contentText = new MimeBodyPart();
            //contentText.setContent(mm);
            //
            ////拼接附件
            //MimeMultipart allFile = new MimeMultipart();
            //allFile.addBodyPart(bodyPart);
            //allFile.addBodyPart(contentText);
            //mm.setSubType("mixed");
            //
            String info="恭喜您注册成功，您的用户名："+user.getName()+",您的密码："+user.getPassword()+",请妥善保管";
            message.setContent(info,"text/html;charset=utf-8");
            message.saveChanges();

            //5.发送邮件
            ts.sendMessage(message,message.getAllRecipients());

            //6.关闭连接
            ts.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}

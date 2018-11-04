package jwt;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

import tool.GetProperty;

import javax.activation.*;
public class email {

	public static void main(String[] args) throws AddressException, MessagingException {
	    //读取配置文件
	    Properties properties = new GetProperty().getPropertyByFileName("config","email.properties");
	    
	    System.out.println(properties);
	    
	    // 收件人电子邮箱
	    String to = "2842513372@qq.com";

	    // 发件人电子邮箱
	    String from = properties.getProperty("from");

	    // 指定发送邮件的主机为 smtp.qq.com
	    String host = properties.getProperty("host");  //指定邮箱服务器
	    
	    // 设置邮件服务器
	    properties.setProperty("mail.smtp.host", host);

	    properties.put("mail.smtp.auth", "true");
	    // 获取默认session对象
	    Session session = Session.getDefaultInstance(properties,new Authenticator(){
	      public PasswordAuthentication getPasswordAuthentication()
	      {
	       return new PasswordAuthentication(properties.getProperty("username"), properties.getProperty("password")); //发件人邮件用户名、密码
	      }
	     });
	    session.setDebug(true);
	    
	    try{
	       // 创建默认的 MimeMessage 对象
	       MimeMessage message = new MimeMessage(session);

	       // Set From: 头部头字段
	       message.setFrom(new InternetAddress(from));

	       // Set To: 头部头字段
	       message.addRecipient(Message.RecipientType.TO,
	                                new InternetAddress(to));

	       // Set Subject: 邮件标题
	       message.setSubject("这是一封测试邮件","utf-8");

	       // 设置消息体
//	       message.setText("测试内容","utf-8");

	       String msg = "<!DOCTYPE html><html><head><meta charset='utf-8'><title>测试邮件</title></head><body>"
	          + "<h1>测试一哈</h1><br/><a href='http://www.baidu.com'>百度一下</a></body></html>";
	       
	       // 发送 HTML 消息, 可以插入html标签
	       message.setContent(msg,"text/html;charset=utf-8");
	       // 发送消息
	       Transport.send(message);
	       System.out.println("发送完毕");
	    }catch (MessagingException mex) {
	       mex.printStackTrace();
	    }
	}

}

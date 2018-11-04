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
	    //��ȡ�����ļ�
	    Properties properties = new GetProperty().getPropertyByFileName("config","email.properties");
	    
	    System.out.println(properties);
	    
	    // �ռ��˵�������
	    String to = "2842513372@qq.com";

	    // �����˵�������
	    String from = properties.getProperty("from");

	    // ָ�������ʼ�������Ϊ smtp.qq.com
	    String host = properties.getProperty("host");  //ָ�����������
	    
	    // �����ʼ�������
	    properties.setProperty("mail.smtp.host", host);

	    properties.put("mail.smtp.auth", "true");
	    // ��ȡĬ��session����
	    Session session = Session.getDefaultInstance(properties,new Authenticator(){
	      public PasswordAuthentication getPasswordAuthentication()
	      {
	       return new PasswordAuthentication(properties.getProperty("username"), properties.getProperty("password")); //�������ʼ��û���������
	      }
	     });
	    session.setDebug(true);
	    
	    try{
	       // ����Ĭ�ϵ� MimeMessage ����
	       MimeMessage message = new MimeMessage(session);

	       // Set From: ͷ��ͷ�ֶ�
	       message.setFrom(new InternetAddress(from));

	       // Set To: ͷ��ͷ�ֶ�
	       message.addRecipient(Message.RecipientType.TO,
	                                new InternetAddress(to));

	       // Set Subject: �ʼ�����
	       message.setSubject("����һ������ʼ�","utf-8");

	       // ������Ϣ��
//	       message.setText("��������","utf-8");

	       String msg = "<!DOCTYPE html><html><head><meta charset='utf-8'><title>�����ʼ�</title></head><body>"
	          + "<h1>����һ��</h1><br/><a href='http://www.baidu.com'>�ٶ�һ��</a></body></html>";
	       
	       // ���� HTML ��Ϣ, ���Բ���html��ǩ
	       message.setContent(msg,"text/html;charset=utf-8");
	       // ������Ϣ
	       Transport.send(message);
	       System.out.println("�������");
	    }catch (MessagingException mex) {
	       mex.printStackTrace();
	    }
	}

}

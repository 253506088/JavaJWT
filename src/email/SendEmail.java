package email;

import java.util.Properties;

import tool.GetProperty;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

import tool.GetProperty;

import javax.activation.*;

public class SendEmail {
	private Properties properties;
	private Session session;
	private String to;// �ռ�������
	private String from;// ����������
	private String host;// ���������

	/**
	 * ��ʼ��
	 */
	public SendEmail() {
		// ��ȡ�����ļ�
		properties = new GetProperty().getPropertyByFileName("email.properties");
		from = properties.getProperty("from");
		host = properties.getProperty("host");
		properties.setProperty("mail.smtp.host", host);
		properties.put("mail.smtp.auth", "true");
		// ��ȡĬ��session����
		session = Session.getDefaultInstance(properties, new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(properties.getProperty("username"),
						properties.getProperty("password")); // �������ʼ��û���������
			}
		});
		session.setDebug(true);
	}

	/**
	 * ����һ�������Ϣ���html���ʼ�
	 * 
	 * @param to
	 *            �ռ���
	 * @param title
	 *            ����
	 * @param msg
	 *            ��Ϣ��
	 * @param html
	 *            html����
	 * @return
	 */
	public Boolean sendHtmlAndMessageEmail(String to, String title, String msg, String html) {
		Boolean flag = true;
		try {
			// ����Ĭ�ϵ� MimeMessage ����
			MimeMessage message = new MimeMessage(session);
			// Set From: ͷ��ͷ�ֶ�
			message.setFrom(new InternetAddress(from));
			// Set To: ͷ��ͷ�ֶ�
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			// Set Subject: �ʼ�����
			message.setSubject(title, "utf-8");
			// ������Ϣ��
			message.setText(msg, "utf-8");
			// ���� HTML�ʼ�, ���Բ���html��ǩ
			message.setContent(html, "text/html;charset=utf-8");
			// ������Ϣ
			Transport.send(message);
			System.out.println("�������");
		} catch (MessagingException mex) {
			mex.printStackTrace();
			flag = false;
			System.out.println("�ʼ������쳣");
		}
		return flag;
	}

	/**
	 * ����һ��html�ʼ�
	 * 
	 * @param to
	 *            �ռ���
	 * @param title
	 *            ����
	 * @param html
	 *            html����
	 * @return
	 */
	public Boolean sendHtmlEmail(String to, String title, String html) {
		Boolean flag = true;
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject(title, "utf-8");
			message.setContent(html, "text/html;charset=utf-8");
			Transport.send(message);
			System.out.println("�������");
		} catch (MessagingException mex) {
			mex.printStackTrace();
			flag = false;
			System.out.println("�ʼ������쳣");
		}
		return flag;
	}

	/**
	 * ����һ����ͨ����Ϣ�ʼ�
	 * 
	 * @param to
	 * @param title
	 * @param msg
	 * @return
	 */
	public Boolean sendEmail(String to, String title, String msg) {
		Boolean flag = true;
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject(title, "utf-8");
			message.setText(msg, "utf-8");
			Transport.send(message);
			System.out.println("�������");
		} catch (MessagingException mex) {
			mex.printStackTrace();
			flag = false;
			System.out.println("�ʼ������쳣");
		}
		return flag;
	}
}

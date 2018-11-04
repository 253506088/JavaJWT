package controller.reception;

import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import database.dao.UserMapper;
import database.pojo.User;
import email.SendEmail;
import io.jsonwebtoken.Claims;
import jwt.JwtUtil;
import tool.Check;
import tool.GetMd5;

@Controller
@Transactional()
public class RegisterAndLogin {
	private final Logger log = Logger.getLogger(RegisterAndLogin.class);
	private ApplicationContext context = new ClassPathXmlApplicationContext("Spring-mybatis.xml");
	private UserMapper um = (UserMapper) context.getBean("userMapper");

	/**
	 * ��¼��ע��ҳ��
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "loginAndRegister", method = RequestMethod.GET)
	public String loginAndRegister(HttpSession session) {
		if (session.getAttribute("user") != null) {
			return "redirect:/";// �ض�����ҳ
		}
		return "registerAndLogin";
	}

	/**
	 * ��¼
	 * 
	 * @param usernameOrEmail
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "login", method = RequestMethod.POST, params = { "usernameOrEmail",
			"password" }, produces = "text/html;charset=utf-8")
	@ResponseBody
	public String login(String usernameOrEmail, String password) {
		HashMap<String, String> map = new HashMap<String, String>();
		User user = null;
		// �ж��ǲ�������
		if (Check.checkEmail(usernameOrEmail)) {
			user = um.loginEmail(usernameOrEmail, GetMd5.md5(password));
		} else {
			user = um.loginUsername(usernameOrEmail, GetMd5.md5(password));
		}
		if (user != null) {
			Integer time = 3 * 60 * 60 * 1000;// 3Сʱ��Ч��
			try {
				String json = JSONArray.toJSONString(user);
				String jwt = new JwtUtil().createJWT("userId:" + user.getUserId(), json, time,
						null);
				map.put("jwt", jwt);
				map.put("loginFlag", "true");
				map.put("userMsg", json);
			} catch (Exception e) {
				log.info("�û�\t" + usernameOrEmail + "\t ����jwtʧ��");
				map.put("loginFlag", "Error");
				return JSONArray.toJSONString(map);
			}
		} else {
			map.put("loginFlag", "false");
		}
		return JSONArray.toJSONString(map);
	}

	/**
	 * ������֤�������Ƿ��Ѵ���
	 * 
	 * @param email
	 * @return
	 */
	@RequestMapping(value = "getUserIdByEmail", method = RequestMethod.GET, params = { "email" })
	@ResponseBody
	public String getUserIdByEmail(String email) {
		HashMap<String, Boolean> map = new HashMap<String, Boolean>();
		if (um.getUserIdByEmail(email) == null) {
			map.put("flag", true);
			log.info("����\t" + email + "\t������");
		} else {
			map.put("flag", false);
			log.info("����\t" + email + "\t�Ѵ���");
		}
		return JSONArray.toJSONString(map);
	}

	/**
	 * ������֤���˺��Ƿ��Ѵ���
	 * 
	 * @param username
	 * @return
	 */
	@RequestMapping(value = "getUserIdByUsername", method = RequestMethod.GET, params = "username")
	@ResponseBody
	public String getUserIdByUsername(String username) {
		HashMap<String, Boolean> map = new HashMap<String, Boolean>();
		if (um.getUserIdByUsername(username) == null) {
			log.info("�˺�\t " + username + "\t������");
			map.put("flag", true);
		} else {
			log.info("�˺�\t" + username + "\t�Ѵ���");
			map.put("flag", false);
		}
		return JSONArray.toJSONString(map);
	}

	/**
	 * ������֤�ǳ��Ƿ��Ѵ���
	 * 
	 * @param nikeName
	 * @return
	 */
	@RequestMapping(value = "getUserIdByNikeName", method = RequestMethod.GET, params = "nikeName")
	@ResponseBody
	public String getUserIdByNikeName(String nikeName) {
		HashMap<String, Boolean> map = new HashMap<String, Boolean>();
		if (um.getUserIdByNikeName(nikeName) == null) {
			map.put("flag", true);
			log.info("�ǳ�\t" + nikeName + "\t������");
		} else {
			log.info("�ǳ�\t" + nikeName + "\t�Ѵ���");
			map.put("flag", false);
		}
		return JSONArray.toJSONString(map);
	}

	/**
	 * ע��
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "register", method = RequestMethod.POST, params = { "usernameOrEmail", "password",
			"nikeName" })
	@ResponseBody
	public String register(HttpServletRequest request, String usernameOrEmail, String password, String nikeName)
			throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("HttpStatus", "valueError");
		// ��֤�����Ƿ�ϸ�,�ǳ��Ƿ��Ѵ���
		if (usernameOrEmail.length() > 20 || usernameOrEmail.length() < 1 || password.length() > 20
				|| password.length() < 1 || nikeName.length() > 20 || nikeName.length() < 1
				|| um.getUserIdByNikeName(nikeName) != null) {
			log.info("�Ƿ�����,��������Ƿ�");
			return JSONArray.toJSONString(map);
		}

		Boolean isEmail = Check.checkEmail(usernameOrEmail);// ������Ϊtrue
		User user = null;
		Date time = new Date();
		// ��֤�˺Ż������Ƿ��Ѵ���,�������������������˺�ע�ỹ������ע���������û�
		if (isEmail) {
			if (um.getUserIdByEmail(usernameOrEmail) != null) {
				log.info("�Ƿ�����,��������Ƿ�");
				return JSONArray.toJSONString(map);
			}
			user = new User(null, GetMd5.md5(password), nikeName, usernameOrEmail, 1, time, time, 0);
		} else {
			if (um.getUserIdByUsername(usernameOrEmail) != null) {
				log.info("�Ƿ�����,��������Ƿ�");
				return JSONArray.toJSONString(map);
			}
			user = new User(usernameOrEmail, GetMd5.md5(password), nikeName, null, 1, time, time, 1);
		}

		// �����û�
		if (um.addUser(user) > 0) {
			if (isEmail) {
				// ����ע�ᣬ������֤�ʼ�
				user = um.getUserByEmailAndTime(usernameOrEmail, time, time);
				String jwt = new JwtUtil().createJWT(user.getUserId().toString(), new JSONArray().toJSONString(user),
						30 * 60 * 1000, null);
				if (sendEmail(request, jwt, usernameOrEmail)) {
					map.put("HttpStatus", "sendEmail");
					log.info("�û�������\t" + usernameOrEmail + "\tע��ɹ�,��֤�ʼ��ѷ���");
					return JSONArray.toJSONString(map);// �ѷ�����֤�ʼ�
				}
				map.put("HttpStatus", "sendEmailError");
				log.info("�û���\t" + usernameOrEmail + "\tע��ɹ�,��֤�ʼ�����ʧ��");
				return JSONArray.toJSONString(map);// �ʼ�����ʧ��
			}
			map.put("HttpStatus", "OK");
			log.info("�û����˺�\t" + usernameOrEmail + "\tע��ɹ�");
			return JSONArray.toJSONString(map);// �˺�ע��ɹ�
		}
		log.info("�û�\t" + usernameOrEmail + "\tע��ʧ��");
		map.put("HttpStatus", "registerError");
		return JSONArray.toJSONString(map);// �˺�ע��ʧ��
	}

	/**
	 * ��֤�����ʼ�
	 * 
	 * @param jwt
	 * @return
	 */
	@RequestMapping(value = "CheckEmailMsg", method = RequestMethod.GET, params = "jwt", produces = "text/html;charset=utf-8")
	@ResponseBody
	public String CheckEmailMsg(String jwt) {
		User user = null;
		try {
			Claims c = new JwtUtil().parseJWT(jwt);// ����JWT
			user = JSON.parseObject(c.getSubject(), User.class);// ��jsonת��Ϊʵ����
			log.info("������Ϸ��͸�\t" + user.getEmail() + "\t����֤�ʼ�");
		} catch (Exception e) {
			log.info("�����ѹ��ڵ���֤�ʼ�");
			return "�ʼ��ѹ���";
		}
		if (um.modifyActivationById(user.getUserId(), 1) > 0) {
			log.info("userId:" + user.getUserId() + ",email:" + user.getEmail() + "����ɹ�");
			return "����ɹ�!";
		}
		log.info("userId:" + user.getUserId() + ",email:" + user.getEmail() + "����ʧ��");
		return "����ʧ�ܣ�����ϵ����Ա!";
	}

	/**
	 * ����ע����֤�ʼ�
	 * 
	 * @param request
	 * @param jwt
	 * @param to
	 *            �ռ���
	 * @return
	 */
	public Boolean sendEmail(HttpServletRequest request, String jwt, String to) {
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
				+ "/";
		String linkHref = basePath + "CheckEmailMsg?jwt=" + jwt;
		SendEmail email = new SendEmail();
		String html = "<!DOCTYPE html><html><head><meta charset='utf-8'><title>XX��վע����֤</title></head><body>"
				+ "<h1>��������������ע�������ӱ��ʼ���Ҫ�����������,�ʼ���Ч��30���� </h1><br/><a href='" + linkHref
				+ "'>���������֤</a></body></html>";
		if (email.sendHtmlAndMessageEmail(to, "XX��վע����֤", "hello", html)) {
			log.info("��\t" + to + "\t����֤�ʼ����ͳɹ�");
			return true;
		}
		log.info("��\t" + to + "\t����֤�ʼ�����ʧ��");
		return false;
	}

	@RequestMapping(value = "test")
	public void test() {
		User user = new User("����", "aaaaa", "ssad", null, 1, new Date(233), new Date(233), 1);
		System.out.println(um.addUser(user));
		System.out.println(111);
		System.out.println(222);
		System.out.println(1 / 0);
	}
}

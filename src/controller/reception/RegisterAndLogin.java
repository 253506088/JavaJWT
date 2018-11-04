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
	 * 登录与注册页面
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "loginAndRegister", method = RequestMethod.GET)
	public String loginAndRegister(HttpSession session) {
		if (session.getAttribute("user") != null) {
			return "redirect:/";// 重定向到首页
		}
		return "registerAndLogin";
	}

	/**
	 * 登录
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
		// 判断是不是邮箱
		if (Check.checkEmail(usernameOrEmail)) {
			user = um.loginEmail(usernameOrEmail, GetMd5.md5(password));
		} else {
			user = um.loginUsername(usernameOrEmail, GetMd5.md5(password));
		}
		if (user != null) {
			Integer time = 3 * 60 * 60 * 1000;// 3小时有效期
			try {
				String json = JSONArray.toJSONString(user);
				String jwt = new JwtUtil().createJWT("userId:" + user.getUserId(), json, time,
						null);
				map.put("jwt", jwt);
				map.put("loginFlag", "true");
				map.put("userMsg", json);
			} catch (Exception e) {
				log.info("用户\t" + usernameOrEmail + "\t 生成jwt失败");
				map.put("loginFlag", "Error");
				return JSONArray.toJSONString(map);
			}
		} else {
			map.put("loginFlag", "false");
		}
		return JSONArray.toJSONString(map);
	}

	/**
	 * 用于验证该邮箱是否已存在
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
			log.info("邮箱\t" + email + "\t不存在");
		} else {
			map.put("flag", false);
			log.info("邮箱\t" + email + "\t已存在");
		}
		return JSONArray.toJSONString(map);
	}

	/**
	 * 用于验证该账号是否已存在
	 * 
	 * @param username
	 * @return
	 */
	@RequestMapping(value = "getUserIdByUsername", method = RequestMethod.GET, params = "username")
	@ResponseBody
	public String getUserIdByUsername(String username) {
		HashMap<String, Boolean> map = new HashMap<String, Boolean>();
		if (um.getUserIdByUsername(username) == null) {
			log.info("账号\t " + username + "\t不存在");
			map.put("flag", true);
		} else {
			log.info("账号\t" + username + "\t已存在");
			map.put("flag", false);
		}
		return JSONArray.toJSONString(map);
	}

	/**
	 * 用于验证昵称是否已存在
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
			log.info("昵称\t" + nikeName + "\t不存在");
		} else {
			log.info("昵称\t" + nikeName + "\t已存在");
			map.put("flag", false);
		}
		return JSONArray.toJSONString(map);
	}

	/**
	 * 注册
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
		// 验证长度是否合格,昵称是否已存在
		if (usernameOrEmail.length() > 20 || usernameOrEmail.length() < 1 || password.length() > 20
				|| password.length() < 1 || nikeName.length() > 20 || nikeName.length() < 1
				|| um.getUserIdByNikeName(nikeName) != null) {
			log.info("非法请求,请求参数非法");
			return JSONArray.toJSONString(map);
		}

		Boolean isEmail = Check.checkEmail(usernameOrEmail);// 是邮箱为true
		User user = null;
		Date time = new Date();
		// 验证账号或邮箱是否已存在,如果不存在则根据是用账号注册还是邮箱注册来创建用户
		if (isEmail) {
			if (um.getUserIdByEmail(usernameOrEmail) != null) {
				log.info("非法请求,请求参数非法");
				return JSONArray.toJSONString(map);
			}
			user = new User(null, GetMd5.md5(password), nikeName, usernameOrEmail, 1, time, time, 0);
		} else {
			if (um.getUserIdByUsername(usernameOrEmail) != null) {
				log.info("非法请求,请求参数非法");
				return JSONArray.toJSONString(map);
			}
			user = new User(usernameOrEmail, GetMd5.md5(password), nikeName, null, 1, time, time, 1);
		}

		// 保存用户
		if (um.addUser(user) > 0) {
			if (isEmail) {
				// 邮箱注册，发送验证邮件
				user = um.getUserByEmailAndTime(usernameOrEmail, time, time);
				String jwt = new JwtUtil().createJWT(user.getUserId().toString(), new JSONArray().toJSONString(user),
						30 * 60 * 1000, null);
				if (sendEmail(request, jwt, usernameOrEmail)) {
					map.put("HttpStatus", "sendEmail");
					log.info("用户以邮箱\t" + usernameOrEmail + "\t注册成功,验证邮件已发送");
					return JSONArray.toJSONString(map);// 已发送验证邮件
				}
				map.put("HttpStatus", "sendEmailError");
				log.info("用户以\t" + usernameOrEmail + "\t注册成功,验证邮件发送失败");
				return JSONArray.toJSONString(map);// 邮件发送失败
			}
			map.put("HttpStatus", "OK");
			log.info("用户以账号\t" + usernameOrEmail + "\t注册成功");
			return JSONArray.toJSONString(map);// 账号注册成功
		}
		log.info("用户\t" + usernameOrEmail + "\t注册失败");
		map.put("HttpStatus", "registerError");
		return JSONArray.toJSONString(map);// 账号注册失败
	}

	/**
	 * 验证激活邮件
	 * 
	 * @param jwt
	 * @return
	 */
	@RequestMapping(value = "CheckEmailMsg", method = RequestMethod.GET, params = "jwt", produces = "text/html;charset=utf-8")
	@ResponseBody
	public String CheckEmailMsg(String jwt) {
		User user = null;
		try {
			Claims c = new JwtUtil().parseJWT(jwt);// 解析JWT
			user = JSON.parseObject(c.getSubject(), User.class);// 将json转换为实体类
			log.info("解析完毕发送给\t" + user.getEmail() + "\t的验证邮件");
		} catch (Exception e) {
			log.info("解析已过期的验证邮件");
			return "邮件已过期";
		}
		if (um.modifyActivationById(user.getUserId(), 1) > 0) {
			log.info("userId:" + user.getUserId() + ",email:" + user.getEmail() + "激活成功");
			return "激活成功!";
		}
		log.info("userId:" + user.getUserId() + ",email:" + user.getEmail() + "激活失败");
		return "激活失败，请联系管理员!";
	}

	/**
	 * 发送注册验证邮件
	 * 
	 * @param request
	 * @param jwt
	 * @param to
	 *            收件人
	 * @return
	 */
	public Boolean sendEmail(HttpServletRequest request, String jwt, String to) {
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
				+ "/";
		String linkHref = basePath + "CheckEmailMsg?jwt=" + jwt;
		SendEmail email = new SendEmail();
		String html = "<!DOCTYPE html><html><head><meta charset='utf-8'><title>XX网站注册验证</title></head><body>"
				+ "<h1>如果不是您发起的注册请无视本邮件不要点击下面连接,邮件有效期30分钟 </h1><br/><a href='" + linkHref
				+ "'>完成邮箱验证</a></body></html>";
		if (email.sendHtmlAndMessageEmail(to, "XX网站注册验证", "hello", html)) {
			log.info("给\t" + to + "\t的验证邮件发送成功");
			return true;
		}
		log.info("给\t" + to + "\t的验证邮件发送失败");
		return false;
	}

	@RequestMapping(value = "test")
	public void test() {
		User user = new User("测测测", "aaaaa", "ssad", null, 1, new Date(233), new Date(233), 1);
		System.out.println(um.addUser(user));
		System.out.println(111);
		System.out.println(222);
		System.out.println(1 / 0);
	}
}

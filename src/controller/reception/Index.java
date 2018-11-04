package controller.reception;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;

import database.pojo.User;
import io.jsonwebtoken.Claims;
import jwt.JwtUtil;

/**
 * 首页控制器
 * @author hp
 *
 */
@Controller
public class Index {
	private final Logger log = Logger.getLogger(RegisterAndLogin.class);
	
	/**
	 * 用户个人主页
	 * @param index
	 */
	@RequestMapping(value="userIndex",method=RequestMethod.GET,params="jwt")
	public String userIndex(String jwt){
		User user = null;
		try {
			Claims c = new JwtUtil().parseJWT(jwt);// 解析JWT
			user = JSON.parseObject(c.getSubject(), User.class);// 将json转换为实体类
		} catch (Exception e) {
			log.info("jwt已过期");
			return "error";
		}
		return "index";
	}
}

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
 * ��ҳ������
 * @author hp
 *
 */
@Controller
public class Index {
	private final Logger log = Logger.getLogger(RegisterAndLogin.class);
	
	/**
	 * �û�������ҳ
	 * @param index
	 */
	@RequestMapping(value="userIndex",method=RequestMethod.GET,params="jwt")
	public String userIndex(String jwt){
		User user = null;
		try {
			Claims c = new JwtUtil().parseJWT(jwt);// ����JWT
			user = JSON.parseObject(c.getSubject(), User.class);// ��jsonת��Ϊʵ����
		} catch (Exception e) {
			log.info("jwt�ѹ���");
			return "error";
		}
		return "index";
	}
}

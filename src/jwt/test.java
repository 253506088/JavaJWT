package jwt;

import java.util.HashMap;
import java.util.Map;

import io.jsonwebtoken.Claims;

public class test {
	public static void main(String[] args) throws Exception {
		JwtUtil util = new JwtUtil();
		// 自定义的JWT存入信息
		Map<String, Object> claims = new HashMap<String, Object>();
		claims.put("nickName", "黑白大彩电");
		claims.put("admin", true);
		//创建JWT
		String jwt = util.createJWT("jwtId", "{id:233,username:253506088,password:123456}", 10*60*60,claims);
		System.out.println("生成的jwt:[\n\t"+jwt+"\n]"); // 輸出一下生成的jwt

		Claims c = util.parseJWT(jwt);// 解析JWT
		// 获取JWT里的数据  
		System.out.println("\n输出JWT中的数据:[");
		System.out.println("\t"+c.getId());
		System.out.println("\t"+c.getIssuedAt());
		System.out.println("\t"+c.getSubject());
		System.out.println("\t"+c.getIssuer());// 这里在生成的时候没设置issuer所以会输出null
		System.out.println("\t"+c.get("nickName", String.class));
		System.out.println("\t"+c.get("admin", Boolean.class));
		System.out.println("]\n");
		
		// 解析过期的jwt会抛出jwt过期异常
		try {
			String jwt_old = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ7aWQ6MjMzLHVzZXJuYW1lOjI1MzUwNjA4OCxwYXNzd29yZDoxMjM0NTZ9Iiwibmlja05hbWUiOiLpu5Hnmb3lpKflvannlLUiLCJhZG1pbiI6dHJ1ZSwiZXhwIjoxNTQwNzI4NzIwLCJpYXQiOjE1NDA3Mjg2ODQsImp0aSI6Imp3dElkIn0.nrGxUdbLT6P6RnLH3TCunCDJWiECLkj2keIMNFQW_fQ";
			Claims c_old = util.parseJWT(jwt_old);
			System.out.println("该jwt未过期");
		} catch (Exception e) {
			System.out.println("该jwt已过期");
		}
	}
}

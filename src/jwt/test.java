package jwt;

import java.util.HashMap;
import java.util.Map;

import io.jsonwebtoken.Claims;

public class test {
	public static void main(String[] args) throws Exception {
		JwtUtil util = new JwtUtil();
		// �Զ����JWT������Ϣ
		Map<String, Object> claims = new HashMap<String, Object>();
		claims.put("nickName", "�ڰ״�ʵ�");
		claims.put("admin", true);
		//����JWT
		String jwt = util.createJWT("jwtId", "{id:233,username:253506088,password:123456}", 10*60*60,claims);
		System.out.println("���ɵ�jwt:[\n\t"+jwt+"\n]"); // ݔ��һ�����ɵ�jwt

		Claims c = util.parseJWT(jwt);// ����JWT
		// ��ȡJWT�������  
		System.out.println("\n���JWT�е�����:[");
		System.out.println("\t"+c.getId());
		System.out.println("\t"+c.getIssuedAt());
		System.out.println("\t"+c.getSubject());
		System.out.println("\t"+c.getIssuer());// ���������ɵ�ʱ��û����issuer���Ի����null
		System.out.println("\t"+c.get("nickName", String.class));
		System.out.println("\t"+c.get("admin", Boolean.class));
		System.out.println("]\n");
		
		// �������ڵ�jwt���׳�jwt�����쳣
		try {
			String jwt_old = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ7aWQ6MjMzLHVzZXJuYW1lOjI1MzUwNjA4OCxwYXNzd29yZDoxMjM0NTZ9Iiwibmlja05hbWUiOiLpu5Hnmb3lpKflvannlLUiLCJhZG1pbiI6dHJ1ZSwiZXhwIjoxNTQwNzI4NzIwLCJpYXQiOjE1NDA3Mjg2ODQsImp0aSI6Imp3dElkIn0.nrGxUdbLT6P6RnLH3TCunCDJWiECLkj2keIMNFQW_fQ";
			Claims c_old = util.parseJWT(jwt_old);
			System.out.println("��jwtδ����");
		} catch (Exception e) {
			System.out.println("��jwt�ѹ���");
		}
	}
}

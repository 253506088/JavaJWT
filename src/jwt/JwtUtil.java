package jwt;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


/**
 * JWT������
 * 
 * @author hp
 *
 */
public class JwtUtil {
	/**
     * ����jwt
     * @param id JWT��Ψһ��ʶ������ҵ����Ҫ�������������Ϊһ�����ظ���ֵ����Ҫ������Ϊһ����token,�Ӷ��ر��طŹ���
     * @param subject json�ַ���
     * @param ttlMillis JWT����Чʱ��(��λ:����)
     * @param claims ����payload��˽�������������ض���ҵ����Ҫ��ӣ����Ҫ���������֤��һ������Ҫ��jwt�Ľ��շ���ǰ��ͨ����֤��ʽ�ģ�,һ�����ڴ���Զ�����Ϣ
     * @return
     * @throws Exception
     */
	public String createJWT(String id, String subject, long ttlMillis,Map<String, Object> claims) throws Exception {
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256; // ָ��ǩ����ʱ��ʹ�õ�ǩ���㷨��Ҳ����header�ǲ��֣�jjwt�Ѿ����ⲿ�����ݷ�װ���ˡ�
		long nowMillis = System.currentTimeMillis();// ����JWT��ʱ��
		Date now = new Date(nowMillis);
		
		// ���claims�ǿ��򴴽�һ��
		if(claims == null)
			claims = new HashMap<String, Object>();
		
		/**
		 * ����ǩ����ʱ��ʹ�õ���Կsecret,����������ط�װ�˵ģ�һ����Դӱ��������ļ��ж�ȡ���м������Կ������¶Ŷ�������������˵�˽Կ�����κγ�������Ӧ����¶��ȥ��һ���ͻ��˵�֪���secret,
		 * �Ǿ���ζ�ſͻ����ǿ�������ǩ��jwt�ˡ�
		 */
		SecretKey key = this.generalKey();

		// ���������Ϊpayload��Ӹ��ֱ�׼������˽��������
		JwtBuilder builder = Jwts.builder()	//������ʵ����newһ��JwtBuilder������jwt��body
				.setClaims(claims) //�����˽��������һ��Ҫ����������Լ�������˽�е�����������Ǹ�builder��claim��ֵ��һ��д�ڱ�׼��������ֵ֮�󣬾��Ǹ�������Щ��׼��������
				.setId(id) //����jti(JWT ID)����JWT��Ψһ��ʶ������ҵ����Ҫ�������������Ϊһ�����ظ���ֵ����Ҫ������Ϊһ����token,�Ӷ��ر��طŹ�����
				.setIssuedAt(now) //iat: jwt��ǩ��ʱ��
				.setSubject(subject) //sub(Subject)���������JWT�����壬�����������ˣ������һ��json��ʽ���ַ��������Դ��ʲôuserid��roldid֮��ģ���Ϊʲô�û���Ψһ��־��
				.signWith(signatureAlgorithm, key); //����ǩ��ʹ�õ�ǩ���㷨��ǩ��ʹ�õ���Կ
		
		if (ttlMillis >= 0) { 
			long expMillis = nowMillis + ttlMillis; 
			Date exp = new Date(expMillis); 
			builder.setExpiration(exp); //���ù���ʱ�� 
		}
		return builder.compact();           //�Ϳ�ʼѹ��Ϊxxxxxxxxxxxxxx.xxxxxxxxxxxxxxx.xxxxxxxxxxxxx������jwt
	}

	/**
     * ����jwt
     * @param jwt
     * @return
     * @throws Exception
     */ 
	public Claims parseJWT(String jwt) throws Exception{ 
    	 SecretKey key = this.generalKey(); //ǩ����Կ�������ɵ�ǩ������Կһģһ�� 
    	 Claims claims = Jwts.parser() //�õ�DefaultJwtParser 
    			 .setSigningKey(key) //����ǩ������Կ 
    			 .parseClaimsJws(jwt).getBody();//������Ҫ������jwt 
		 return claims; 
     }
	
	/**
	 * ���ַ������ɼ���key,ʹ��jdk8��Base64����н��������
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public SecretKey generalKey() throws UnsupportedEncodingException {
		String stringKey = Base64.getEncoder().encodeToString("BlackTV23333".getBytes("utf-8"));// ���������ļ��м��ܵ�����,����ͼʡ��ֱ�ӻ�ȡ����
		byte[] encodedKey = Base64.getDecoder().decode(stringKey);// ���ص��������
//		System.out.println(encodedKey);// ���������ļ��м��ܵ����Ľ���֮��Ľ��
//		System.out.println(Base64.getMimeEncoder().encodeToString(encodedKey));// ����Base64URL��ȫ�ַ���
		/**
		 * ���ݸ������ֽ�����ʹ��AES�����㷨����һ����Կ��ʹ�� encodedKey�е�ʼ���Ұ��� 0 ��ǰ leng
		 * ���ֽ����ǵ�Ȼ�����С�����������������ϻ��Ƴ�����Java���ܺͽ��ܵ�һЩ�㷨��
		 */
		SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
		return key;
	}
}

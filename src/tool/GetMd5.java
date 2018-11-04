package tool;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * MD5鍔犲瘑鐨勭被
 * 
 * @author BlackTV
 *
 */
public class GetMd5 {
	/**
	 * MD5鍔犲瘑
	 * 
	 * @param password
	 * @return
	 */
	public static String md5(String password) {
		try {
			// 鐢熸垚涓�涓狹D5鍔犲瘑璁＄畻鎽樿
			MessageDigest md = MessageDigest.getInstance("MD5");
			// 璁＄畻md5鍑芥暟
			md.update(password.getBytes());
			// digest()鏈�鍚庣‘瀹氳繑鍥瀖d5 hash鍊硷紝杩斿洖鍊间负8涓哄瓧绗︿覆銆傚洜涓簃d5 hash鍊兼槸16浣嶇殑hex鍊硷紝瀹為檯涓婂氨鏄�8浣嶇殑瀛楃
			// BigInteger鍑芥暟鍒欏皢8浣嶇殑瀛楃涓茶浆鎹㈡垚16浣峢ex鍊硷紝鐢ㄥ瓧绗︿覆鏉ヨ〃绀猴紱寰楀埌瀛楃涓插舰寮忕殑hash鍊�
			String md5 = new BigInteger(1, md.digest()).toString(16);
			// BigInteger浼氭妸0鐪佺暐鎺夛紝闇�琛ュ叏鑷�32浣�
			return fillMD5(md5);
		} catch (Exception e) {
			throw new RuntimeException("MD5鍔犲瘑閿欒:" + e.getMessage(), e);
		}
	}

	private static String fillMD5(String md5) {
		return md5.length() == 32 ? md5 : fillMD5("0" + md5);
	}
}

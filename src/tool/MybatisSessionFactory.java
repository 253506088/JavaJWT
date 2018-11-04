package tool;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * �������ɡ��ر�Mybatis��SqlSession�Ự����
 * @author BlackTV
 *
 */
public class MybatisSessionFactory {
	private static SqlSessionFactory factory;
	//������Ӧ�ó���������������̬�����ֻ��ִ��һ��
	static{
		String config = "mybatis_config.xml";//mybatis�����ļ�����
		try {
			InputStream is = Resources.getResourceAsStream(config);
			factory = new SqlSessionFactoryBuilder().build(is);//����factory
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param affair true�ر�����false��������
	 * @return	��ȡSqlSession�Ự
	 */
	public static SqlSession getSqlSession(boolean affair){
		return factory.openSession(affair);
	}
	
	/**
	 * �ر�SqlSession�Ự
	 * @param sqlSession
	 */
	public static void close(SqlSession sqlSession){
		if(sqlSession!=null){
			sqlSession.close();
		}
	}
}

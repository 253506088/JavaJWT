package tool;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * 用于生成、关闭Mybatis的SqlSession会话的类
 * @author BlackTV
 *
 */
public class MybatisSessionFactory {
	private static SqlSessionFactory factory;
	//在整个应用程序的生命周期里，静态代码块只会执行一次
	static{
		String config = "mybatis_config.xml";//mybatis配置文件名称
		try {
			InputStream is = Resources.getResourceAsStream(config);
			factory = new SqlSessionFactoryBuilder().build(is);//创建factory
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param affair true关闭事务，false开启事务
	 * @return	获取SqlSession会话
	 */
	public static SqlSession getSqlSession(boolean affair){
		return factory.openSession(affair);
	}
	
	/**
	 * 关闭SqlSession会话
	 * @param sqlSession
	 */
	public static void close(SqlSession sqlSession){
		if(sqlSession!=null){
			sqlSession.close();
		}
	}
}

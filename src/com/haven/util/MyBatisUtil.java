package com.haven.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public final class MyBatisUtil {

	private static InputStream is = null;
	private static SqlSessionFactory sqlSessionFactory = null;
	
	static {
		try {
			is = Resources.getResourceAsStream("com/haven/config/Configuration.xml");
		} catch (IOException e) {
			System.out.println("加载配置文件失败："+ e.getMessage());
			e.printStackTrace();
		}
	}
	
	private MyBatisUtil() {
	}
	
	/**
	 * 获取SQLSessionFactory对象
	 * @return
	 * @throws IOException
	 */
	private synchronized static SqlSessionFactory getSqlSessionFactory() throws IOException {
		if(sqlSessionFactory == null) {
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
		}
		return sqlSessionFactory;
	}
	
	public static SqlSession openSession() throws IOException {
		
		return getSqlSessionFactory().openSession();
	}
	
}

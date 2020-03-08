package junit.test;

import java.sql.SQLException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;

//使用注解测试  此时spring容器已经加载
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:beans.xml"})
public class Apptest {
	
	@Autowired
	private DruidDataSource druidDataSource;
	
	@Test
	public void test01() throws SQLException{
		
		DruidPooledConnection connection = druidDataSource.getConnection();
		System.out.println(connection);
		
		
	}
	
}

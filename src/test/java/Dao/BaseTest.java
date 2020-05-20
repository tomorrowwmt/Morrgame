package Dao;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 
 * 
 * @ClassName: BaseTest
 * 
 * @Description: 测试类的基类,配置Spring和junit整合,junit启动时加载springIOC容器
 */
//告诉junit spring配置文件
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-netty.xml"})
public class BaseTest {
	
}

package netty;

import cn.pomit.springwork.netty.Shopping.Entity.Shop;
import cn.pomit.springwork.netty.Mapper.ShopMapper;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class ShopTeST {
    @Test
    public void test(){
        ApplicationContext ac=new ClassPathXmlApplicationContext("spring-netty.xml");
        ShopMapper shopMapper = ac.getBean(ShopMapper.class);
        List<Shop> shoppings = shopMapper.selectAll();
        Integer price = shoppings.get(0).getPrice();
        System.out.println("\n"+price);
    }
}

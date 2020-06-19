package netty;

import cn.pomit.springwork.netty.Twitter.IdWorker;
import cn.pomit.springwork.netty.entity.Equipment;
import cn.pomit.springwork.netty.mapper.EquipMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class EquipTest {
    @Test
    public void  query(){
        ApplicationContext ac=new ClassPathXmlApplicationContext("spring-netty.xml");
        EquipMapper equipMapper = ac.getBean(EquipMapper.class);
        List<Equipment> queryequip = equipMapper.queryequip();
        System.out.println(queryequip);
    }
    @Test
    public void insert(){
        ApplicationContext ac=new ClassPathXmlApplicationContext("spring-netty.xml");
        EquipMapper equipMapper = ac.getBean(EquipMapper.class);
        Equipment equip=new Equipment();
        IdWorker worker=new IdWorker(1,1,1);
        long l=worker.nextId();
        equip.setId(l);
        equip.setName("sddscs");
        equip.setAtk(100);
        equip.setEndurance(70);
        int insert = equipMapper.insert(equip);
        //System.out.println(insert);
    }
    @Test
    public void querid(){
        ApplicationContext ac=new ClassPathXmlApplicationContext("spring-netty.xml");
        EquipMapper equipMapper = ac.getBean(EquipMapper.class);
        Equipment querid = equipMapper.querid(1);
        System.out.println(querid);
    }
    @Test
    public void delete(){
        ApplicationContext ac=new ClassPathXmlApplicationContext("spring-netty.xml");
        EquipMapper equipMapper = ac.getBean(EquipMapper.class);
        int delete = equipMapper.delete(4);
        System.out.println(delete);
    }
    @Test
    public void update(){
        ApplicationContext ac=new ClassPathXmlApplicationContext("spring-netty.xml");
        EquipMapper equipMapper = ac.getBean(EquipMapper.class);
        Equipment equip=new Equipment();
        equip.setId(4L);
        equip.setEndurance(60);
        int update = equipMapper.update(equip);
        System.out.println(update);
    }
}

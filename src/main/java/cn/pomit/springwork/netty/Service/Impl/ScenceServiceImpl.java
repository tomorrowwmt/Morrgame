package cn.pomit.springwork.netty.Service.Impl;

import cn.pomit.springwork.netty.DTO.ResultCode;
import cn.pomit.springwork.netty.Entity.User;
import cn.pomit.springwork.netty.Excel.ExcelUtil;
import cn.pomit.springwork.netty.Excel.PeiZhi;
import cn.pomit.springwork.netty.Monster.Monster;
import cn.pomit.springwork.netty.Npc.NPC;
import cn.pomit.springwork.netty.Service.ScenceService;
import cn.pomit.springwork.netty.Service.UserService;
import cn.pomit.springwork.netty.UtilSpring.SpringUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ScenceServiceImpl implements ScenceService {
    private ResultCode resultCode;
    @Override
    public Map getMonster(Monster monster) throws Exception {
        //直接采用读取不变map读取怪兽
        Map<String,Object> master=PeiZhi.jiazai();
        System.out.println("\n"+master.get("Monster"));
        return master;
    }

    @Override
    public Map getScenceByRole(User user) throws Exception {
        //先查询当前场景用户,这里直接使用Uservice中缓存 @Cacheable(value = "userCache")查询
        UserService userService= SpringUtil.getBean(UserService.class);
        //ApplicationContext ac=new ClassPathXmlApplicationContext("spring-netty.xml");
        //UserService userService = ac.getBean(UserService.class);
        List<User> users = userService.queryAllUser();
        //在调用配置类方法读取配置表加载当前怪兽数量，npc等等
        Map m=PeiZhi.jiazai();
        System.out.println("============================");
        System.out.println("\n"+users+m.get("Ditu")+""+m.get("NPC")+""+m.get("Monster"));
        return m;
    }

    @Override
    public String moveScence(User user) throws Exception {
        //先缓存拿到用户数据
        //ApplicationContext ac=new ClassPathXmlApplicationContext("spring-netty.xml");
       // UserService userService = ac.getBean(UserService.class);
        UserService userService= SpringUtil.getBean(UserService.class);
        String username = userService.queryById(1L).getUsername();
        //地图信息
        String filepath=Thread.currentThread().getContextClassLoader().getResource("Excel/Ditu.xlsx").getPath();
        String[] keys = new String[]{"mid","name","desc","neighbor","monsterStr"};
        List<Map<String, Object>> impList = ExcelUtil.imp(filepath,keys);
            for (Map<String, Object> map : impList) {
                System.out.println("\n"+username+"移动<"+ map.get("neighbor")+">");
            }
       return "移动成功"+ResultCode.MOVE_SUCESS;
    }

    @Override
    public String talkNpc(User user) throws Exception {
        UserService userService= SpringUtil.getBean(UserService.class);
        String username = userService.queryById(1L).getUsername();
        NPC npc=new NPC();
        return (username+"玩家talk<"+npc.getTalk()+">");
    }

    public static void main(String[] args) throws Exception {
        User user=new User();
        Monster monster=new Monster();
        //new ScenceServiceImpl().getMonster(monster);
        new ScenceServiceImpl().moveScence(user);
    }
}

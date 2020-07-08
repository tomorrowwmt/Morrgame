package cn.pomit.springwork.netty.User.Service.Impl;

import cn.pomit.springwork.netty.DTO.ResultCode;
import cn.pomit.springwork.netty.User.Entity.User;
import cn.pomit.springwork.netty.Excel.ExcelUtil;
import cn.pomit.springwork.netty.Excel.PeiZhi;
import cn.pomit.springwork.netty.Monster.Monster;
import cn.pomit.springwork.netty.Npc.NPC;
import cn.pomit.springwork.netty.User.Service.ScenceService;
import cn.pomit.springwork.netty.User.Service.UserService;
import cn.pomit.springwork.netty.UtilSpring.SpringUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ScenceServiceImpl implements ScenceService {
    private ResultCode resultCode;

    @Override
    public String getScenceByRole(User user) throws Exception {
        //先查询当前场景用户,这里直接使用Uservice中缓存 @Cacheable(value = "userCache")查询
        UserService userService= SpringUtil.getBean(UserService.class);
        List<User> users = userService.queryAllUser();
        //在调用配置类方法读取配置表加载当前怪兽数量，npc等等
        Object ditu = PeiZhi.jiazai().get("Ditu");
        Object monster = PeiZhi.jiazai().get("Monster");
        System.out.println("\n"+users+ditu+""+""+monster);
        return "当前场景玩家"+users+"\n"+"当前场景信息"+ditu+monster;
    }

    @Override
    public String moveScence(User user) throws Exception {
        //先缓存拿到用户数据
        UserService userService= SpringUtil.getBean(UserService.class);
        String username = userService.queryById(1L).getUsername();
        //地图信息
        String filepath=Thread.currentThread().getContextClassLoader().getResource("Excel/Ditu.xlsx").getPath();
        String[] keys = new String[]{"mid","name","desc","neighbor","monsterStr"};
        List<Map<String, Object>> impList = ExcelUtil.imp(filepath,keys);
            for (Map<String, Object> map : impList) {
               return username+"移动<"+ map.get("neighbor")+">"+"\n"+"移动完成";
            }
       return "移动成功";
    }

    @Override
    public String talkNpc(User user) throws Exception {
        UserService userService= SpringUtil.getBean(UserService.class);
        String username = userService.queryById(1L).getUsername();
        NPC npc=new NPC();
        return (username+"玩家talk<"+npc.getTalk()+">"+"\n"+"谈话完成");
    }
}

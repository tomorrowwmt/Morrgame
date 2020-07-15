package cn.pomit.springwork.netty.User.Service.Impl;
import cn.pomit.springwork.netty.DTO.ResultCode;
import cn.pomit.springwork.netty.Excel.PeiZhiBiao;
import cn.pomit.springwork.netty.Mapper.UserMapper;
import cn.pomit.springwork.netty.User.Entity.User;
import cn.pomit.springwork.netty.Excel.ExcelUtil;
import cn.pomit.springwork.netty.Npc.NPC;
import cn.pomit.springwork.netty.User.Service.ScenceService;
import cn.pomit.springwork.netty.User.Service.UserService;
import cn.pomit.springwork.netty.UtilSpring.SpringUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class ScenceServiceImpl implements ScenceService {
    private ResultCode resultCode;

    @Override
    public  String onEnterScene(User user) {
        return "欢迎玩家进入游戏场景";
    }

    @Override
    public  String getScenceByRole(User user) throws Exception {
        //当前场景用户
        String onEnterScene = onEnterScene(user);
        UserMapper userMapper= SpringUtil.getBean(UserMapper.class);
       // Long userBymap = userMapper.findUserBymap(1L);
        List<String> strings = PeiZhiBiao.ditu().get(0);
        List<List<String>> mas= PeiZhiBiao.monster();
        NPC npc = new NPC("村花莉萌", "大傻逼你来了");
        System.out.println(onEnterScene + "\n" + "当前场景玩家" + user.getUsername() + "\n" + "当前场景信息" +
                strings  + mas + "\n" + "当前场景npc:" + npc.getName());
       //if(user.getMid().equals(userBymap)) {
           //return onEnterScene + "\n" + "当前场景玩家" + user.getUsername() + "\n" + "当前场景信息" +  strings  + mas +
                   //"\n" + "当前场景npc:" + npc.getName();
       //}
       return "打印完成";
    }
    @Override
    public String moveScence(User user) throws Exception {
       // UserService userService= SpringUtil.getBean(UserService.class);
        //获取相邻地图
        String neighbor= PeiZhiBiao.ditu().get(0).get(3);
        return user.getUsername()+"移动<"+ neighbor+">"+"\n"+"移动到魔化之地成功";
    }

    @Override
    public String talkNpc(User user) throws Exception {
        UserService userService= SpringUtil.getBean(UserService.class);
        NPC npc=new NPC();
        return (user.getUsername()+"玩家talk<"+npc.getTalk()+">"+"\n"+"谈话完成");
    }
}

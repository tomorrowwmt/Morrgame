package cn.pomit.springwork.netty.User.Service.Impl;
import cn.pomit.springwork.netty.DTO.ResultCode;
import cn.pomit.springwork.netty.Excel.PeiZhiBiao;
import cn.pomit.springwork.netty.Mapper.UserMapper;
import cn.pomit.springwork.netty.User.Entity.User;
import cn.pomit.springwork.netty.Excel.ExcelUtil;
import cn.pomit.springwork.netty.Npc.NPC;
import cn.pomit.springwork.netty.User.Service.ScenceService;
import cn.pomit.springwork.netty.User.Service.UserService;
import cn.pomit.springwork.netty.User.Session.SessionImpl;
import cn.pomit.springwork.netty.UtilSpring.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.SocketTimeoutException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class ScenceServiceImpl implements ScenceService {
    private ResultCode resultCode;
    @Autowired
    private  UserService userService;
    private SessionImpl session;
    @Override
    public  String onEnterScene(User user) {
        return "欢迎玩家进入游戏场景";
    }

    @Override
    public  String getScenceByRole(User user) throws Exception {
         String ret;
         UserMapper userMapper= SpringUtil.getBean(UserMapper.class);
         //设置开始地图mid=1，查看玩家
         List<User> userBymap = userService.findUserBymap(1L);
         user.setUsername(userBymap.toString());
         //配置表mid=1数据
          List<String> strings = PeiZhiBiao.ditu().get(0);
          //场景怪物
          List<List<String>> mas= PeiZhiBiao.monster();
          //Npc
          NPC npc = new NPC("村花莉萌", "大傻逼你来了");
           ret= "当前场景玩家" + user.getUsername()+"\n" + "当前场景信息" +
                    strings  + mas + "\n" + "当前场景npc:" + npc.getName();
       return ret;
    }
    @Override
    public String moveScence(User user) throws Exception {
        //获取相邻地图
        String neighbor= PeiZhiBiao.ditu().get(0).get(3);
        return user.getUsername()+"移动<"+ neighbor+">"+"\n"+"起始之地移动到魔化之地成功";
    }

    @Override
    public String talkNpc(User user) throws Exception {
        String result;
        NPC npc=new NPC();
        result=user.getUsername()+"玩家talk<"+npc.getTalk()+">"+"\n"+"谈话完成";
        return result ;
    }
}

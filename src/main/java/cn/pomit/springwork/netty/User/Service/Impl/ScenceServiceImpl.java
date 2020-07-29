package cn.pomit.springwork.netty.User.Service.Impl;
import cn.pomit.springwork.netty.Ditu.Ditu;
import cn.pomit.springwork.netty.Excel.JaxbUtil;
import cn.pomit.springwork.netty.Excel.PeiZhiBiao;
import cn.pomit.springwork.netty.User.Entity.User;
import cn.pomit.springwork.netty.Npc.NPC;
import cn.pomit.springwork.netty.User.Service.ScenceService;
import cn.pomit.springwork.netty.User.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import static cn.pomit.springwork.netty.Excel.JaxbUtil.*;

@Service
public class ScenceServiceImpl implements ScenceService {
    @Autowired
    private  UserService userService;
    @Override
    public String getScenceByRole(User user) throws Exception {
         String ret;
         //设置开始地图mid=1，查看玩家
         List<User> userBymap = userService.findUserBymap(1L);
         user.setUsername(userBymap.toString());
        //xml配置表mid=1数据
          Ditu ditu = xmlTojava();
        //选出地图一场景怪物
          //List<List<String>> mas= PeiZhiBiao.monster();
        String mas = masxmlTojava().getName();
        //Npc
          NPC npc = new NPC("村花莉萌", "大傻逼你来了");
           ret= "当前场景"+ditu.getMname()+",玩家" + user.getUsername()+"\n" + "当前场景信息" +
                   ditu.toString() + mas + "\n" + "当前场景npc:" + npc.getName();
       return ret;
    }
    @Override
    public String moveScence() throws Exception {
        //拿到地图mid=1操作
        Integer mid = xmlTojava().getMid();
        //获取相邻地图
        String neighbor = xmlTojava().getNeighbor();
        //地图信息
        List<List<String>> monster= PeiZhiBiao.monster();
        List<String> movescences = PeiZhiBiao.ditu().get(1);
        return "地图从mid="+mid+",移动<"+ neighbor+">"+"\n"+"起始之地移动到魔化之地成功"+"\n"+
                "场景信息"+movescences +monster;
    }

    @Override
    public String talkNpc(User user) throws Exception {
        String result;
        NPC npc=new NPC();
        user.setMap("魔化之地");
        result="场景"+user.getMap()+",玩家"+user.getUsername()+"玩家talk<"+ npc.getTalk() +">";
        return result ;
    }
}

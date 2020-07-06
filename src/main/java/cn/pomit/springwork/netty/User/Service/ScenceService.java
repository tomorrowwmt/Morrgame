package cn.pomit.springwork.netty.User.Service;

import cn.pomit.springwork.netty.User.Entity.User;
import cn.pomit.springwork.netty.Monster.Monster;

import java.util.Map;

public interface ScenceService {
   //打印当前在起始之地地图所有信息
   String getScenceByRole(User user) throws Exception;
   //移动当前地图到一个地图
   String moveScence(User user) throws Exception;
   //与地图npc谈话
   String talkNpc(User user)  throws Exception;
}

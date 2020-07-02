package cn.pomit.springwork.netty.Service;

import cn.pomit.springwork.netty.Entity.User;
import cn.pomit.springwork.netty.Monster.Monster;

import java.util.Map;

public interface ScenceService {
   //生成怪物
   Map getMonster(Monster monster) throws  Exception;
   //打印当前在起始之地地图所有信息
   Map getScenceByRole(User user) throws Exception;
   //移动当前地图到一个地图
   String moveScence(User user) throws Exception;
   //与地图npc谈话
   String talkNpc(User user)  throws Exception;
}

package cn.pomit.springwork.netty.Npc;
/*
npc玩家设计实体类
 */
public class NPC {
    //npc的名字
    String name;
    //对话
    String talk;
    public NPC(String name,String  talk){
        this.name=name;
        this.talk=talk;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTalk() {
        return talk;
    }

    public void setTalk(String talk) {
        this.talk = talk;
    }

    @Override
    public String toString() {
      return name+"玩家"+talk;
    }
}

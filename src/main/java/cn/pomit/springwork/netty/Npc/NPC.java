package cn.pomit.springwork.netty.Npc;
/*
npc玩家设计实体类
 */
public class NPC {
    private Integer id;
    //npc的名字
    private  String name;
    //对话
   String talk="你好我是美丽大方的开心萌萌，终于等到你来了" +"\n"+
            "哦，我才不想看见你呵呵呵";
    public NPC(String name,String  talk){
        this.name=name;
        this.talk=talk;
    }
    public NPC() {

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

   // @Override
    //public String toString() {
      //return name+"村花莉萌"+talk;
    //}

    @Override
    public String toString() {
        return "NPC{" +
                ", name='村花莉萌" + name + '\'' +
                ", talk='" + talk + '\'' +
                '}';
    }
}

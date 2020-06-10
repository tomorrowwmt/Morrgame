package cn.pomit.springwork.netty.entity;

import cn.pomit.springwork.netty.Monster.Monster;
import cn.pomit.springwork.netty.Skills.Skill;
import cn.pomit.springwork.netty.mapper.EquipMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/*
用户
 */
public class User {
    private Integer uid;
    private String username;
    private String password;
    //当前生命值
    private int hp;
    //药水增加的hp或者mp
    public int yaoshui;
    //设计玩家的经验
    public int exp;
   //等级
    public  int level;
    //下一级所需经验
    public  int levelExp;
    private  int atk;
    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevelExp() {
        return levelExp;
    }

    public void setLevelExp(int levelExp) {
        this.levelExp = levelExp;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getYaoshui() {
        return yaoshui;
    }


    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", hp=" + hp +
                ", yaoshui=" + yaoshui +
                ", exp=" + exp +
                ", level=" + level +
                ", levelExp=" + levelExp +
                ", atk=" + atk +
                '}';
    }

    public void setYaoshui(int yaoshui) {
        this.yaoshui = yaoshui;
    }

    //攻击
    public void bit(Monster mas){
        Skill skill=new Skill();
        skill.name="末日风暴";
        skill.type="普通攻击";
        skill.cd=1;
        skill.mp=30;
        mas.setHp(mas.getHp()-10);
        if(mas.getHp()<=0){
            mas.setHp(0);
        }
        System.out.println(mas.getName()+"被"+username+"玩家利用"+skill.type+skill.name
                +"攻击，剩余血量是"+mas.getHp()+"\n"+"技能CD需要"+skill.cd+"秒后恢复");
        System.out.println("==========================================");
        System.out.println("CD恢复");
        skill.setMp(skill.getMp()-5);
        if(skill.getMp()<=0){
            System.out.println("mp值过低无法使用技能");
        }else{
            System.out.println("mp等待1s自动恢复");
            System.out.println("=============");
            System.out.println("mp恢复成功");
            System.out.println("=============");
        }
    }
    //必杀攻击
    public void MagicAttack(Monster mas) {
        Skill skill=new Skill();
        skill.name="屠龙大击";
        skill.type="必杀技";
        skill.cd=3;
        skill.mp=30;
        mas.setHp(mas.getHp()-30);
        if(mas.getHp()<=0){
            mas.setHp(0);
        }
        System.out.println(mas.getName()+"被"+username+"玩家利用"+skill.type+skill.name
                +"攻击，剩余血量是"+mas.getHp()+"\n"+"技能CD需要"+skill.cd+"秒后恢复");
        System.out.println("==========================================");
        System.out.println("==========================================");
        System.out.println("==========================================");
        System.out.println("CD恢复");
        skill.setMp(skill.getMp()-5);
        if(skill.getMp()<=0){
            System.out.println("mp值过低无法使用技能");
        }else{
            System.out.println("mp等待1s自动恢复");
            System.out.println("=============");
            System.out.println("mp恢复成功");
            System.out.println("=============");
        }
    }
    //使用药水恢复hp或者mp
    //这里设计用药水的话恢复值不能超过生命值上限
    public void drug(){
        //设计0.15倍数
        yaoshui= (int) Math.round(hp*0.15);
        hp=hp+yaoshui;
        System.out.println("["+username+"使用的药水,增加了血量hp="+yaoshui+"的血量!]");
        System.out.println("喝了药水后迅速恢复hp="+hp);
    }
     public void wear(Equipment equip){
        //设计玩家一开始是没有装备的，不进持久化
         equip.loaded=0;
         //穿上装备
         new User().wearEquip(4);
         //锁定装备
         equip.lock();
    }
    public void wearEquip(int id) {
        //获取装备
        Equipment equip= getEquipmentById(id);
        //穿戴类型
        //String replaceEquip= getEquipmentById(id).getType();
        equip.setLoaded(0);
        this.atk=equip.getAtk()+100;
        System.out.println("穿上"+equip.getName()+",攻击力增加"+equip.getAtk()+"得以触发必杀技能");
        equip.setEndurance(equip.getEndurance()-30);
        if(equip.getEndurance()<=50){
            System.out.println("武器耐久度过低需要去修理一下");
            //修理方法，这里做一个假输出
            XiuLi();
        }else{
            System.out.println("武器耐久度正常不需要修理");
            System.out.println("=========================");
        }
    }
    //修理方法
       public void XiuLi() {
           System.out.println("1s自动修复");
           System.out.println("=================");
           System.out.println("修复完成");
    }

    //通过装备id获取装备
    public Equipment getEquipmentById(int id) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring-netty.xml");
        EquipMapper equipMapper = ac.getBean(EquipMapper.class);
        Equipment quer = equipMapper.querid(4);
        System.out.println(quer);
        return quer;
    }
    /**
     * 脱装备
     */
    public void unlockEquip(int id){
        Equipment equip = getEquipmentById(id);
        equip.unlock();
        System.out.println("解除脱装备完成");
    }
    public static void main(String[] args) {
        new User().wearEquip(4);
    }
}

package cn.pomit.springwork.netty.Entity;

import cn.pomit.springwork.netty.Enum.BagType;
import cn.pomit.springwork.netty.Enum.EquipType;
import cn.pomit.springwork.netty.Enum.SkillType;
import cn.pomit.springwork.netty.Monster.Boss;
import cn.pomit.springwork.netty.Monster.Monster;
import cn.pomit.springwork.netty.Skills.Skill;
import cn.pomit.springwork.netty.mapper.EquipMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.persistence.Id;
import javax.persistence.Table;

/*
用户
 */
@Table(name="tb_user")
public class User {
    @Id
    private Long uid;
    private String username;
    private String password;
    //当前生命值
    private Integer hp;
    //药水增加的hp或者mp
    private Integer yaoshui;
    //设计玩家的经验
    public Integer exp;
   //等级
   public Integer level;
    //下一级所需经验
    public Integer levelExp;
    public Integer atk;
    //金钱
    public Integer money;

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
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

    public Integer getHp() {
        return hp;
    }

    public void setHp(Integer hp) {
        this.hp = hp;
    }

    public Integer getYaoshui() {
        return yaoshui;
    }

    public void setYaoshui(Integer yaoshui) {
        this.yaoshui = yaoshui;
    }

    public Integer getExp() {
        return exp;
    }

    public void setExp(Integer exp) {
        this.exp = exp;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getLevelExp() {
        return levelExp;
    }

    public void setLevelExp(Integer levelExp) {
        this.levelExp = levelExp;
    }

    public void setAtk(Integer atk) {
        this.atk = atk;
    }

    public Integer getAtk() {
        return atk;
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
                ", money=" + money +
                '}';
    }

    //普通攻击
    public void bit(Monster mas){
        Skill skill=new Skill();
        skill.cd=1;
        skill.mp=30;
        mas.setHp(mas.getHp()-10);
        if(mas.getHp()<=0){
            mas.setHp(0);
        }
        System.out.println(mas.getName()+"被"+username+"玩家利用"+SkillType.Common.getName()
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
        skill.cd=3;
        skill.mp=30;
        mas.setHp(mas.getHp()-30);
        if(mas.getHp()<=0){
            mas.setHp(0);
        }
        System.out.println(mas.getName()+"被"+username+"玩家利用"+ SkillType.Bisha.getName()
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
    public void drug(User user){
        //设计0.15倍数
        yaoshui= (int) Math.round(hp*0.15);
        hp=hp+yaoshui;
        System.out.println("["+username+"使用背包中的物品"+ BagType.Yaoshui.getName() +",增加了血量hp="+yaoshui+"的血量!]");
        System.out.println("喝了药水后迅速恢复hp="+hp);
    }
     public void wear(Equipment equip){
        //设计玩家一开始是没有装备的，不进持久化
         equip.loaded=0;
         //穿上装备
         wearEquip(4);
         //穿上毒刀装备
         wearEquip(2);
         //锁定装备
         equip.setLocked(1);
    }
    public void wearEquip(int id) {
        //获取装备
        Equipment equip= getEquipmentById(id);
        //穿戴类型
        //String replaceEquip= getEquipmentById(id).getType();
        //equip.setLoaded(0);
        this.atk=equip.getAtk()+100;
        System.out.println("穿上装备"+",装备类型为"+ EquipType.Weapon.getName() +
                ",使得攻击力增加"+",继续加大攻击");
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
        Equipment quer = equipMapper.selectByPrimaryKey(4L);
        Equipment quer1 = equipMapper.selectByPrimaryKey(2L);
        System.out.println(quer);
        System.out.println(quer1);
        return quer;
    }
    /**
     * 脱装备
     */
    public void unlockEquip(int id){
        //脱下装备这里直接采用delete
        ApplicationContext ac=new ClassPathXmlApplicationContext("spring-netty.xml");
        EquipMapper equipMapper = ac.getBean(EquipMapper.class);
        int delete = equipMapper.deleteByPrimaryKey(4L);
        System.out.println("解除脱装备完成");
    }
    //副本boss攻击方法
    public void attackboss(Boss boss){
        Skill skill=new Skill();
        skill.cd=1;
        skill.mp=30;
        boss.setHp(boss.getHp()-30);
        if(boss.getHp()<=0){
            boss.setHp(0);
        }
        System.out.println(boss.getName()+"被"+username+"玩家利用"+SkillType.Common.getName()
                +"攻击，剩余血量是"+boss.getHp()+"\n"+"技能CD需要"+skill.cd+"秒后恢复");
        System.out.println("==========================================");
        System.out.println("CD恢复");
        skill.setMp(skill.getMp()-10);
        if(skill.getMp()<=0){
            System.out.println("mp值过低无法使用技能");
        }else{
            System.out.println("mp等待1s自动恢复");
            System.out.println("=============");
            System.out.println("mp恢复成功");
            System.out.println("=============");
        }
    }
    //穿上毒刀直接让怪兽中毒减大量的血
    public void zhongdu(Boss boss){
        boss.setHp(boss.getHp()-60);
        if(boss.getHp()<=0){
            boss.setHp(0);
        }
        System.out.println(boss.getName()+"被"+username+"玩家用毒刀斩杀中毒"+"血量为"+boss.getHp());
    }
    public static void main(String[] args) {
        new User().wearEquip(4);
    }
}

package cn.pomit.springwork.netty.User.Service.Impl;

import cn.pomit.springwork.netty.Equip.Enum.EquipType;
import cn.pomit.springwork.netty.Equip.Equipment;
import cn.pomit.springwork.netty.Mapper.EquipMapper;
import cn.pomit.springwork.netty.User.Entity.User;
import cn.pomit.springwork.netty.User.Service.EquipService;
import cn.pomit.springwork.netty.UtilSpring.SpringUtil;
import org.springframework.stereotype.Service;

@Service
public class EquipServiceImpl implements EquipService {
    @Override
    public void wear(User user, Equipment equip) {
        //设计玩家一开始是没有装备的，不进持久化
        equip.loaded=0;
        //穿上装备
        wearEquip(user,4L);
        //穿上毒刀装备
        wearEquip(user,2L);
        //锁定装备
        equip.setLocked(1);
    }
    @Override
    public void wearEquip(User user,Long id) {
        //获取装备
        Equipment equip= getEquipmentById(id);
        user.setAtk(equip.getAtk()+100);
       // this.atk=equip.getAtk()+100;
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
    @Override
    //通过装备id获取装备
    public Equipment getEquipmentById(Long id) {
        EquipMapper equipMapper=SpringUtil.getBean(EquipMapper.class);
        Equipment quer = equipMapper.selectByPrimaryKey(4L);
        Equipment quer1 = equipMapper.selectByPrimaryKey(2L);
        System.out.println(quer);
        System.out.println(quer1);
        return quer;
    }
    @Override
    /**
     * 脱装备
     */
    public void unlockEquip(Long  id){
        //脱下装备这里直接采用delete
        EquipMapper equipMapper=SpringUtil.getBean(EquipMapper.class);
        int delete = equipMapper.deleteByPrimaryKey(4L);
        System.out.println("解除脱装备完成");
    }
}

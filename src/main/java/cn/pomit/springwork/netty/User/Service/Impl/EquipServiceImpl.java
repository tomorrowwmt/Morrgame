package cn.pomit.springwork.netty.User.Service.Impl;

import cn.pomit.springwork.netty.Equip.Enum.EquipType;
import cn.pomit.springwork.netty.Equip.Equipment;
import cn.pomit.springwork.netty.Mapper.EquipMapper;
import cn.pomit.springwork.netty.User.Entity.User;
import cn.pomit.springwork.netty.User.Service.EquipService;
import cn.pomit.springwork.netty.UtilSpring.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("EquipGuavaCache")
public class EquipServiceImpl implements EquipService {
    @Autowired
    private  EquipMapper equipMapper;
    @Autowired
    private EquipService equipService;
    @Override
    public void wear(User user, Equipment equip) {
        //设计玩家一开始是没有装备的，不进持久化
        equip.loaded=0;
        //缓存查询装备
        EquipMapper equipMapper=SpringUtil.getBean(EquipMapper.class);
        Long eq= equipService.queryAllEquip().get(1).getId();
        Long id = equipService.queryAllEquip().get(3).getId();
        //穿上毒刀装备
        wearEquip(user,eq);
        //穿上屠龙刀装备
        wearEquip(user,id);
        //锁定装备
        equip.setLocked(1);
    }
    @Override
    public void wearEquip(User user,Long id) {
        //获取装备
        Equipment equip= getEquipmentById(id);
        System.out.println("穿上装备"+",装备类型为"+ EquipType.Weapon.getName() +
                ",使得攻击力增加"+",继续加大攻击");
        equip.setEndurance(equip.getEndurance()-20);
        if(equip.getEndurance()<=60){
            System.out.println("武器耐久度过低需要去修理一下");
            //修理方法，这里做一个假输出
            xiuLi();
        }else{
            System.out.println("武器耐久度正常不需要修理");
            System.out.println("=========================");
        }
    }
    //修理方法
    public void xiuLi() {
        System.out.println("1s自动修复");
        System.out.println("=================");
        System.out.println("修复完成");
    }
    @Override
    //通过装备id获取装备
    public Equipment getEquipmentById(Long id) {
        //缓存查询装备
        EquipMapper equipMapper=SpringUtil.getBean(EquipMapper.class);
        Long equipment= equipService.queryAllEquip().get(1).getId();
        Long equ = equipService.queryAllEquip().get(3).getId();
        //传入相关参数
        Equipment quer= equipService.queryEquipById(equipment);
        Equipment que= equipService.queryEquipById(equ);
        System.out.println(quer);
        System.out.println(que);
        return quer;
    }
    @Override
    /**
     * 脱装备
     */
    public void unlockEquip(Long  id){
        //脱下装备这里直接采用delete
        EquipMapper equipMapper=SpringUtil.getBean(EquipMapper.class);
        //查询装备所在地
        Long equid = equipMapper.selectAll().get(3).getId();
        int delete = equipMapper.deleteByPrimaryKey(equid);
        System.out.println("解除脱装备完成");
    }

    @Override
    @Cacheable(value = "equipCache")
    public List<Equipment> queryAllEquip() {
        return equipMapper.selectAll();
    }

    @Override
    @Cacheable(value = "equipCache",key="#id")
    public Equipment queryEquipById(Long id) {
        return equipMapper.selectByPrimaryKey(id);
    }
}

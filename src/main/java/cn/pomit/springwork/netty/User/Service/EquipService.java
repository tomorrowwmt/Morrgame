package cn.pomit.springwork.netty.User.Service;

import cn.pomit.springwork.netty.Equip.Equipment;
import cn.pomit.springwork.netty.User.Entity.User;

public interface EquipService {
    //穿装备
    void wear(User user,Equipment equip);
    //穿哪一个装备
    void wearEquip(User user,Long id);
    //获取装备
    Equipment getEquipmentById(Long id);
    //解脱装备
    void unlockEquip(Long  id);
}

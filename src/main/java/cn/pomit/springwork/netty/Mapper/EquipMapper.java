package cn.pomit.springwork.netty.Mapper;

import cn.pomit.springwork.netty.Equip.Equipment;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
@Repository
public interface EquipMapper extends Mapper<Equipment> {
}
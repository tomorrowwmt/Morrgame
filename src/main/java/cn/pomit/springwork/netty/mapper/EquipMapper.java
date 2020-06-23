package cn.pomit.springwork.netty.mapper;

import cn.pomit.springwork.netty.Entity.Equipment;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
@Repository
public interface EquipMapper extends Mapper<Equipment> {
}
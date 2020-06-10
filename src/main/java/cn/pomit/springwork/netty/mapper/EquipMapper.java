package cn.pomit.springwork.netty.mapper;

import cn.pomit.springwork.netty.entity.Equipment;
import net.sf.json.JSONObject;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface EquipMapper {
    //查询
    @Select("select * from tb_equip")
    List<Equipment> queryequip();
    //id查询
    @Select("select * from tb_equip where id=#{id}")
    Equipment querid(int id);
    //增加
    @Insert("insert into tb_equip values(#{id},#{name},#{atk},#{type},#{endurance})")
    int insert(Equipment equipment);
    //删除
    @Delete("delete from tb_equip where id=#{id}")
    int delete(int id);
    //更新
    @Update("update tb_equip set endurance=#{endurance} where id=#{id}")
    int update(Equipment equip);
}
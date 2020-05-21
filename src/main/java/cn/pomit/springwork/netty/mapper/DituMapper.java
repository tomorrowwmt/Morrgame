package cn.pomit.springwork.netty.mapper;

import cn.pomit.springwork.netty.entity.Ditu;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DituMapper {
    /*
    查询所有地图
     */
    @Select("select * from tb_map")
    List<Ditu> querymap();
}

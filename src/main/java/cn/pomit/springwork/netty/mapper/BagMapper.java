package cn.pomit.springwork.netty.mapper;
import cn.pomit.springwork.netty.entity.Bag;
import cn.pomit.springwork.netty.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.poi.ss.formula.functions.Count;

import java.util.List;


public interface BagMapper {
    @Select("select * from tb_bag")
    List<Bag> queryBag();
    @Insert("insert into tb_bag(id,Iname,besc,capacity) values(#{id},#{Iname},#{besc},#{capacity})")
    int insert(Bag bag);
    @Delete("delete from tb_bag where id=#{id}")
    int delete(int id);
    //通过id查询用户
    @Select("select * from tb_bag where id=#{id}")
    Bag queryid(int id);
    //更新
    @Update("update tb_bag set count=#{count} where id=#{id}")
    int update(Bag bag);
}

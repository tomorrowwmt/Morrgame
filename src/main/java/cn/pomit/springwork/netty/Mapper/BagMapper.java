package cn.pomit.springwork.netty.Mapper;
import cn.pomit.springwork.netty.Bag.Entity.Bag;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface BagMapper extends Mapper<Bag> {
}

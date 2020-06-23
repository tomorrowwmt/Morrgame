package cn.pomit.springwork.netty.mapper;
import cn.pomit.springwork.netty.Entity.Bag;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface BagMapper extends Mapper<Bag> {
}

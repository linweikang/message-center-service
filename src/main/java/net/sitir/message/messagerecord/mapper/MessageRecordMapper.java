package net.sitir.message.messagerecord.mapper;

import net.sitir.message.messagerecord.pojo.entity.MessageRecordEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 消息记录信息表Mapper 接口
 * </p>
 *
 * @author wangyj
 * @since 2023-08-07
 */
@Mapper
public interface MessageRecordMapper extends BaseMapper<MessageRecordEntity> {

}

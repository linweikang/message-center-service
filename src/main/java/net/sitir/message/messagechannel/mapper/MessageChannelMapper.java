package net.sitir.message.messagechannel.mapper;

import net.sitir.message.messagechannel.pojo.entity.MessageChannelEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 消息渠道信息表Mapper 接口
 * </p>
 *
 * @author wangyj
 * @since 2023-08-07
 */
@Mapper
public interface MessageChannelMapper extends BaseMapper<MessageChannelEntity> {

}

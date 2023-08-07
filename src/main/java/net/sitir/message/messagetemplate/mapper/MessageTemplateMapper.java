package net.sitir.message.messagetemplate.mapper;

import net.sitir.message.messagetemplate.pojo.entity.MessageTemplateEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 消息模板信息表Mapper 接口
 * </p>
 *
 * @author wangyj
 * @since 2023-08-07
 */
@Mapper
public interface MessageTemplateMapper extends BaseMapper<MessageTemplateEntity> {

}

package net.sitir.message.messagechannel.service;

import com.baomidou.mybatisplus.extension.service.IService;

import net.sitir.message.component.common.Pagination;
import net.sitir.message.messagechannel.pojo.dto.OperationMessageChannelDTO;
import net.sitir.message.messagechannel.pojo.dto.PageMessageChannelDTO;
import net.sitir.message.messagechannel.pojo.entity.MessageChannelEntity;
import net.sitir.message.messagechannel.pojo.vo.MessageChannelVO;

/**
 * <p>
 * 消息渠道信息表服务类
 * </p>
 *
 * @author wangyj
 * @since 2023-08-07
 */
public interface MessageChannelService extends IService<MessageChannelEntity> {

    /**
     * 分页条件查询消息渠道信息表
     * @param dto 查询条件
     * @return 消息渠道信息表
     */
    Pagination<MessageChannelVO> getPage(PageMessageChannelDTO dto);

    /**
     * 新增消息渠道信息表
     * @param dto 消息渠道信息表
     * @return 返回插入的主键id 0：失败；> 0：成功
     */
    Long saveEntity(OperationMessageChannelDTO dto);

    /**
     * 修改消息渠道信息表
     * @param dto 消息渠道信息表
     * @return 操作结果 true: 成功；false: 失败；
     */
    Boolean updateEntity(OperationMessageChannelDTO dto);


}

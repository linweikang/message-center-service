package net.sitir.message.messagerecord.service;

import com.baomidou.mybatisplus.extension.service.IService;

import net.sitir.message.component.common.Pagination;
import net.sitir.message.messagerecord.pojo.dto.OperationMessageRecordDTO;
import net.sitir.message.messagerecord.pojo.dto.PageMessageRecordDTO;
import net.sitir.message.messagerecord.pojo.entity.MessageRecordEntity;
import net.sitir.message.messagerecord.pojo.vo.MessageRecordVO;

/**
 * <p>
 * 消息记录信息表服务类
 * </p>
 *
 * @author wangyj
 * @since 2023-08-07
 */
public interface MessageRecordService extends IService<MessageRecordEntity> {

    /**
     * 分页条件查询消息记录信息表
     * @param dto 查询条件
     * @return 消息记录信息表
     */
    Pagination<MessageRecordVO> getPage(PageMessageRecordDTO dto);

    /**
     * 新增消息记录信息表
     * @param dto 消息记录信息表
     * @return 返回插入的主键id 0：失败；> 0：成功
     */
    Long saveEntity(OperationMessageRecordDTO dto);

    /**
     * 修改消息记录信息表
     * @param dto 消息记录信息表
     * @return 操作结果 true: 成功；false: 失败；
     */
    Boolean updateEntity(OperationMessageRecordDTO dto);


}
